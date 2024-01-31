package br.dev.brum.poc.jpos.entity;

import lombok.Data;

@Data
public class Consumer {
    private String documentType;
    private String documentNumber;
}
