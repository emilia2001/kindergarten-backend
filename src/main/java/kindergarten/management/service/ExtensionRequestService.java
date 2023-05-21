package kindergarten.management.service;

import kindergarten.management.model.dto.request.extension.ExtensionRequestParentDto;

import java.util.List;

public interface ExtensionRequestService {

    void addRequest(ExtensionRequestParentDto requestDto);

    List<ExtensionRequestParentDto> findAllRequestsByParent(Long id);

    ExtensionRequestParentDto findOneById(Long id);

    List<ExtensionRequestParentDto> findAllRequests();

    void updateRequest(ExtensionRequestParentDto requestDto);
}
