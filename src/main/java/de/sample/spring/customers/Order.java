package de.sample.spring.customers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private LocalDate date;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long price;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
