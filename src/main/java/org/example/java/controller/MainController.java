package org.example.java.controller;

import org.example.java.container.ComponentContainer;
import org.example.java.db.DataBase;
import org.example.java.dto.Profile;
import org.example.java.enums.Role;
import org.example.java.repository.ProfileRepository;


public class MainController {
    public void started() {
        ProfileRepository.intAdmin();
       // ProfileRepository.addCompanyCard();

        DataBase.init();
        System.out.println(" *** Asosiy menyu *** ");
        while (true) {
            menu();
            int action = ComponentContainer.getAction();
            switch (action) {
                case 1 -> {
                    kirish();
                }
                case 2 -> {
                    registratsiya();
                }
                case 0 -> {
                    System.exit(0);
                }
            }

        }
    }

    private void registratsiya() {
        System.out.print("Ismizi kiriting>> ");
        String name = ComponentContainer.stringScanner.next();
        System.out.print("Familiyangizni kiriting >> ");
        String surname = ComponentContainer.stringScanner.next();
        System.out.print("Telefoningizni kiriting >> ");
        String phone = ComponentContainer.stringScanner.next();
        System.out.print("Parol kiriting ( parol > 8) >> ");
        String password = ComponentContainer.stringScanner.next();
        Profile profile = new Profile();
        profile.setPassword(password);
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        ComponentContainer.profileService.registration(profile);

    }

    private void kirish() {
        System.out.print("Telefoningizni kiriting :");
        String phone = ComponentContainer.stringScanner.next();
        System.out.print("Parol kiriting:");
        String password = ComponentContainer.stringScanner.next();
        Profile profile = ComponentContainer.profileService.login(phone, password);
        if (profile == null) {
            System.out.println("parol yoki or telefon number xato");
            return;
        }
        if (profile.getRole().equals(Role.USER)) {
            ComponentContainer.profileController.start(profile);
        } else if (profile.getRole().equals(Role.ADMIN)) {
            ComponentContainer.adminController.start(profile);
        }
    }


    private void menu() {
        System.out.println("   1.Kirish");
        System.out.println("   2.Registratsiya");
        System.out.println("   0.Chiqish");
    }
}
