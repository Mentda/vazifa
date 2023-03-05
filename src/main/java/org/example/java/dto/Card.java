package org.example.java.dto;

import lombok.*;
import org.example.java.enums.CardStatus;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Integer id;
    private String number;
    private String exp_date;
    private Double amount;
    private Integer profile_id;
    private LocalDateTime created_date;
    private CardStatus status;
    private String phone;

}
