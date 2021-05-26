package polowiec.mateusz.exchange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import polowiec.mateusz.exchange.dto.ExchangeValues;
import polowiec.mateusz.exchange.model.ExchangeRate;
import polowiec.mateusz.exchange.service.ExchangeService;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class ExchangeController {

    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("")
    public List<ExchangeRate> getExchangeRates(){
        return exchangeService.findAll();
    }
    @PostMapping("")
    public ResponseEntity<ExchangeValues> calculator(@RequestBody ExchangeValues exchangeValues){
        if(exchangeValues.getEurValue() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Należy wpisać kwotę do przeliczenia");
        if(exchangeValues.getCurrency() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Należy wybrać docelową walutę");
        return ResponseEntity.ok(exchangeService.calculate(exchangeValues));
    }
}
