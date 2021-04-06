package Pay.Stripe;

import com.stripe.net.RequestOptions;

public class StripeConfig {

    public static String USD = "usd";

    static RequestOptions requestOptions;
    private StripeConfig() {

        requestOptions = RequestOptions.builder()
                .setApiKey("pk_test_hasldPZsx3Bnkeuv7IC3JZ4Q00Kd3riNeh")
                .build();
    }

    public static RequestOptions requestOptions(){
        return requestOptions;
    }
}
