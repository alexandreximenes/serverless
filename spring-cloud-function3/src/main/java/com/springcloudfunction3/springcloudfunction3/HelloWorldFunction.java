package com.springcloudfunction3.springcloudfunction3;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.function.Function;

public class HelloWorldFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        return
                new APIGatewayProxyResponseEvent()
                        .withBody("ola mundo")
                        .withStatusCode(200);
    }
}

/*
* public class HelloWorldFunction implements Function<String, String> {
    @Override
    public String apply(String s) {
        return s.toUpperCase();
    }
}
* */
