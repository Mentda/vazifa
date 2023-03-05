package org.example.java.controller;

import org.example.java.container.ComponentContainer;
import org.example.java.dto.Card;
import org.example.java.dto.Profile;
import org.example.java.dto.Transaction;

import java.util.List;

public class ProfileController {
    public void start(Profile profile) {
        boolean b = true;
        System.out.println(" ***  Foydalanuvchi menyusi  *** ");
        while (b) {
            userMenu();
            int action = ComponentContainer.getAction();
            switch (action) {
                case 1 -> {
                    addCard(profile);
                }
                case 2 -> {
                    cardList(profile);
                }
                case 3 -> {
                    changeCardStatus(profile);
                }
                case 4 -> {
                    deleteCardUser(profile);
                }
                case 5 -> {
                    refill(profile);
                }
                case 6 -> {
                    transaction(profile);
                }
                case 7 -> {
                    transactionList(profile);
                }
                case 0 -> {
                    b = false;
                }


            }
        }

    }

    private void transactionList(Profile profile) {
        List<Transaction> transactionList = ComponentContainer.transactionRepository.transactionList(profile);
        transactionList.stream().forEach(transaction -> System.out.println(transaction));

    }

    private void transaction(Profile profile) {
        System.out.print("Enter Card Number:");
        String numCard = ComponentContainer.stringScanner.next();
        System.out.print("Enter terminal number: ");
        String terminalNumber = ComponentContainer.stringScanner.next();
        ComponentContainer.transactionService.transaction(profile, numCard, terminalNumber);


    }

    private void refill(Profile profile) {
        System.out.print("Enter Card Number:");
        String numCard = ComponentContainer.stringScanner.next();
        System.out.print("Balance: ");
        Double amount = ComponentContainer.intScanner.nextDouble();
        ComponentContainer.cardService.refill(profile, numCard, amount);
    }

    private void deleteCardUser(Profile profile) {
        System.out.print("Enter Card Number:");
        String numCard = ComponentContainer.stringScanner.next();
        System.out.print("Enter card data : ");
        String cardExp_date = ComponentContainer.stringScanner.next();
        ComponentContainer.cardService.deleteCardUser(profile, numCard, cardExp_date);
    }

    private void changeCardStatus(Profile profile) {
        System.out.print("Enter Card Number:");
        String numCard = ComponentContainer.stringScanner.next();
        System.out.print("Enter card data : ");
        String cardExp_date = ComponentContainer.stringScanner.next();
        ComponentContainer.cardService.changeCardStatusUser(profile, numCard, cardExp_date);
    }

    private void cardList(Profile profile) {
        List<Card> cardList = ComponentContainer.cardService.cardListUser(profile);
        cardList.forEach(System.out::println);
    }

    private void addCard(Profile profile) {
        System.out.print("Enter Card Number:");
        String numCard = ComponentContainer.stringScanner.next();
        System.out.print("Enter card data : ");
        String cardExp_date = ComponentContainer.stringScanner.next();
        ComponentContainer.cardService.addCard(profile, numCard, cardExp_date);
    }

    private void userMenu() {
        System.out.print("1.Add card\n" +
                "2.Card List\n" +
                "3.Change Card Status\n" +
                "4.Delete Card\n" +
                "5.ReFill\n" +
                "6.Transaction\n" +
                "7.Make Payment\n" +
                "0.Exit\n");
    }
}
