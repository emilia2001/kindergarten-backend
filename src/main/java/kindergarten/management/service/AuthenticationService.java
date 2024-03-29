package kindergarten.management.service;

import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.dto.parent.ParentRegisterDto;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    User findUser(UserLoginDto loginDto);

    Parent registerParent(ParentRegisterDto registerDto) throws Exception;

}
