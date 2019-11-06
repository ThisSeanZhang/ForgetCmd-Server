package io.whileaway.forgetcmd.snapshot.entities;

import io.whileaway.forgetcmd.snapshot.enums.ShareType;
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
    @Convert(converter = ShareType.Converter.class)
    private ShareType share;
    private String shareCode;
    private Boolean allowCopy;
    private Long cid;

    private Long did;

}
