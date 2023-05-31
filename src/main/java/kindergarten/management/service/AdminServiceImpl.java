package kindergarten.management.service;

import kindergarten.management.mapper.AdminMapper;
import kindergarten.management.model.dto.AdminDto;
import kindergarten.management.model.entity.Admin;
import kindergarten.management.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private AdminMapper adminMapper;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public void addAdmin(AdminDto adminDto) {
        adminRepository.save(adminMapper.toEntity(adminDto));
    }

}
