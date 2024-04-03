package conn.ra.Service.AuthService;

import conn.ra.Model.Dto.Request.UserLogin;
import conn.ra.Model.Dto.Request.UserRegister;
import conn.ra.Model.Dto.Response.UserResponse;
import conn.ra.Model.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    Page<User> getAll(Pageable pageable);
    User findById(Long id);
    void delete(Long id);
    User save(User user);
    User updateAcc(UserRegister userRegister,Long id);
    Optional<User> findByUserName(String userName);
}
