package org.example.java.service;

import org.example.java.container.ComponentContainer;
import org.example.java.dto.Terminal;
import org.example.java.enums.TerminalStatus;

public class TerminalService {

    public void createTerminal(String number, String address) {
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(number);
        ////checking
        if (terminal!=null){
            System.out.println("This terminal already exists");
            return;
        }
        ComponentContainer.terminalRepository.createTerminal(number, address);
    }

    public void updateTerminalByNumber(String number, String address) {
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(number);
        if (terminal == null||!terminal.getAddress().equals(address)) {
            System.out.println("Card not found");
        } else {
            System.out.print("Enter new TERMINAL number : ");
            String newTerminalNum = ComponentContainer.stringScanner.next();
            System.out.print("Enter new TERMINAL address : ");
            String new_address = ComponentContainer.stringScanner.next();
            Terminal terminal1 = ComponentContainer.terminalRepository.getTerminalByNumber(newTerminalNum);
            if (terminal1 == null) {
                ComponentContainer.terminalRepository.updateTerminalByNumber(terminal, newTerminalNum, new_address);
                System.out.println("Successfully");
            } else {
                System.out.println("This card already exists");
            }
        }
    }

    public void changeTerminalStatus(String numCard, String address) {
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(numCard);
        if (terminal == null||!terminal.getAddress().equals(address)) {
            System.out.println("Card not found");
        } else {
            if (terminal.getStatus().equals(TerminalStatus.BLOCK)) {
                terminal.setStatus(TerminalStatus.ACTIVE);
            } else if (terminal.getStatus().equals(TerminalStatus.ACTIVE)) {
                terminal.setStatus(TerminalStatus.BLOCK);
            }
            ComponentContainer.terminalRepository.updateTerminalStatus(terminal);

        }
    }

    public void deleteTerminal(String numTerminal, String address) {
        Terminal terminal = ComponentContainer.terminalRepository.getTerminalByNumber(numTerminal);
        if (terminal == null || (!terminal.getAddress().equals(address))) {
            System.out.println("Terminal not found");
            return;
        }
        ComponentContainer.terminalRepository.deleteTerminal(terminal);
    }
}
