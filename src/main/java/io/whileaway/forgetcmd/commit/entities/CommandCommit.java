package io.whileaway.forgetcmd.commit.entities;

import io.whileaway.forgetcmd.commit.enums.CommitStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CommandCommit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // commit info
    private Long ccid;
    private Long createTime;
    private CommitStatus status;
    // create info
    private Long creatorId;
    private String whoCreated;
    // command info
    private Long cid;
    private String commandName;
    private String briefDesc;
    private String description;
    private Long version;
    private String platform;
    private Integer argNum;
    private String whenDeprecated;
    private String whenEnable;
    // Developer ID
    private Long did;

    // improve cmd remark
    @Column(columnDefinition="text")
    private String remark;
    @Column(columnDefinition="text")
    private String options;
    @Column(columnDefinition="text")
    private String params;

}
