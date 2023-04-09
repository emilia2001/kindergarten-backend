package kindergarten.management.controller;

import kindergarten.management.model.entity.Admin;
import kindergarten.management.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin()
@AllArgsConstructor
public class AdminController {
    @Autowired
    AdminRepository repository;

    @GetMapping("/auth")
    public ResponseEntity<List<Admin>> authentication(){
        return ResponseEntity.ok(repository.findAll());
    }
}
