package helloworld.api;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class ProjetoRest {

    public APIGatewayProxyResponseEvent get(APIGatewayProxyRequestEvent req){

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("lista de produtos");

    }
}
