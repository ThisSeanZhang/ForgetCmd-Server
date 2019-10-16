package io.whileaway.forgetcmd.rbac.task;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.request.CreateAccount;
import io.whileaway.forgetcmd.rbac.request.CreateSession;

public interface RBACTask {

    Developer createSession(CreateSession createSession);

    Developer createAccount(CreateAccount createAccount);

    boolean existNameOrEmail(String key);
}
