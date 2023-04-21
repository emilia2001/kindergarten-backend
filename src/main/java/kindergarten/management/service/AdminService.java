package kindergarten.management.service;

import kindergarten.management.model.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin findByUsername(String username);

    List<Admin> findAllAdmins();
}
