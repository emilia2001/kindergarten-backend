package kindergarten.management.service;

import kindergarten.management.exceptions.RequestException;
import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;

import java.util.List;

public interface ExtensionRequestService {

    void addRequest(ExtensionRequestParentDto requestDto) throws RequestException;

    List<ExtensionRequestParentDto> findAllRequestsByParent(Long id);

    ExtensionRequestParentDto findOneById(Long id);

    List<ExtensionRequestParentDto> findAllRequests();

    void updateRequestByAdmin(ExtensionRequestParentDto requestDto);

    void updateRequestByParent(ExtensionRequestParentDto requestDto);
}
