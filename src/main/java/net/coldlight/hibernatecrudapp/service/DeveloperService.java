package net.coldlight.hibernatecrudapp.service;


import net.coldlight.hibernatecrudapp.model.Developer;
import net.coldlight.hibernatecrudapp.repository.DeveloperRepository;

import java.util.List;

public class DeveloperService {
    private final DeveloperRepository developerRepository;


    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer createDeveloper(String firstName, String lastName) {
        Developer developer = Developer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        return developerRepository.save(developer);
    }

    public List<Developer> getAllDevelopers(){
        return developerRepository.getAll();
    }

    public Developer getDeveloperByID(Long id) {
        return developerRepository.getByID(id);
    }

    public Developer updateDeveloper(Long id, String newFirstName, String newLastName) {
        Developer developer = developerRepository.getByID(id);
        if (developer == null) {
            throw new RuntimeException("Developer with ID = " + id + "not found");
        }
        developer.setFirstName(newFirstName);
        developer.setLastName(newLastName);
        return developerRepository.update(developer);
    }

    public void deleteDeveloperByID(Long id) {
        Developer developer = developerRepository.getByID(id);
        if (developer == null) {
            throw new RuntimeException("Developer with ID = " + id + "not found");
        }
        developerRepository.delete(developer);
    }
}
