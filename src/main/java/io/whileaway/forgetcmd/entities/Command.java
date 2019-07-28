package io.whileaway.forgetcmd.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    private String commandName;
    private String description;
    private String version;
    private String platform;
    private Integer argNum;
    private Long whenDeprecated;
    private Long whenEnable;
}
