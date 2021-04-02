package helloworld;

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

/**
 * Handler for requests to Lambda function.
 */

public class App{

    public String hello(String s){
        return "Ola nundo "+s;
    }


    public Integer duplicate(Integer n){
        return n*2;
    }

    public List<String> listNames(List<Integer> ids){
        System.out.println(ids);

        Map<Integer, String> names = new HashMap<>();
        names.put(1, "Alexandre");
        names.put(2, "Tiago");
        names.put(3, "Ximenes");


        return names.entrySet()
                .stream()
                .filter(k1 -> ids.contains(k1.getKey()))
                .map(k2 -> k2.getValue())
                .collect(Collectors.toList());
    }

    public String tomap(Map<String, Integer> map){

        System.out.println(map);

        return map.toString();

    }


}
//public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
//
//    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("X-Custom-Header", "application/json");
//
//        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
//                .withHeaders(headers);
//        try {
//            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
//            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);
//
//            return response
//                    .withStatusCode(200)
//                    .withBody(output);
//        } catch (IOException e) {
//            return response
//                    .withBody("{}")
//                    .withStatusCode(500);
//        }
//    }
//
//    private String getPageContents(String address) throws IOException{
//        URL url = new URL(address);
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
//            return br.lines().collect(Collectors.joining(System.lineSeparator()));
//        }
//    }
//}
