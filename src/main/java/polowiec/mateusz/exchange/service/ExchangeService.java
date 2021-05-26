package polowiec.mateusz.exchange.service;

import org.springframework.stereotype.Service;
import polowiec.mateusz.exchange.model.ExchangeRate;
import polowiec.mateusz.exchange.dto.ExchangeValues;
import polowiec.mateusz.exchange.repository.ExchangeRateRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ExchangeService {

    private ExchangeRateRepository exchangeRateRepository;

    public ExchangeService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public void save(List<ExchangeRate> exchangeRates){
        exchangeRates.forEach(p -> exchangeRateRepository.save(p));
    }

    public List<ExchangeRate> findAll(){
        return exchangeRateRepository.findAllByOrderByCurrencyAsc();
    }

    public ExchangeValues calculate(ExchangeValues exchangeValues){
        ExchangeValues exchange = new ExchangeValues();
        List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();
        exchangeRates.forEach(p -> {
            if(p.getCurrency().equals(exchangeValues.getCurrency())) {
                exchange.setRate(BigDecimal.valueOf(p.getRate()));
            }
        });
        exchange.setEurValue(exchangeValues.getEurValue());
        exchange.setCurrency(exchangeValues.getCurrency());
        exchange.setResultValue(exchange.getRate().multiply(exchange.getEurValue()).setScale(2, RoundingMode.HALF_UP));
        return exchange;
    }
}
