package net.coldlight.hibernatecrudapp.controller;

import net.coldlight.hibernatecrudapp.model.Developer;
import net.coldlight.hibernatecrudapp.repository.repoImpl.DeveloperRepositoryImpl;
import net.coldlight.hibernatecrudapp.service.DeveloperService;

import java.util.List;

public class DeveloperController {
    private final DeveloperService developerService = new DeveloperService(new DeveloperRepositoryImpl());


    public Developer createDeveloper(String firstName, String lastName) {
        return developerService.createDeveloper(firstName, lastName);
    }

    public List<Developer> getAllDevelopers() {
        return developerService.getAllDevelopers();
    }

    public Developer getDeveloperByID(Long id) {
        return developerService.getDeveloperByID(id);
    }

    public Developer updateDeveloper(Long id, String newFirstName, String newLastName) {
        return developerService.updateDeveloper(id, newFirstName, newLastName);
    }

    public void deleteDeveloper(Long id) {
        developerService.deleteDeveloperByID(id);
    }

}

