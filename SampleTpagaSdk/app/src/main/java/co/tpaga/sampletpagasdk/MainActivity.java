package co.tpaga.sampletpagasdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button withUI = (Button) findViewById(R.id.button_with_ui);
        withUI.setOnClickListener(this);
        Button withoutUI = (Button) findViewById(R.id.button_without_ui);
        withoutUI.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_with_ui:
                startActivity(new Intent(this, AddCreditCardWithFragmentActivity.class));
                break;
            case R.id.button_without_ui:
                startActivity(new Intent(this, AddCreditCardActivity.class));
                break;
        }
    }
}
