package de.sample.spring.customers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity(name = "OrderProduct")
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        OrderProduct that = (OrderProduct) o;
        return order != null && Objects.equals(order, that.order)
          && product != null && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
