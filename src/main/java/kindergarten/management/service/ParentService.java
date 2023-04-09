package kindergarten.management.service;

import kindergarten.management.model.entity.Parent;

public interface ParentService {

    Parent findByUsername(String username);

}
