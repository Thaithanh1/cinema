package movie.controller;

import movie.dto.response.TokenResponse;
import movie.dto.request.LoginRequest;
import movie.entity.User;
import movie.payload.dto.FoodDTO;
import movie.payload.dto.UserDTO;
import movie.payload.request.ChangePasswordRequest;
import movie.payload.request.FoodRequest;
import movie.payload.request.UserRequest;
import movie.payload.response.ResponseObject;
import movie.service.PasswordResetService;
import movie.service.UserService;
import movie.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok("Đã gửi email xác nhận đăng ký. Vui lòng kiểm tra hộp thư của bạn.");
    }

    @GetMapping("confirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("code") String confirmCode) {
        userService.confirmRegistration(confirmCode);
        return ResponseEntity.ok("Đăng ký tài khoản thành công!");
    }

    @PostMapping("login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(userServiceImpl.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.changePassword(currentUsername, request.getOldPassword(), request.getNewPassword());
        return ResponseEntity.ok("Password changed successfully");
    }

    // Quên password
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        passwordResetService.sendResetPasswordCode(email);
        return "Reset password code sent to your email.";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String newPassword) {
        boolean isSuccess = passwordResetService.verifyResetCodeAndChangePassword(email, code, newPassword);
        return isSuccess ? "Password changed successfully!" : "Invalid code or expired.";
    }

    @RequestMapping(value = "user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> addUser(@RequestBody UserRequest request) {
        return userService.addUser(request);
    }

    @RequestMapping(value = "user/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> editUser(@RequestParam int userID, @RequestBody UserRequest request) {
        return userService.editUser(userID, request);
    }

    @RequestMapping(value = "user/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<UserDTO> deleteUser(@RequestParam int userID) {
        return userService.deleteUser(userID);
    }
}
