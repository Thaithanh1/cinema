package movie.service.interfaceservice;

import movie.entity.User;

import java.util.Optional;

public interface iUserService {
    public void register(User user);
    public void confirmRegistration(String confirmCode);
    public Optional<User> findByUsername(String username);
}
