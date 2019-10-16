package io.whileaway.forgetcmd.rbac.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long did;
    @Column(length = 50,unique = true, nullable = false)
    private String nickname;
    @Column(length = 64, nullable = false)
    @JsonIgnore
    private String pass;
    @Column(length = 50, nullable = false)
    private String email;
    private Integer status;
    @JsonIgnore
    private String salt;

}
