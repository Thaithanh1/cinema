package movie.service;

import movie.dto.response.MessageResponse;
import movie.dto.response.TokenResponse;
import movie.dto.request.LoginRequest;
import movie.dto.request.RegisterRequest;

public interface UserServcie {
    TokenResponse login(LoginRequest loginRequest);

    MessageResponse register(RegisterRequest registerRequest);
}
