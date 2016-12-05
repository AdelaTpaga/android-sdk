package co.tpaga.sampletpagasdk;


import android.app.Application;

import co.tpaga.tpagasdk.Network.TpagaAPI;
import co.tpaga.tpagasdk.Tpaga;

public class SampleApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Tpaga.initialize(getResources().getString(R.string.tpaga_public_api_key), TpagaAPI.SANDBOX);
    }
}
