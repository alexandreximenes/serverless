package pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

public class ObjectMapperConvert {



    ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public ObjectMapperConvert() {
    }

    @SneakyThrows
    public Pay toObject(String str) {

        Pay pay = objectMapper.readValue(str, Pay.class);
        return pay;
    }

    @SneakyThrows
    public String toJson(Object o) {

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);

    }

}
