package br.dev.brum.poc.jpos.model.request;


import br.dev.brum.poc.jpos.entity.Card;
import br.dev.brum.poc.jpos.entity.Consumer;
import br.dev.brum.poc.jpos.entity.Merchant;
import br.dev.brum.poc.jpos.entity.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {

    private Payment payment;

    private Card card;

    private Merchant merchant;

    private Consumer consumer;

}
