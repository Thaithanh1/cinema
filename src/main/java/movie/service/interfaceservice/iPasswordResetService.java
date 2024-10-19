package movie.service.interfaceservice;

import movie.repository.UserRepository;
import movie.service.InMemoryConfirmationStore;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface iPasswordResetService {
    public void sendResetPasswordCode(String email);
    public boolean verifyResetCodeAndChangePassword(String email, String code, String newPassword);
}
