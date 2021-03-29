package mpackage;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VendaLambdaPost {

    public APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        Venda venda = mapper.readValue(request.getBody(), Venda.class);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Venda recebido: "+mapper.writeValueAsString(venda));
    }
}
