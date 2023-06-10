package kindergarten.management.service;

import kindergarten.management.mapper.ParentMapper;
import kindergarten.management.model.dto.parent.ParentRegisterDto;
import kindergarten.management.model.entity.Parent;
import kindergarten.management.model.enums.EUserRole;
import kindergarten.management.model.dto.UserLoginDto;
import kindergarten.management.model.entity.User;
import kindergarten.management.repository.AdminRepository;
import kindergarten.management.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private ParentRepository parentRepository;
    private ParentMapper parentMapper;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findUser(UserLoginDto loginDto) {
        User user = EUserRole.ADMIN == loginDto.getRole() ?
                adminRepository.findByUsername(loginDto.getUsername()) : parentRepository.findByUsername(loginDto.getUsername());
        if (user != null){
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Parent registerParent(ParentRegisterDto registerDto) {
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        Parent existingParent = parentRepository.findByEmail(registerDto.getEmail());
        Parent parent = parentMapper.toEntityFromRegisterDto(registerDto);
        if (existingParent != null)
            parent.setId(existingParent.getId());
        parentRepository.save(parent);
        return parent;
    }

}
