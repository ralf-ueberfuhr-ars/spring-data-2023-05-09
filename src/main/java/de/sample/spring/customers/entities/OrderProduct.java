package de.sample.spring.customers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "OrderProduct")
@Getter
@Setter
@ToString
// @EqualsAndHashCode ???
@Table(name = "ORDERPRODUCTS")
public class OrderProduct { // "OrderEntry" ?

    @Id
    @ManyToOne
    @JoinColumn(name = "order_uuid")
    private Order order;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_uuid")
    private Product product;
    private int count;

}
