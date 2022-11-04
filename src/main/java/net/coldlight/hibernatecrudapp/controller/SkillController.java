package net.coldlight.hibernatecrudapp.controller;


import net.coldlight.hibernatecrudapp.model.Skill;
import net.coldlight.hibernatecrudapp.repository.repoImpl.SkillRepositoryImpl;
import net.coldlight.hibernatecrudapp.service.SkillService;

import java.util.List;

public class SkillController {

    private final SkillService skillService = new SkillService(new SkillRepositoryImpl());


    public Skill createSkill(String name) {
        return skillService.createSkill(name);
    }

    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    public Skill getSkillByID(Long id) {
        return skillService.getSkillByID(id);
    }

    public Skill updateSkill(Long id, String newSkill) {
        return skillService.updateSkill(id, newSkill);
    }

    public Skill deleteSkill(Long id) {
        return skillService.deleteSkillByID(id);
    }
}

