package pay.Stripe;

import com.stripe.net.RequestOptions;

public class StripeConfig {

    public static String USD = "usd";

    static RequestOptions requestOptions;
    private StripeConfig() {

        requestOptions = RequestOptions.builder()
                .setApiKey("jfklsdjfljsdklfjsklfjklsjfklasdjfklsdjfkl")
                .build();
    }

    public static RequestOptions requestOptions(){
        return requestOptions;
    }
}
