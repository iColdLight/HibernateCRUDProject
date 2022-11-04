package net.coldlight.hibernatecrudapp.view.commands;


import net.coldlight.hibernatecrudapp.view.SpecialityView;

public class SpecialityCommandFactory implements CommandFactory {
    SpecialityView specialityView = new SpecialityView();

    @Override
    public void getCommand(String command) {
        if (command.equalsIgnoreCase("create speciality")) {
            specialityView.createSpeciality();
        } else if (command.equalsIgnoreCase("show specialities")) {
            specialityView.getAllSpecialities();
        } else if (command.equalsIgnoreCase("get speciality by id")) {
            specialityView.getSpecialityByID();
        } else if (command.equalsIgnoreCase("update speciality")) {
            specialityView.updateSpeciality();
        } else if (command.equalsIgnoreCase("delete speciality")) {
            specialityView.deleteSpeciality();
        }
    }
}
