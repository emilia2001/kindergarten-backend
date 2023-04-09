package kindergarten.management.service;

import kindergarten.management.model.entity.Parent;
import kindergarten.management.repository.ParentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ParentServiceImpl implements ParentService{
    private ParentRepository parentRepository;

    @Override
    public Parent findByUsername(String username) {
        return parentRepository.findByUsername(username);
    }
}
