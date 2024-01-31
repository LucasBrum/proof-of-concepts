package br.dev.brum.poc.jpos.entity;

import lombok.Data;

@Data
public class Token {

    private String token;
    private String ownerName;
    private String cvv;
    private String expiryMonth;
    private String expiryYear;
}
