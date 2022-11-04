package net.coldlight.hibernatecrudapp.view.commands;


import net.coldlight.hibernatecrudapp.view.DeveloperView;

public class DeveloperCommandFactory implements CommandFactory {
    DeveloperView developerView = new DeveloperView();

    @Override
    public void getCommand(String command) {
        if (command.equalsIgnoreCase("create developer")) {
            developerView.createDeveloper();
        } else if (command.equalsIgnoreCase("show developers")) {
            developerView.getAllDevelopers();
        } else if (command.equalsIgnoreCase("get developer by id")) {
            developerView.getDeveloperByID();
        } else if (command.equalsIgnoreCase("update developer")) {
            developerView.updateDeveloper();
        } else if (command.equalsIgnoreCase("delete developer")) {
            developerView.deleteDeveloper();
        }
    }
}
