package io.whileaway.forgetcmd.rbac.request;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.enums.DeveloperStatus;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccount {

    @NotNull(message = "require developerName")
    @NotBlank(message = "开发者名称不能为空")
    @Length(min = 1 ,max = 20)
    private String nickName;
    @NotNull(message = "require developerPass")
    @Length(min = 1 ,max = 20)
    @NotBlank(message = "密码不能为空")
    private String pass;
    @NotNull(message = "require email")
    @Length(min = 1 ,max = 20)
    @NotBlank(message = "email不能为空")
    private String email;


    public Developer convertDeveloper() {
        Developer developer = new Developer();
        developer.setNickname( nickName );
        developer.setEmail(email);
        developer.setPass(pass);
        developer.setStatus(DeveloperStatus.NORMAL);
        return developer;
    }
}
