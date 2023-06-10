package kindergarten.management.service;

import kindergarten.management.exceptions.RequestException;
import kindergarten.management.model.dto.request.registration.RegistrationRequestParentDto;

import java.util.List;

public interface RegistrationRequestService {

    void addRequest(RegistrationRequestParentDto requestDto) throws RequestException;

    List<RegistrationRequestParentDto> findAllRequestsByParent(Long id);

    RegistrationRequestParentDto findOneById(Long id);

    List<RegistrationRequestParentDto> findAllRequests();

    void updateRequestByAdmin(RegistrationRequestParentDto requestDto);

    void updateRequestByParent(RegistrationRequestParentDto requestDto);
}
