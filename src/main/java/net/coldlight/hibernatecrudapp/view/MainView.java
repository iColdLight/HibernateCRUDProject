package net.coldlight.hibernatecrudapp.view;


import net.coldlight.hibernatecrudapp.view.commands.MainCommandFactory;

import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);
    private final DeveloperView developerView = new DeveloperView();
    private final SkillView skillView = new SkillView();
    private final SpecialityView specialityView = new SpecialityView();
    private final MainCommandFactory mainCommandFactory = new MainCommandFactory();
    public static boolean isInterrupted = false;


    public void start() {
        while (!isInterrupted) {
            System.out.println("Select which entity you want to work with: developer, skill, speciality");
            System.out.println("Enter 'exit' to abort");
            mainCommandFactory.getCommand(scanner.nextLine());
        }
    }
}
