package helloworld.api;

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
import helloworld.Projeto;

import java.util.List;
import java.util.stream.Collectors;

public class ProjetoRest {

    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public APIGatewayProxyResponseEvent get(APIGatewayProxyRequestEvent req) throws JsonProcessingException {

        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        ScanResult scanResult = dynamoDB.scan(new ScanRequest().withTableName(System.getenv("PROJETO_TABLE")));
        List<Projeto> projetos = scanResult.getItems().stream().map(item ->
                new Projeto(Integer.parseInt(item.get("id").getN()), item.get("nome").getS())
        ).collect(Collectors.toList());
        if(!projetos.isEmpty()){
            System.out.println(mapper.writeValueAsString(projetos));
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody(mapper.writeValueAsString(projetos));
        }
        System.out.println(mapper.writeValueAsString(projetos));
        return new APIGatewayProxyResponseEvent().withStatusCode(404).withBody("Nenhuma venda encontrada");

    }

    public APIGatewayProxyResponseEvent post(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        System.out.println("projeto recebido: "+ request.getBody());
        Projeto projeto = mapper.readValue(request.getBody(), Projeto.class);

        //banco
        DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
        Table table = dynamoDB.getTable(System.getenv("PROJETO_TABLE"));
        Item item = new Item()
                .withPrimaryKey("id", projeto.getId())
                .withString("nome", projeto.getNome());

        System.out.println(item);
        table.putItem(item);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Venda recebido: "+mapper.writeValueAsString(item));
    }
}
