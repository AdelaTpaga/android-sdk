package co.tpaga.sampletpagasdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import co.tpaga.tpagasdk.Fragment.AddCreditCardFragment;
import co.tpaga.tpagasdk.Fragment.AddCreditCardView;

public class AddCreditCardWithFragmentActivity extends AppCompatActivity implements AddCreditCardView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credit_card_with_fragment);

        showAddCreditCardFragment();

    }

    private AddCreditCardFragment mAddCreditCardFragment;

    public void showAddCreditCardFragment() {
        if (mAddCreditCardFragment == null) mAddCreditCardFragment = new AddCreditCardFragment();
        if (!mAddCreditCardFragment.isVisible())
            addFragment(mAddCreditCardFragment, AddCreditCardFragment.TAG, true);
    }

    public void addFragment(Fragment fragment, String fragmentTag, boolean toStack) {
        try {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_default, fragment, fragmentTag);
            if (toStack) ft.addToBackStack(fragmentTag);
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClickCreateAccount() {
        //Your code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAddCreditCardFragment.onActivityResult(requestCode, resultCode, data);
    }
}
