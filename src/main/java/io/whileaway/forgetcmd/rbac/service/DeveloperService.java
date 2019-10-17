package io.whileaway.forgetcmd.rbac.service;

import io.whileaway.forgetcmd.rbac.entites.Developer;

import java.util.List;

public interface DeveloperService {

    List<Developer> matchTheWholeNameOrEmail(String key);

    Developer registerDeveloper(Developer developer);
}
