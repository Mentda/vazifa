package org.example.java.dto;

import org.example.java.enums.TerminalStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Terminal {
    private Integer id;
    private String number;
    private String address;
    private LocalDateTime localDateTime;
    private TerminalStatus status;
}
