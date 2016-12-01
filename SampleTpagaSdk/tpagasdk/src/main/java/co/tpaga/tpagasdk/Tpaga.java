package co.tpaga.tpagasdk;


import android.app.Activity;
import android.content.Intent;

import co.tpaga.tpagasdk.Entities.CreditCardTpaga;
import co.tpaga.tpagasdk.Network.TpagaAPI;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class Tpaga {

    public static TpagaAPI tpagaApi;

    private Tpaga(String tpagaPublicApiKey, int enviroment) {
        tpagaApi = TpagaAPI.initialize(tpagaPublicApiKey, enviroment);

    }

    public static Tpaga initialize(String tpagaPublicApiKey, int enviroment) {
        return new Tpaga(tpagaPublicApiKey, enviroment);
    }

    /*request code for scan card*/
    public static final int SCAN_CREDIT_CARD = 1126;

    public static void scanCard(Activity context) {
        Intent scanIntent = new Intent(context, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, true);
        context.startActivityForResult(scanIntent, SCAN_CREDIT_CARD);
    }

    public static boolean validateCreditCard(CreditCardTpaga creditCard) {
        return TpagaTools.isValidCardNumber(creditCard.primaryAccountNumber.replaceAll("\\s+", ""))
                && TpagaTools.isValidExpirationDate(creditCard.expirationYear, creditCard.expirationMonth)
                && !creditCard.cvc.isEmpty()
                && TpagaTools.isNameValid(creditCard.cardHolderName);
    }

    public static CreditCardTpaga onActivityResultScanCreditCard(Intent data) {
        try {
            if (data != null || data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                CreditCardTpaga creditCardTpaga = CreditCardTpaga.create(
                        scanResult.getFormattedCardNumber(),
                        (scanResult.isExpiryValid()) ? scanResult.expiryYear + "" : "",
                        (scanResult.isExpiryValid()) ? scanResult.expiryMonth + "" : "",
                        (scanResult.cvv != null) ? scanResult.cvv : "",
                        "");

                return creditCardTpaga;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
