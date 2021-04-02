package Pay;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor({@__(DynamoDB), @__(AmazonDynamoDB)})
public class PayRepository {

    private final DynamoDB dynamoDB;
    private final AmazonDynamoDB amazonDynamoDB;

    public Pay save(Pay pay) {
        return new Pay();
    }
}
