package io.whileaway.forgetcmd.snapshot.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snapId;
    private String config;
    private String title;
    private Long createTime;
    private Boolean isOpen;
    private Boolean share;
    private String shareCode;
    private Boolean allowCopy;

    private Long did;

}
