package de.sample.spring.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Customer")
@Getter
@Setter
@ToString
@Table(name = "CUSTOMERS")
@NamedQuery(
  name = "findAllCustomers",
  query = "SELECT c FROM Customer c"
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    @Size(min = 3)
    private String name;
    private LocalDate birthdate;

}
