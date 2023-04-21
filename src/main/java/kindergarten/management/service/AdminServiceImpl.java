package kindergarten.management.service;

import kindergarten.management.model.entity.Admin;
import kindergarten.management.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

}
