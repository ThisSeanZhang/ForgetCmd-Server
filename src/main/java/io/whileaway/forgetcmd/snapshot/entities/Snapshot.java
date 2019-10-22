package io.whileaway.forgetcmd.snapshot.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Snapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snapId;
    @Column(columnDefinition="text")
    private String config;
    private String title;
    private Long createTime;
    private Boolean isOpen;
    private Boolean share;
    private String shareCode;
    private Boolean allowCopy;

    private Long did;

}
