package io.whileaway.forgetcmd.rbac.service.impl;

import io.whileaway.forgetcmd.rbac.entites.Developer;
import io.whileaway.forgetcmd.rbac.repository.DeveloperRepository;
import io.whileaway.forgetcmd.rbac.service.DeveloperService;
import io.whileaway.forgetcmd.rbac.specs.DeveloperSpec;
import io.whileaway.forgetcmd.rbac.Crypto;
import io.whileaway.forgetcmd.util.spec.QueryListBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository repository;

    public DeveloperServiceImpl(DeveloperRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Developer> matchTheWholeNameOrEmail(String key) {
        return new QueryListBuilder<Developer>()
                .appendCondition(DeveloperSpec.matchCreateName(() -> key))
                .appendCondition(DeveloperSpec::normal)
                .findFrom(repository::findAll)
                .primitive();
    }

    @Override
    public Developer registerDeveloper(Developer developer) {
        // TODO 格式验证
        Crypto.cryptoDeveloperPass(developer);
        return repository.save(developer);
    }
}
