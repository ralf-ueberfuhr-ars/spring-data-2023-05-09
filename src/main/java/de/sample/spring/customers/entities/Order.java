package de.sample.spring.customers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Order")
@Getter
@Setter
@ToString
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    private String nr;
    private LocalDate date;
    private long price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_uuid", referencedColumnName = "uuid")
    private Customer customer;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude // ! Lazy Loaded
    private Set<OrderProduct> orderProducts = new HashSet<>();

}
