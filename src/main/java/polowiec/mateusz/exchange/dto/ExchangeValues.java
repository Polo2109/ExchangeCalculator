package polowiec.mateusz.exchange.dto;

import java.math.BigDecimal;

public class ExchangeValues {

    private BigDecimal eurValue;
    private String currency;
    private BigDecimal rate;
    private BigDecimal resultValue;

    public BigDecimal getEurValue() {
        return eurValue;
    }

    public void setEurValue(BigDecimal eurValue) {
        this.eurValue = eurValue;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getResultValue() {
        return resultValue;
    }

    public void setResultValue(BigDecimal resultValue) {
        this.resultValue = resultValue;
    }
}
