package net.coldlight.hibernatecrudapp.view;


import net.coldlight.hibernatecrudapp.controller.SkillController;
import net.coldlight.hibernatecrudapp.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private final SkillController skillController = new SkillController();
    private final Scanner scanner = new Scanner(System.in);


    public void createSkill() {
        System.out.println("Enter skill name: ");
        String name = scanner.nextLine();
        Skill skill = skillController.createSkill(name);
        System.out.println("Created skill: " + skill);
    }

    public void getAllSkills() {
        List<Skill> allSkills = skillController.getAllSkills();
        for (Skill skill : allSkills) {
            System.out.println(skill);
        }
    }

    public void getSkillByID() {
        System.out.println("Enter skill ID: ");
        Long id = scanner.nextLong();
        System.out.println(skillController.getSkillByID(id));

    }

    public void updateSkill() {
        System.out.println("Enter skill id you want to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new skill name: ");
        String newSkill = scanner.nextLine();
        skillController.updateSkill(id, newSkill);
    }

    public void deleteSkill() {
        System.out.println("Enter skill ID you want to delete: ");
        Long skillID = scanner.nextLong();
        Skill skill = skillController.deleteSkill(skillID);
        System.out.println("Skill " + skill + " was deleted");
    }
}
