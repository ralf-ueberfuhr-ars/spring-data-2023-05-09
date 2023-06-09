package de.sample.spring.customers.persistence;

import de.sample.spring.customers.entities.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setUuid(UUID.fromString(rs.getString(1)));
        customer.setName(rs.getString(2));
        return customer;
    }
}
