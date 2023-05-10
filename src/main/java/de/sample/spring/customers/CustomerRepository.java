package de.sample.spring.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findAllByInterestsContainingIgnoreCase(String interest);

    @Query("SELECT c FROM Customer c WHERE c.state = de.sample.spring.customers.CustomerState.ACTIVE")
    List<Customer> findAllActive();
}
