package polowiec.mateusz.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polowiec.mateusz.exchange.model.ExchangeRate;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

     List<ExchangeRate> findAllByOrderByCurrencyAsc();
}
