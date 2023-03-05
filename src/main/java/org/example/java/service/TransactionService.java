package org.example.java.service;

import org.example.java.container.ComponentContainer;
import org.example.java.dto.Card;
import org.example.java.dto.Profile;
import org.example.java.dto.Terminal;
import org.example.java.dto.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionService {
    public void transaction(Profile profile, String numCard, String terminalNumber) {
        Card card = ComponentContainer.cardRepository.getCard(numCard);
        if (card == null || !card.getProfile_id().equals(profile.getId())) {
            System.out.println("This card isn't your");
            return;
        }
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(terminalNumber);
        if (terminal == null) {
            System.out.println("Terminal not found");
            return;
        }
        card.setAmount(card.getAmount() - ComponentContainer.amountPrice);
        ComponentContainer.cardRepository.updateCardBalance(card);

        Card companyCard = ComponentContainer.cardRepository.getCard(ComponentContainer.companyCard);
        companyCard.setAmount(companyCard.getAmount() + ComponentContainer.amountPrice);
        ComponentContainer.cardRepository.updateCardBalance(companyCard);

        ComponentContainer.transactionRepository.transaction(profile, card, terminal);


    }

    public List<Transaction> paymentDay(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return ComponentContainer.transactionRepository.paymentDay(localDate);
    }

    public List<Transaction> intermediatePayment(String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
        LocalDate dateFrom = LocalDate.parse(fromDate, formatter);
        LocalDate dateTo = LocalDate.parse(toDate, formatter);
        return ComponentContainer.transactionRepository.intermediatePayment(dateFrom, dateTo);
    }

    public List<Transaction> transactionByTerminal(String terminalNum) {
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(terminalNum);
        if (terminal == null) {
            return null;
        }
        return  ComponentContainer.transactionRepository.transactionByTerminal(terminal);

    }

    public List<Transaction> transactionByCard(String cardNum) {
        Card card = ComponentContainer.cardRepository.getCard(cardNum);
        if (card == null) {
            return null;
        }
        return  ComponentContainer.transactionRepository.transactionByCard(card);
    }
}
