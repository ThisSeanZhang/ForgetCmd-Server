package io.whileaway.forgetcmd.cmd.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CmdParam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpid;
    private Long cid;
    private Integer sort;
    private String paramName;
    private String description;
    private boolean required;
}
