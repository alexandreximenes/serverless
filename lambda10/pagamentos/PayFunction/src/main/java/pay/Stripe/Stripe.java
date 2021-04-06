package pay.Stripe;

import pay.Pay;
import com.stripe.model.Payout;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class Stripe {

    @SneakyThrows
    public void charge(Pay pay){
        Map<String, Object> params = new HashMap<>();
        params.put("amount", pay.getPrice());
        params.put("currency", StripeConfig.USD);

        Payout payout = Payout.create(params);

        System.out.println(payout);
    }
}
