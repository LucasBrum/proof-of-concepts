package br.dev.brum.poc.jpos.service.impl;

import br.dev.brum.poc.jpos.listener.RequestListener;
import br.dev.brum.poc.jpos.model.request.PaymentRequest;
import br.dev.brum.poc.jpos.service.ISOMessageService;
import org.jpos.iso.*;
import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.jpos.util.NameRegistrar.*;

@Service
public class ISOMessageServiceImpl implements ISOMessageService {

    private Logger log = LoggerFactory.getLogger(ISOMessageServiceImpl.class);

    @Autowired
    private QMUX qmux;

    @Autowired
    private ISOSource isoSource;

    private SimpleDateFormat dateFormatterBit7 = new SimpleDateFormat("MMddHHmmss");
    private SimpleDateFormat hourMinuteformatterBit12 = new SimpleDateFormat("HHmmss");
    private SimpleDateFormat localDateformatterBit13 = new SimpleDateFormat("MMdd");

    @Override
    public void isoMessageRequestAuthorization0200(PaymentRequest paymentRequest) throws NotFoundException, ISOException, IOException {

        log.info("Initiating Request Authorization - 0200");

        ISOMsg isoMsgRequest = new ISOMsg("0200");

        isoMsgRequest.set(4, paymentRequest.getPayment().getAmount().setScale(0).toString());
        isoMsgRequest.set(7, dateFormatterBit7.format(new Date()));
        isoMsgRequest.set(11, "000123");
        isoMsgRequest.set(12, hourMinuteformatterBit12.format(new Date()));
        isoMsgRequest.set(13, localDateformatterBit13.format(new Date()));
        isoMsgRequest.set(14, getCardValidation(paymentRequest));
        isoMsgRequest.set(18, "5411"); // Entender
        isoMsgRequest.set(19, "076");
        isoMsgRequest.set(22, "831"); // Entender
        isoMsgRequest.set(32, "00000269612");
        isoMsgRequest.set(33, "00000281120");
        isoMsgRequest.set(41, "00000000");
        isoMsgRequest.set(42, "000000001483136");
        isoMsgRequest.set(43, "mcdonalds           sao paulo   BR02544032");
        isoMsgRequest.set(48, "986");
        isoMsgRequest.set(60, "00521005");
        isoMsgRequest.set(127, "009123456789");

        ISOMsg response = (ISOMsg) isoMsgRequest.clone();
        response.setMTI("0210");
        isoSource.send(response);

        log.info("RespMsg {}", new String(response.pack()));

    }

    private static String getCardValidation(PaymentRequest paymentRequest) {
        return paymentRequest.getCard().getToken().getExpiryMonth() + paymentRequest.getCard().getToken().getExpiryYear();
    }



}
