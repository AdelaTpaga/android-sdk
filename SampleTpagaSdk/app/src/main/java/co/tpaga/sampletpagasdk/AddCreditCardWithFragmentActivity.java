package co.tpaga.sampletpagasdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import co.tpaga.tpagasdk.Entities.CreditCardTpaga;
import co.tpaga.tpagasdk.Entities.CreditCardWallet;
import co.tpaga.tpagasdk.Fragment.AddCreditCardFragment;
import co.tpaga.tpagasdk.Fragment.AddCreditCardView;
import co.tpaga.tpagasdk.GenericResponse;
import co.tpaga.tpagasdk.StatusResponse;

public class AddCreditCardWithFragmentActivity extends AppCompatActivity implements AddCreditCardView {

    private AddCreditCardFragment mAddCreditCardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card_with_fragment);
        showAddCreditCardFragment();
    }

    public void showAddCreditCardFragment() {
        if (mAddCreditCardFragment == null) mAddCreditCardFragment = new AddCreditCardFragment();
        if (!mAddCreditCardFragment.isVisible())
            addFragment(mAddCreditCardFragment, AddCreditCardFragment.TAG);
    }

    public void addFragment(Fragment fragment, String fragmentTag) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_default, fragment, fragmentTag);
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseSuccessfulOfAddCreditCard(CreditCardWallet creditCardWallet) {
        mAddCreditCardFragment.clear();
        Toast.makeText(this, "credit card " + creditCardWallet.tempCcToken, Toast.LENGTH_LONG).show();
        /**
         * you must send the card token to your server to use in payments
         */
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, "credit card Throwable error", Toast.LENGTH_LONG).show();
        t.printStackTrace();
    }

    @Override
    public void showError(GenericResponse genericResponse) {
        showToastError(genericResponse.status);
    }

    @Override
    public void showToastError(StatusResponse response) {
        if (response != null && !response.responseMessage.isEmpty()) {
            Toast.makeText(this, response.responseMessage, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public CreditCardTpaga getCreditCard() {
        return mAddCreditCardFragment.getCC();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddCreditCardFragment.onActivityResult(requestCode, resultCode, data);
    }
}
