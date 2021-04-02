package Pay;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(PayService.class))
public class PayController {

    PayService payService;

    public APIGatewayProxyResponseEvent post(String strPay){

        System.out.println("Recebibo: "+strPay);

        Pay pay = payService.save(strPay);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Success: "+pay);
    }

}
