package de.sample.spring.customers.persistence;

import de.sample.spring.customers.entities.CustomerState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.PersistenceException;

@Converter(autoApply = true)
public class CustomerStateConverter implements AttributeConverter<CustomerState, String> {
    @Override
    public String convertToDatabaseColumn(CustomerState attribute) {
        if(null == attribute) {
            return null;
        } else {
            return switch (attribute) {
                case ACTIVE -> "a";
                case TEMPORARILY_LOCKED -> "tl";
                case LOCKED -> "l";
            };
        }
    }

    @Override
    public CustomerState convertToEntityAttribute(String dbData) {
        if(null == dbData) {
            return null;
        } else {
            return switch (dbData) {
                case "a" -> CustomerState.ACTIVE;
                case "tl" -> CustomerState.TEMPORARILY_LOCKED;
                case "l" -> CustomerState.LOCKED;
                default -> throw new PersistenceException();
            };
        }
    }
}
