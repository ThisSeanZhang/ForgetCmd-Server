package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.SessionKeyConstant;
import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.enums.DeveloperError;
import io.whileaway.forgetcmd.rbac.enums.DeveloperStatus;
import io.whileaway.forgetcmd.rbac.request.CreateAccount;
import io.whileaway.forgetcmd.rbac.request.CreateSession;
import io.whileaway.forgetcmd.rbac.service.DeveloperService;
import io.whileaway.forgetcmd.util.Crypto;
import io.whileaway.forgetcmd.util.ParamInspect;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
        developers = developers.stream()
                .filter(developer -> DeveloperStatus.NORMAL == developer.getStatus())
                .filter(developer ->
                        developer.getPass().equals(
                                Crypto.cryptoPass(createSession.getPass(), developer.getSalt())
                        )
                ).collect(Collectors.toList());
        // 先判断是否为空
        if (developers.isEmpty()) DeveloperError.NAME_OR_EMAIL_OR_PASS_ERROR.throwThis();
        // 再判断是否有多匹配了
        if (developers.size() > 1) CommonErrorEnum.SERVER_ERROR.throwThis();
        session.setAttribute(SessionKeyConstant.CURRENT_DEVELOPER, developers.get(0));
        return developers.get(0);
    }

    @Override
    public Developer createAccount(CreateAccount createAccount) {
        return null;
    }

    @Override
    public boolean existNameOrEmail(String key) {
        if (ParamInspect.unValidString(key)) {
            CommonErrorEnum.PARAM_ERROR.throwThis();
        }
        List<Developer> developers = developerService.matchTheWholeNameOrEmail(key);
        return developers.size() > 0;
    }
}
