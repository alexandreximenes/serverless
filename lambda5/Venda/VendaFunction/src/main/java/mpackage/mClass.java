package mpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handler for requests to Lambda function.
 */
public class mClass {

    public String mMethod(String name, Context context){

        return "Olá "+name + " voce esta executando sua função: " + context.getFunctionName();


    }

    public List<Venda> getVendasList(){

        return List.of(
                new Venda(1l, "Celular"),
                new Venda(1l, "Guarda roupa"),
                new Venda(1l, "TV")
                );
    }

    public String getVendasJson() throws JsonProcessingException {

        return new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(
                List.of(
                new Venda(1l, "Celular"),
                new Venda(1l, "Guarda roupa"),
                new Venda(1l, "TV")
        )
                );
    }

    public String getVendasMap(Map<String, String> map) throws JsonProcessingException {

        String s = new ObjectMapper()
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        System.out.println(s);
        return s;
    }
}
