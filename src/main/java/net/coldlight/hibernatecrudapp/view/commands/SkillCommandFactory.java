package net.coldlight.hibernatecrudapp.view.commands;


import net.coldlight.hibernatecrudapp.view.SkillView;

public class SkillCommandFactory implements CommandFactory {
    SkillView skillView = new SkillView();

    @Override
    public void getCommand(String command) {
        if (command.equalsIgnoreCase("create skill")) {
            skillView.createSkill();
        } else if (command.equalsIgnoreCase("show skills")) {
            skillView.getAllSkills();
        } else if (command.equalsIgnoreCase("get skill by id")) {
            skillView.getSkillByID();
        } else if (command.equalsIgnoreCase("update skill")) {
            skillView.updateSkill();
        } else if (command.equalsIgnoreCase("delete skill")) {
            skillView.deleteSkill();
        }
    }
}
