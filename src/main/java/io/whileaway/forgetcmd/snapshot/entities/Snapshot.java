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
    private String paramVal;
    @Column(columnDefinition="text")
    private String optionVal;
    private String title;
    private Long createTime;
//    @Convert(converter = ShareType.Converter.class)
    private boolean share;
    private String shareCode;
    private boolean allowCopy;
    private String commandName;
    private Long cid;
    private Long ccid;
    private Long did;
    private String location;

}
