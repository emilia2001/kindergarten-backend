package kindergarten.management.service;

import kindergarten.management.mapper.AnnouncementMapper;
import kindergarten.management.model.dto.AnnouncementDto;
import kindergarten.management.model.entity.Announcement;
import kindergarten.management.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public List<AnnouncementDto> findAllAnnouncements() {
        return announcementMapper.toDtos(announcementRepository.findAll());
    }

    @Override
    public void addAnnouncement(AnnouncementDto announcementDto) {
        announcementRepository.save(announcementMapper.toEntity(announcementDto));
    }

    @Override
    public void deleteAnnouncement(String id) {
        Announcement announcement = new Announcement();
        announcement.setId(Long.valueOf(id));
        announcementRepository.delete(announcement);
    }

    @Override
    public AnnouncementDto findOneById(Long id) {
        return announcementMapper.toDto(announcementRepository.getById(id));
    }
}
