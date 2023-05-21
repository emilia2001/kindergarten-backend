package kindergarten.management.service;

import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;

import java.util.List;

public interface RegistrationRequestService {

    void addRequest(RegistrationRequestParentDto requestDto);

    List<RegistrationRequestParentDto> findAllRequestsByParent(Long id);

    RegistrationRequestParentDto findOneById(Long id);

    List<RegistrationRequestParentDto> findAllRequests();

    void updateRequest(RegistrationRequestParentDto requestDto);
}
