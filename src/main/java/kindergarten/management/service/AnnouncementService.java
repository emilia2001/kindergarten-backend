package kindergarten.management.service;

import kindergarten.management.model.dto.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {

    List<AnnouncementDto> findAllAnnouncements();

    void addAnnouncement(AnnouncementDto announcementDto);

    void deleteAnnouncement(String id);
    AnnouncementDto findOneById(final Long id);

}
