package de.sample.spring.customers.persistence;

import de.sample.spring.customers.entities.Customer;
import de.sample.spring.customers.entities.CustomerState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer>  findAllByInterestsContainingIgnoreCase(String interest);
    List<Customer> findAllByStateIs(CustomerState state);

    @Query("SELECT c FROM Customer c WHERE c.state = de.sample.spring.customers.entities.CustomerState.ACTIVE")
    List<Customer> findAllActive();


}
