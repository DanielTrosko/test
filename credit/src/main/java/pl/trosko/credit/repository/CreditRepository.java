package pl.trosko.credit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.trosko.credit.entity.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {
}
