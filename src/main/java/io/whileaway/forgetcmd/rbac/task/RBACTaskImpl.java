package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.SessionKeyConstant;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.request.CreateAccount;
import io.whileaway.forgetcmd.rbac.request.CreateSession;
import io.whileaway.forgetcmd.rbac.service.DeveloperService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class RBACTaskImpl implements RBACTask {

    private final HttpSession session;
    private final DeveloperService developerService;

    public RBACTaskImpl(HttpSession session, DeveloperService developerService) {
        this.session = session;
        this.developerService = developerService;
    }

    @Override
    public Developer createSession(CreateSession createSession) {
        List<Developer> developers = developerService.matchTheWholeNameOrEmail(createSession.getNameOrEmail());
        developers.stream().filter()
        session.setAttribute(SessionKeyConstant.CURRENT_DEVELOPER, developer);
        return null;
    }

    @Override
    public Developer createAccount(CreateAccount createAccount) {
        return null;
    }

    @Override
    public boolean existNameOrEmail(String key) {
        developerService.matchTheWholeNameOrEmail(createSession.getNameOrEmail());
        return false;
    }
}
