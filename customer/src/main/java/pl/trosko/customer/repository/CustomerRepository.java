package pl.trosko.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.trosko.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query( "select o from Customer o where o.id in :ids" )
    List<Customer> findByIds(@Param("ids") List<Long> ids);

    Optional<Customer> findById(Long id);
}
