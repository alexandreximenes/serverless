package Pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

public class ObjectMapperConvert<T> {


    static ObjectMapper objectMapper;

    public ObjectMapperConvert() {
        objectMapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @SneakyThrows
    public T toObject(Class<T> object, String str) {

        T t = objectMapper.readValue(str, object);
        return t;
    }

    @SneakyThrows
    public String toJson(Class<T> object, String str) {

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(str);

    }

}
