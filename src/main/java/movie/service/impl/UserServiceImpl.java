package movie.service.impl;

import movie.dto.response.MessageResponse;
import movie.dto.response.TokenResponse;
import movie.dto.request.LoginRequest;
import movie.dto.request.RegisterRequest;
import movie.entity.Role;
import movie.entity.User;
import movie.model.UserCustomDetail;
import movie.repository.RoleRepository;
import movie.repository.UserRepository;
import movie.service.UserServcie;
import movie.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServcie {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Optional<User> findByUser = userRepository.findByUserName(loginRequest.getUsername());
        String jwtToken = jwtService.generatedToken(new UserCustomDetail(findByUser.get()));
        return TokenResponse
                .builder()
                .token(jwtToken)
                .role(findByUser.get().getRole().getRoleEnums().name())
                .build();
    }

    @Override
    public MessageResponse register(RegisterRequest registerRequest) {

        Optional<Role> findByRole = roleRepository.findByRoleEnums(registerRequest.getRole());

        User user = User
                .builder()
                .userName(  registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(findByRole.get())
                .build();
        userRepository.save(user);
        return MessageResponse.builder().message("Dang ky thanh cong").build();
    }
}
