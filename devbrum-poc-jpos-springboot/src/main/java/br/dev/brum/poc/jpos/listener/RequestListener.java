package br.dev.brum.poc.jpos.listener;

import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestListener implements ISORequestListener {

    @Override
    public boolean process(ISOSource sender, ISOMsg request) {
        try {
            String mti = request.getMTI();
            if ("0800".equals(mti)) {
                ISOMsg response = (ISOMsg) request.clone();
                response.setMTI("0810");
                response.set(39, "00");
                sender.send(response);
                return true;
            }

            if ("0100".equals(mti)) { //MTI 0100 is an authorization request

                String processingCode = request.getString(3);
                if (processingCode.startsWith("002000")) {
                    ISOMsg isoMessage = (ISOMsg) request.clone();
                    isoMessage.setMTI("0110"); // MTI 0210 is an Issuer Response to Financial Request

                    sender.send(isoMessage);
                    //System.out.println("ISO message : " + new String(isoMessage.pack()));
                    return true;
                }
            }

            if ("0200".equals(mti)) { // MTI 200 is an Acquirer Financial Request

                // Processing Code 0200 trasaction types: Sale, Cash, Credit Voucher and Void
                String processingCode = request.getString(3);

                //TODO : handle if processing code is empty or null

                if (processingCode.startsWith("002000")) {
                    ISOMsg response = (ISOMsg) request.clone();
                    response.setMTI("0210"); // MTI 0210 is an Issuer Response to Financial Request

                    sender.send(response);
                    System.out.println("ISO message : " + new String(response.pack()));
                    return true;
                }

                if (processingCode.startsWith("34")) {
                    ISOMsg response = (ISOMsg) request.clone();
                    response.setMTI("0210");

                    String accountNumber = request.getString(102);

                    sender.send(response);
                    return true;
                }

                return false;
            }

            return false;
        } catch (Exception ex) {
            Logger.getLogger(RequestListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
