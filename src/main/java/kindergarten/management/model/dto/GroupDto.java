package kindergarten.management.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GroupDto implements Serializable {
    private long id;
    private String name;
}
