package movie.model;

import lombok.*;
import movie.entity.RefreshToken;
import movie.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomDetail implements UserDetails {
    private User user;

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserCustomDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCustomDetail(User user) {
        this.user = user;
    }

//    public UserCustomDetail(User user) {
//        this.user = user;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().getRoleEnums().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUserId() {
        return ""+user.getUserID();
    }
}