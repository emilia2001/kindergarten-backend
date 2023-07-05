package kindergarten.management.service.job;

import kindergarten.management.model.entity.Group;
import kindergarten.management.model.enums.EGroupType;
import kindergarten.management.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChangeGroupTypeJob {

    private final GroupRepository groupRepository;

//    @Scheduled(cron = "0 0 1 1 7 *")
    public void changeGroupTypes() {
        List<Group> groups = groupRepository.findAll();

        groups.forEach((Group group) -> {
            EGroupType currentType = group.getGroupType();
            EGroupType newType = getNextGroupType(currentType);

            group.setGroupType(newType);
        });

        groupRepository.saveAll(groups);
    }

    private static EGroupType getNextGroupType(EGroupType currentType) {
        if (currentType == EGroupType.JUNIOR) {
            return EGroupType.MIDDLE;
        } else if (currentType == EGroupType.MIDDLE) {
            return EGroupType.SENIOR;
        } else {
            return EGroupType.JUNIOR;
        }
    }
}
