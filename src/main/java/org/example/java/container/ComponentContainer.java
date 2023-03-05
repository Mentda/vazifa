package org.example.java.container;

import org.example.java.controller.AdminController;
import org.example.java.controller.ProfileController;
import org.example.java.repository.CardRepository;
import org.example.java.repository.ProfileRepository;
import org.example.java.repository.TerminalRepository;
import org.example.java.repository.TransactionRepository;
import org.example.java.service.CardService;
import org.example.java.service.ProfileService;
import org.example.java.service.TerminalService;
import org.example.java.service.TransactionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ComponentContainer {
    public static Double amountPrice=1400.00;
    public static String companyCard= 7777+"";
    public static TerminalRepository terminalRepository;
    public static TransactionService transactionService;
    public static TransactionRepository transactionRepository;
    public static ProfileRepository profileRepository;
    public static TerminalService terminalService ;
    public static Scanner intScanner = new Scanner(System.in);
    public static Scanner stringScanner = new Scanner(System.in);
    public static ProfileService profileService;
    public static ProfileController profileController ;
    public static AdminController adminController ;
    public static CardRepository cardRepository ;
    public static CardService cardService ;
    static {
        transactionRepository = new TransactionRepository();
        transactionService = new TransactionService();
        profileRepository = new ProfileRepository();
        terminalService = new TerminalService();
        terminalRepository = new TerminalRepository();
        cardService = new CardService();
        cardRepository = new CardRepository();
        profileService = new ProfileService();
        profileController = new ProfileController();
        adminController = new AdminController();
    }
    public static int getAction() {
        System.out.print("Bajarmoqchi bo'lgan amalni tanlang >> ");
        /*agar menuda raqam tanlamasdan harf yoki belgi tanlasa exception tashlaydi*/
        try {
            return ComponentContainer.intScanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Xato ,Iltimos raqam tanlang! ");
        }
        return 0;
    }

}
