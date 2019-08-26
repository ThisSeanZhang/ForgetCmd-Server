package io.whileaway.forgetcmd.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CmdOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private Long cid;
    private String briefName;
    private String fullName;
    private String description;
    private Long whenDeprecated;
    private Long whenEnable;
    private Long frequency;
}
