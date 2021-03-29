package mpackage;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.stream.Collectors;

public class VendaLambda {

    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public APIGatewayProxyResponseEvent get(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(System.getenv("VENDA_TABLE")));
        List<Venda> vendas = scanResult.getItems().stream().map(item ->
                new Venda(Long.parseLong(item.get("id").getN()), item.get("produto").getS())
        ).collect(Collectors.toList());
        if(!vendas.isEmpty()){
            System.out.println(mapper.writeValueAsString(vendas));
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(mapper.writeValueAsString(vendas));
        }
        System.out.println(mapper.writeValueAsString(vendas));
        return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("Nenhuma venda encontrada");

    }

    public APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        System.out.println("venda recebido: "+ request.getBody());
        Venda venda = mapper.readValue(request.getBody(), Venda.class);

        //banco
        DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
        Table table = dynamoDB.getTable(System.getenv("VENDA_TABLE"));
        Item item = new Item()
                .withPrimaryKey("id", venda.getId())
                .withString("produto", venda.getProduto());

        System.out.println(item);
        table.putItem(item);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Venda recebido: "+mapper.writeValueAsString(venda));
    }
}
