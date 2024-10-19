package movie.payload.converter;

import movie.entity.User;
import movie.payload.dto.UserDTO;
import movie.payload.request.UserRequest;

public class UserConverter {
    public UserDTO entityToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setPoint(user.getPoint());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setPassword(user.getPassword());
        dto.setActive(user.isActive());
        dto.setRankCustomerID(user.getRankCustomerID());
        dto.setUserStatusID(user.getUserStatusID());
        dto.setRoleID(user.getRoleID());
        return dto;
    }

    public User addUser(UserRequest request) {
        User user = new User();
        user.setPoint(request.getPoint());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setActive(request.isActive());
        user.setRankCustomerID(request.getRankCustomerID());
        user.setUserStatusID(request.getUserStatusID());
        user.setRoleID(request.getRoleID());
        return user;
    }

    public User editUser(User user, UserRequest request) {
        user.setPoint(request.getPoint());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setActive(request.isActive());
        user.setRankCustomerID(request.getRankCustomerID());
        user.setUserStatusID(request.getUserStatusID());
        user.setRoleID(request.getRoleID());
        return user;
    }
}
