package br.dev.brum.poc.jpos.controller;

import br.dev.brum.poc.jpos.model.request.PaymentRequest;
import br.dev.brum.poc.jpos.service.ISOMessageService;
import org.jpos.iso.ISOException;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.jpos.util.NameRegistrar.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private ISOMessageService isoMessageService;

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody PaymentRequest paymentRequest) throws ISOException, NotFoundException, IOException {
        Map<String, String> result = new HashMap<>();

        isoMessageService.isoMessageRequestAuthorization0200(paymentRequest);

        return null;
    }

    @GetMapping
    public ResponseEntity<String> findAll() {

        return ResponseEntity.ok().body("Teste Teste!!!");
    }


}
