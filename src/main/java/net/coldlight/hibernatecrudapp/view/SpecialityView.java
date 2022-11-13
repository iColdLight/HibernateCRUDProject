package net.coldlight.hibernatecrudapp.view;


import net.coldlight.hibernatecrudapp.controller.SpecialityController;
import net.coldlight.hibernatecrudapp.model.Speciality;

import java.util.List;
import java.util.Scanner;

public class SpecialityView {
    private final SpecialityController specialityController = new SpecialityController();
    private final Scanner scanner = new Scanner(System.in);

    public void createSpeciality() {
        System.out.println("Enter speciality name: ");
        String name = scanner.nextLine();
        Speciality speciality = specialityController.createSpeciality(name);
        System.out.println("Created speciality: " + speciality);
    }

    public void getAllSpecialities() {
        List<Speciality> allSpecialities = specialityController.getAllSpecialities();
        for (Speciality speciality : allSpecialities) {
            System.out.println(speciality);
        }
    }

    public void getSpecialityByID() {
        System.out.println("Enter speciality ID: ");
        Long id = scanner.nextLong();
        System.out.println(specialityController.getSpecialityByID(id));

    }

    public void updateSpeciality() {
        System.out.println("Enter speciality ID you want to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new speciality name: ");
        String newSpeciality = scanner.nextLine();
        specialityController.updateSpeciality(id, newSpeciality);
    }

    public void deleteSpeciality() {
        System.out.println("Enter speciality ID you want to delete: ");
        Long id = scanner.nextLong();
        specialityController.deleteSpeciality(id);
    }
}
