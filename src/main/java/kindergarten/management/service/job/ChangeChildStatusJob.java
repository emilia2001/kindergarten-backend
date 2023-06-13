package kindergarten.management.service.job;

import kindergarten.management.model.entity.Child;
import kindergarten.management.model.entity.Group;
import kindergarten.management.model.enums.EChildStatus;
import kindergarten.management.model.enums.EGroupType;
import kindergarten.management.repository.ChildrenRepository;
import kindergarten.management.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChangeChildStatusJob {

    private final ChildrenRepository childRepository;
    private final GroupRepository groupRepository;


    @Scheduled(cron = "0 0 2 1 7 *")
    public void changeChildStatus() {
        List<Group> groups = groupRepository.findByGroupType(EGroupType.SENIOR);

        groups.forEach((Group group) -> {
            List<Child> children = group.getChildren();

            children.forEach((Child child) -> {
                if (child.getStatus() != EChildStatus.REJECTED) {
                    child.setStatus(EChildStatus.REJECTED);
                }
            });

            childRepository.saveAll(children);
        });
    }
}
