package api;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.tools.javac.util.List;
import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicReference;

public class PatientController {


    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @SneakyThrows
    public APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent request, Context context){

//
        Thread.sleep(5_000);
//        List<Object> objects = List.of(context, System.getenv());
        Object objects = context.getFunctionName();
        String s = mapper.writeValueAsString(objects);

        LambdaLogger logger = context.getLogger();
        logger.log("Send: "+ s);

        AmazonSNS sns = AmazonSNSClientBuilder.defaultClient();

        sns.publish(System.getenv("PATIENT_TOPIC"), mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request.getBody()));

        return new APIGatewayProxyResponseEvent().withBody(s).withStatusCode(200);
    }

    @SneakyThrows
    public APIGatewayProxyResponseEvent get(SNSEvent snsEvent, Context context){

        AtomicReference<Object> o = new AtomicReference<>(new Object());
        snsEvent.getRecords().forEach(sns -> {
            try {

                o.set(mapper.readValue(sns.getSNS().getMessage(), Object.class));
                LambdaLogger logger = context.getLogger();
                logger.log("Received: "+ o);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        return new APIGatewayProxyResponseEvent().withBody(mapper.writeValueAsString(o.get()
        )).withStatusCode(200);
    }

    public void mSQS(SQSEvent event){

        event.getRecords().forEach(e -> System.out.println(e.getBody()));

    }
}
