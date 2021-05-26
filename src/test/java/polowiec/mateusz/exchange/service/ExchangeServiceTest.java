package polowiec.mateusz.exchange.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import polowiec.mateusz.exchange.dto.ExchangeValues;

import java.math.BigDecimal;

@SpringBootTest
class ExchangeServiceTest {

    @Autowired
    private ExchangeService exchangeService;

    @Test
    void isReturnGoodResultValue() {
        ExchangeValues exchangeValues = new ExchangeValues();
        exchangeValues.setEurValue(new BigDecimal(100));
        exchangeValues.setCurrency("PLN");
        Assert.isTrue((exchangeService.calculate(exchangeValues)).getResultValue().equals(BigDecimal.valueOf(448.39)),
                "Failure in calculation, wrong result");

    }

}