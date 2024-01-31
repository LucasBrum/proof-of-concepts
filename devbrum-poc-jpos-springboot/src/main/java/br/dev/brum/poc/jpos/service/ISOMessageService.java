package br.dev.brum.poc.jpos.service;

import br.dev.brum.poc.jpos.model.request.PaymentRequest;
import org.jpos.iso.ISOException;
import org.jpos.util.NameRegistrar;

import java.io.IOException;

public interface ISOMessageService {

    void isoMessageRequestAuthorization0200(PaymentRequest paymentRequest) throws ISOException, NameRegistrar.NotFoundException, IOException;
}
