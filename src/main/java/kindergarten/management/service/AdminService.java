package kindergarten.management.service;

import kindergarten.management.model.dto.AdminDto;
import kindergarten.management.model.entity.Admin;

public interface AdminService {

    Admin findByUsername(String username);

    void addAdmin(AdminDto adminDto);
}
