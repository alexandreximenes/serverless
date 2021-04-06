package Pay;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PayRepository {

    private final DynamoDB dynamoDB;
    private final AmazonDynamoDB amazonDynamoDB;

    public Pay save(Pay pay) {
        return new Pay();
    }

    public List<Pay> findAll() {
        return List.of(new Pay(),new Pay(),new Pay());
    }
}
