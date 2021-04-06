package pay;

import com.amazonaws.services.dynamodbv2.document.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pay {

    private String id;
    private String produto;
    private Double price;

    public Pay(Item item) {
        this.id = item.getString("id");
        this.produto = item.getString("produto");
        this.price = Double.parseDouble(item.getString("price"));
    }
}
