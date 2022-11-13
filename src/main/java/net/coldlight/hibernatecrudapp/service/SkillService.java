package net.coldlight.hibernatecrudapp.service;


import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.repository.SkillRepository;

import java.util.List;

public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(String name) {
        Skill skill = Skill.builder()
                .skillName(name)
                .build();
        return skillRepository.save(skill);
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public Skill getSkillByID(Long id) {
        return skillRepository.getByID(id);
    }

    public Skill updateSkill(Long id, String newSkill) {
        List<Skill> allSkills = getAllSkills();
        Skill skill = allSkills.stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
        if (skill == null) {
            throw new RuntimeException("Skill with ID = " + id + "not found");
        }
        skill.setSkillName(newSkill);
        return skillRepository.update(skill);
    }

    public Skill deleteSkillByID(Long id) {
        Skill skill = skillRepository.getByID(id);
        if (skill == null) {
            throw new RuntimeException("Developer with ID = " + id + "not found");
        }
        skillRepository.delete(skill);
        return skill;
    }
}
