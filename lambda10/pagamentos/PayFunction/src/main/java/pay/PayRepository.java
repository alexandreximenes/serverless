package pay;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.document.*;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class PayRepository {

    private DynamoDB dynamoDB;
    private AmazonDynamoDB amazonDynamoDB;
    private Table table;
    private ScanResult scanResult;

    public PayRepository() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
        this.amazonDynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
        this.table = this.dynamoDB.getTable(System.getenv("PAY_TABLE"));
        this.scanResult = amazonDynamoDB.scan(new ScanRequest().withTableName(System.getenv("PAY_TABLE")));
    }

    public Pay save(Pay pay) {

        pay.setId(UUID.randomUUID().toString().substring(6));

        Item item = new Item()
                .withPrimaryKey("id", pay.getId())
                .withString("produto", pay.getProduto())
                .withDouble("price", pay.getPrice());

        this.table.putItem(item);

        return pay;
    }

    public List<Pay> findAll() {

        return scanResult
                .getItems().stream()
                .map(item -> new Pay(item.get("id").getS(), item.get("produto").getS(), Double.parseDouble(item.get("price").getN())))
                .collect(Collectors.toList());
    }

    public Optional<Pay> findById(String id) {

        Pay pay = null;

        HashMap<String, String> nameMap = new HashMap<String, String>();
        nameMap.put("#yr", "year");

        HashMap<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put(":yyyy", 1985);

        QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
                .withValueMap(valueMap);

        ItemCollection<QueryOutcome> items = null;
        Iterator<Item> iterator = null;
        Item item = null;

        try {
            System.out.println("Movies from 1985");
            items = table.query(querySpec);

            iterator = items.iterator();
            while (iterator.hasNext()) {
                item = iterator.next();
                System.out.println(item.getString("id") + ": " + item.getString("produto") + " : " + item.getString("price"));
            }

        }
        catch (Exception e) {
            System.err.println("Not found");
            System.err.println(e.getMessage());
        }

        if(nonNull(item)){
            pay = new Pay(item);
        }

        return Optional.ofNullable(pay);
    }

    public Pay update(Pay p) {
        Pay pay = p;
        return pay;
    }
}
