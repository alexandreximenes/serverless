package Pay;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor({@__(PayRepository.class, @__(ObjectMapperConvert.class))})
public class PayService {

    private final PayRepository payRepository;
    private final ObjectMapperConvert<Pay> objectMapperConvert;

    public Pay save(String strPay) {

        Pay pay = objectMapperConvert.toObject(Pay.class, strPay);
        return payRepository.save(pay);
    }
}
