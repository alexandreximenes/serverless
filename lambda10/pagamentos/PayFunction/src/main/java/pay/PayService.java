package pay;

import lombok.RequiredArgsConstructor;

import java.util.List;

public class PayService {

    private PayRepository payRepository = new PayRepository();
    private ObjectMapperConvert objectMapperConvert = new ObjectMapperConvert();

    public PayService() {
    }

    public Pay save(String strPay) {

        Pay pay = objectMapperConvert.toObject(strPay);
        return payRepository.save(pay);
    }

    public Object findAll() {

        List<Pay> all = payRepository.findAll();
        return objectMapperConvert.toJson(all);
    }

    public Object findById(String id) {

        return payRepository
                .findById(id)
                .map(objectMapperConvert::toJson)
                .orElse("Não encontramos com ID: "+id);
    }

    public Object update(String id, Pay p) {

        Pay pay = payRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontramos com ID: " + id));

        pay.setPrice(p.getPrice());

        pay = payRepository.update(pay);
        return objectMapperConvert.toJson(pay);
    }
}
