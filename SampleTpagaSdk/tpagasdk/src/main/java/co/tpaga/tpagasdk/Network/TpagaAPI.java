package co.tpaga.tpagasdk.Network;

import java.io.IOException;
import java.lang.annotation.Annotation;

import co.tpaga.tpagasdk.BuildConfig;
import co.tpaga.tpagasdk.Entities.CreditCardResponseTpaga;
import co.tpaga.tpagasdk.Entities.CreditCardTpaga;
import co.tpaga.tpagasdk.GenericResponse;
import co.tpaga.tpagasdk.StatusResponse;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by TPaga on 8/30/16.
 */
public class TpagaAPI {

    public static final int SANDBOX = 1000;
    public static final int PRODUCTION = 2000;
    private static final String SANDBOX_TPAGA_API_HOST = "https://sandbox.tpaga.co/api/";
    private static final String PRODUCTION_TPAGA_API_HOST = "https://api.tpaga.co/api/";

    private static ManageCreditCard mManageCreditCard;
    private String tpagaPublicApiKey;
    private int enviroment;

    private TpagaAPI(String tpagaPublicApiKey, int enviroment) {
        this.tpagaPublicApiKey = tpagaPublicApiKey;
        this.enviroment = enviroment;
        mManageCreditCard = getAuthAdapter().create(ManageCreditCard.class);
    }

    public static TpagaAPI initialize(String tpagaPublicApiKey, int enviroment) {
        return new TpagaAPI(tpagaPublicApiKey, enviroment);
    }

    private Retrofit getAuthAdapter() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new TPagaInterceptor(tpagaPublicApiKey));

        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl((enviroment == PRODUCTION) ? PRODUCTION_TPAGA_API_HOST : SANDBOX_TPAGA_API_HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public Call<CreditCardResponseTpaga> addCreditCard(CreditCardTpaga creditCard) {
        return mManageCreditCard.addCreditCardPost(creditCard);
    }

    public GenericResponse errorResponse(ResponseBody errorBody) {
        try {
            CreditCardResponseTpaga.Error error = (CreditCardResponseTpaga.Error) getAuthAdapter().responseBodyConverter(CreditCardResponseTpaga.Error.class, new Annotation[0]).convert(errorBody);
            return GenericResponse.create(StatusResponse.create(error));
        } catch (IOException e) {
            throw new IllegalArgumentException("error body can't be serialized");
        }
    }


    private interface ManageCreditCard {
        @POST("tokenize/credit_card")
        Call<CreditCardResponseTpaga> addCreditCardPost(@Body CreditCardTpaga appInfo);
    }
}
