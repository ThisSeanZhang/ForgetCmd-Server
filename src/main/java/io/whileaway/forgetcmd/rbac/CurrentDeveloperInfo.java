package io.whileaway.forgetcmd.rbac;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CurrentDeveloperInfo {

    private final HttpSession session;

    public Developer getCurrentDeveloper() {
        Object attribute = session.getAttribute(RBACConstant.CURRENT_DEVELOPER);
        return (Developer) attribute;
    }

    public Long getDid() {
        Object developer = session.getAttribute(RBACConstant.CURRENT_DEVELOPER);
        return Optional.ofNullable(developer)
                .map(de -> (Developer) de)
                .map(Developer::getDid)
                .orElse(null);
    }
}
