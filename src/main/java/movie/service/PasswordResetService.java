package movie.service;

import movie.entity.User;
import movie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private InMemoryConfirmationStore store;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;


    public void sendResetPasswordCode(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String code = generateResetCode();
            store.saveCode(user.getEmail(), code);

            // Logic gửi email chứa mã xác nhận
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Mã đổi mật khẩu người dùng");
            message.setText("Xin chào " + user.getName() + ",\n"
                    + "Vui lòng nhập đoạn mã sau để đổi mật khẩu của bạn:\n"
                    + "" + code);
            javaMailSender.send(message);
        }
    }

    public boolean verifyResetCodeAndChangePassword(String email, String code, String newPassword) {
        boolean isCodeValid = store.verifyCode(email, code);
        if (isCodeValid) {
            User user = userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private String generateResetCode() {
        // Sinh mã ngẫu nhiên
        return String.valueOf((int)((Math.random() * 9000) + 1000)); // Mã 4 chữ số
    }
}
