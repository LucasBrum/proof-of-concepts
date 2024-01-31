package br.dev.brum.poc.jpos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String captureType;
    private String currency;
    private BigDecimal amount;

}


