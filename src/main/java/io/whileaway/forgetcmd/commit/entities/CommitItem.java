package io.whileaway.forgetcmd.commit.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CommitItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ciid;
    private String type; // 用于指定修改的是命令还是参数还是选项
    // 指定key如果是命令  那就是cName之类的，Option以全称为关键字，全称变了就算变了 Param为参数的index索引
    private String keyPath;
    private String oValue;
    private String value; // 变更后的值
    private Long cid;
    private Long ccid; // 所属的命令提交
    private Long version;
    private Integer action;

}
