package net.coldlight.hibernatecrudapp.controller;

import net.coldlight.hibernatecrudapp.model.Speciality;
import net.coldlight.hibernatecrudapp.repository.repoImpl.SpecialityRepositoryImpl;
import net.coldlight.hibernatecrudapp.service.SpecialityService;

import java.util.List;

public class SpecialityController {
    private final SpecialityService specialityService = new SpecialityService(new SpecialityRepositoryImpl());


    public Speciality createSpeciality(String name) {
        return specialityService.createSpeciality(name);
    }

    public List<Speciality> getAllSpecialities() {
        return specialityService.getAllSpecialities();
    }

    public Speciality getSpecialityByID(Long id) {
        return specialityService.getSpecialityByID(id);
    }

    public Speciality updateSpeciality(Long id, String newSpeciality) {
        return specialityService.updateSpeciality(id, newSpeciality);
    }

    public void deleteSpeciality(Long id) {
        specialityService.deleteSpeciality(id);
    }


}

