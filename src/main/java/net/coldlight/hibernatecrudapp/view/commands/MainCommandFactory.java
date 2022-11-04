package net.coldlight.hibernatecrudapp.view.commands;


import net.coldlight.hibernatecrudapp.view.MainView;

import java.util.Scanner;

public class MainCommandFactory implements CommandFactory {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void getCommand(String command) {
        if (command.equalsIgnoreCase("developer")) {
            DeveloperCommandFactory developerCommandFactory = new DeveloperCommandFactory();
            System.out.println("Select a command to proceed: create developer, show developers, get developer by id, " +
                    "update developer, delete developer");
            String operation = scanner.nextLine();
            developerCommandFactory.getCommand(operation);
        } else if (command.equalsIgnoreCase("speciality")) {
            SpecialityCommandFactory specialityCommandFactory = new SpecialityCommandFactory();
            System.out.println("Select a command to proceed: create speciality, show specialities, get speciality by id, " +
                    "update speciality, delete speciality");
            String operation = scanner.nextLine();
            specialityCommandFactory.getCommand(operation);
        } else if (command.equalsIgnoreCase("skill")) {
            SkillCommandFactory skillCommandFactory = new SkillCommandFactory();
            System.out.println("Select a command to proceed: create skill, show skills, get skill by id, " +
                    "update skill, delete skill");
            String operation = scanner.nextLine();
            skillCommandFactory.getCommand(operation);
        } else if (command.equalsIgnoreCase("exit")) {
            MainView.isInterrupted = true;
        }
    }
}
