package kindergarten.management.service;


import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    User findUser(UserLoginDto loginDto);

}
