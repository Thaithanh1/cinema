package movie.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import movie.entity.User;
import movie.model.UserCustomDetail;
import movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    public static final String SECRET_KEY = "20DIRVMdduxwuE9QGgVnnTRqzg/Qbjt5JjeFys1Jnk4=";
//    public static final String SECRET_KEY = "HMOFRM3BTqhvrwyvheNQDkMpIc2xTyIZ/bN62VW1zPg=";

    @Autowired
    private UserRepository userRepository;
    public UserCustomDetail verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            // Tìm kiếm người dùng trong cơ sở dữ liệu
            return userRepository.findByUserName(username)
                    .map(user -> new UserCustomDetail(user.getUserName(), user.getPassword()))
                    .orElse(null); // Trả về null nếu không tìm thấy người dùng
        } catch (JwtException e) {
            // Xử lý token không hợp lệ
            return null;
        }
    }

    // Phương thức để xác thực token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // Phương thức để lấy username từ token
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public String extractUsername(String token) {
        return extractClam(token, Claims::getSubject);
    }

    public <T> T extractClam(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClams(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClams(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generatedToken(UserCustomDetail userCustomDetail) {
        return generatedToken(new HashMap<>(), userCustomDetail);
    }

    public String generatedToken(Map<String, Object> extractClam, UserCustomDetail userCustomDetail) {
        return buildToken(extractClam, userCustomDetail);
    }

    public String buildToken(Map<String, Object> extractClam, UserCustomDetail userCustomDetail) {
        return Jwts
                .builder()
                .setClaims(extractClam)
                .setSubject(userCustomDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClam(token, Claims::getExpiration);
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
