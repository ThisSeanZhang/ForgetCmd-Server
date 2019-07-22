package io.whileaway.forgetcmd.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CmdOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    private Long belongCid;
    private String briefName;
    private String fullName;
    private String description;

}
