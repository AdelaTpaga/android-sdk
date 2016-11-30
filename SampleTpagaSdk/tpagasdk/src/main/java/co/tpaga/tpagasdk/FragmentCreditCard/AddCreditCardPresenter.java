package co.tpaga.tpagasdk.FragmentCreditCard;

import android.support.annotation.NonNull;

import co.tpaga.tpagasdk.Entities.CreditCardResponseTpaga;
import co.tpaga.tpagasdk.GenericResponse;
import co.tpaga.tpagasdk.Network.TpagaAPI;
import co.tpaga.tpagasdk.R;
import co.tpaga.tpagasdk.StatusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddCreditCardPresenter {

    private AddCreditCardView.UserActionsListener userActionsListener;
    @NonNull
    private AddCreditCardView.View view;
    @NonNull
    private TpagaAPI tpagaApi;

    public AddCreditCardPresenter(AddCreditCardView.UserActionsListener userActionsListener, @NonNull TpagaAPI tpagaApi) {
        this.userActionsListener = userActionsListener;
        this.tpagaApi = checkNotNull(tpagaApi);
    }

    public void addCreditCardView(@NonNull AddCreditCardView.View view) {
        this.view = checkNotNull(view);
    }

    public void onClickAddCC() {
        if (!view.validateFieldsCC()) {
            view.showToastMsg();
            return;
        }
        tokenizeCreditCard();
    }

    public void tokenizeCreditCard() {
        tpagaApi.addCreditCard(userActionsListener.getCreditCard()).enqueue(new Callback<CreditCardResponseTpaga>() {
            @Override
            public void onResponse(Call<CreditCardResponseTpaga> call, Response<CreditCardResponseTpaga> response) {
                if (response.isSuccessful()) {
                    userActionsListener.onResponseSuccessfulOfAddCreditCard(response.body().toCreditCardWallet());
                    return;
                }
                if (response.code() >= 500) {
                    userActionsListener.showError(GenericResponse.create(StatusResponse.create(R.string.error_to_connect_with_server)));
                } else {
                    userActionsListener.showError(GenericResponse.create(StatusResponse.create(R.string.error_to_add_cc)));
                }
            }

            @Override
            public void onFailure(Call<CreditCardResponseTpaga> call, Throwable t) {
                userActionsListener.showError(t);
            }
        });
    }
}
