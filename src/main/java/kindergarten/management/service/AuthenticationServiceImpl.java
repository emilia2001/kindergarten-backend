package kindergarten.management.service;

import kindergarten.management.model.EUserRole;
import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.entity.User;
import kindergarten.management.repository.AdminRepository;
import kindergarten.management.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private ParentRepository parentRepository;
    private AdminRepository adminRepository;

    @Override
    public User findUser(UserLoginDto loginDto) {
        User user = EUserRole.ADMIN == loginDto.getRole() ?
                adminRepository.findByUsername(loginDto.getUsername()) : parentRepository.findByUsername(loginDto.getUsername());
        if(user != null){
            if(user.getPassword().equals(loginDto.getPassword())){
                return user;
            }
        }
        return null;
    }

}
