package io.whileaway.forgetcmd.commit.task;

import io.whileaway.forgetcmd.cmd.entities.Command;
import io.whileaway.forgetcmd.cmd.request.SearchCmdRequest;
import io.whileaway.forgetcmd.commit.service.AdminService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminTaskImpl implements AdminTask {

    private final AdminService service;

    public AdminTaskImpl(AdminService service) {
        this.service = service;
    }

    @Override
    public List<Command> searchCommand(SearchCmdRequest request) {
        return service.search(request);
    }
}
