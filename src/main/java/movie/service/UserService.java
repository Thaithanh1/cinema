package movie.service;

import movie.entity.ConfirmEmail;
import movie.entity.User;
import movie.payload.converter.UserConverter;
import movie.payload.dto.UserDTO;
import movie.payload.request.UserRequest;
import movie.payload.response.ResponseObject;
import movie.repository.ConfirmEmailRepository;
import movie.repository.UserRepository;
import movie.service.interfaceservice.iUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements iUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmEmailRepository confirmEmailRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserConverter _converter;
    private ResponseObject<UserDTO> _userResponseObject;

    public UserService() {
        _converter = new UserConverter();
        _userResponseObject = new ResponseObject<>();
    }


    public void register(User user) {
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        ConfirmEmail confirmEmail = new ConfirmEmail();
        confirmEmail.setUserID(user.getUserID());
        confirmEmail.setUser(savedUser);
        confirmEmail.setConfirmCode(generateRandomCode(10)); // Độ dài mã xác nhận là 10 ký tự
        // Tạo thời điểm hết hạn là 30 phút sau thời điểm hiện tại
        Date expiredTime = new Date(System.currentTimeMillis() + (30 * 60 * 1000));
        confirmEmail.setExpiredTime(expiredTime);
        confirmEmailRepository.save(confirmEmail);

        // Gửi email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Xác nhận đăng ký tài khoản");
        message.setText("Xin chào " + user.getName() + ",\n"
                + "Vui lòng click vào đường link sau để xác nhận đăng ký tài khoản:\n"
                + "http://localhost:8080/authentication/confirm?code=" + confirmEmail.getConfirmCode());
        javaMailSender.send(message);
    }

    public void confirmRegistration(String confirmCode) {
        ConfirmEmail confirmEmail = confirmEmailRepository.findByConfirmCode(confirmCode);
        if (confirmEmail != null && !confirmEmail.isConfirm() && confirmEmail.getExpiredTime().after(new Date())) {
            confirmEmail.getUser().setActive(true);
            confirmEmail.setConfirm(true);
            userRepository.save(confirmEmail.getUser());
            confirmEmailRepository.save(confirmEmail);
        }
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    private String generateRandomCode(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    public ResponseObject<UserDTO> addUser(UserRequest request) {
        User us = _converter.addUser(request);
        userRepository.save(us);
        return _userResponseObject.responseSuccess("Them user thanh cong", _converter.entityToUserDTO(us));
    }

    public ResponseObject<UserDTO> editUser(int userID, UserRequest request) {
        var checkUser = userRepository.findById(userID);
        if (checkUser.isEmpty()) {
            return _userResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "User khong ton tai", null);
        }else {
            User us = _converter.editUser(checkUser.get(), request);
            userRepository.save(us);
            return _userResponseObject.responseSuccess("Cap nhat user thanh cong", _converter.entityToUserDTO(us));
        }
    }

    public ResponseObject<UserDTO> deleteUser(int userID) {
        var checkUser = userRepository.findById(userID);
        if (checkUser.isEmpty()) {
            return _userResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "User not found", null);
        }else {
            userRepository.delete(checkUser.get());
            return _userResponseObject.responseSuccess("Xoa user thanh cong", null);
        }
    }
}