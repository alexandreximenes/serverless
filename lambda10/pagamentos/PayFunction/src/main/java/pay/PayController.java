package pay;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class PayController {

    private PayService payService = new PayService();

    public APIGatewayProxyResponseEvent charge(APIGatewayProxyRequestEvent requestEvent){

        System.out.println("Recebibo: "+requestEvent);

        Pay pay = payService.save(requestEvent.getBody());

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Success: "+pay);
    }

    public Object findAll() {

        Object all = payService.findAll();
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Success: "+all);
    }

    public Object findById(String id) {

        Object byId = payService.findById(id);
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Success: "+byId);
    }

    public Object update(String id, Pay p) {

        Object up = payService.update(id, p);
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Success: "+up);

    }

}
