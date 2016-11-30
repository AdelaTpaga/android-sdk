package co.tpaga.tpagasdk.FragmentCreditCard;

import co.tpaga.tpagasdk.Entities.CreditCardTpaga;
import co.tpaga.tpagasdk.Entities.CreditCardWallet;
import co.tpaga.tpagasdk.GenericResponse;
import co.tpaga.tpagasdk.StatusResponse;

public interface AddCreditCardView {

    interface UserActionsListener {
        void onResponseSuccessfulOfAddCreditCard(CreditCardWallet creditCardWallet);

        void showError(Throwable t);

        void showError(GenericResponse genericResponse);

        void showToastError(StatusResponse response);

        CreditCardTpaga getCreditCard();
    }

    interface View {

        boolean validateFieldsCC();

        void showToastMsg();

        void clear();
        
        CreditCardTpaga getCC();
    }


}
