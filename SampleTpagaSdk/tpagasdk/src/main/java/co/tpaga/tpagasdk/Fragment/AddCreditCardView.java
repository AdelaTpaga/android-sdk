package co.tpaga.tpagasdk.Fragment;

import co.tpaga.tpagasdk.Entities.CreditCardTpaga;
import co.tpaga.tpagasdk.Entities.CreditCardWallet;
import co.tpaga.tpagasdk.GenericResponse;
import co.tpaga.tpagasdk.StatusResponse;

public interface AddCreditCardView {

    void onResponseSuccessfulOfAddCreditCard(CreditCardWallet creditCardWallet);

    void showError(Throwable t);

    void showError(GenericResponse genericResponse);

    void showToastError(StatusResponse response);

    CreditCardTpaga getCreditCard();
}
