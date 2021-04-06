package Pay;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final ObjectMapperConvert<Pay> objectMapperConvert;

    public Pay save(String strPay) {

        Pay pay = objectMapperConvert.toObject(Pay.class, strPay);
        return payRepository.save(pay);
    }

    public Pay findAll() {

        new Paym
        Pay pay = objectMapperConvert.toJson(Pay.class, );
        return payRepository.findAll();
    }
}
