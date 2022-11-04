package net.coldlight.hibernatecrudapp.service;


import net.coldlight.hibernatecrudapp.model.Speciality;
import net.coldlight.hibernatecrudapp.repository.SpecialityRepository;

import java.util.List;

public class SpecialityService {
    private final SpecialityRepository specialityRepository;


    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public Speciality createSpeciality(String name) {
        Speciality speciality = Speciality.builder()
                .specialityName(name)
                .build();
        return specialityRepository.save(speciality);
    }

    public List<Speciality> getAllSpecialities() {
        return specialityRepository.getAll();
    }

    public Speciality getSpecialityByID(Long id) {
        return specialityRepository.getByID(id);
    }

    public Speciality updateSpeciality(Long id, String newSpeciality) {
        List<Speciality> allSpecialities = getAllSpecialities();
        Speciality speciality = allSpecialities.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if (speciality != null) {
            speciality.setSpecialityName(newSpeciality);
            return specialityRepository.update(speciality);
        }
        throw new RuntimeException("Speciality with ID = " + id + "not found");
    }

    public void deleteSpeciality(Long id) {
        Speciality speciality = specialityRepository.getByID(id);
        if (speciality == null) {
            throw new RuntimeException("Developer with ID = " + id + "not found");
        }
        specialityRepository.delete(speciality);
    }
}
