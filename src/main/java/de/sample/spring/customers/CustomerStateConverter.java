package de.sample.spring.customers;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter(autoApply = true)
public class CustomerStateConverter implements AttributeConverter<CustomerState, String> {
    @Override
    public String convertToDatabaseColumn(CustomerState value) {
        return switch (Objects.requireNonNullElse(value, CustomerState.ACTIVE)) {
            case ACTIVE -> "a";
            case LOCKED -> "l";
            case TEMPORARILY_LOCKED -> "tl";
        };
    }

    @Override
    public CustomerState convertToEntityAttribute(String value) {
        return switch (Objects.requireNonNullElse(value, "a")) {
            case "a" -> CustomerState.ACTIVE;
            case "l" -> CustomerState.LOCKED;
            case "tl" -> CustomerState.TEMPORARILY_LOCKED;
            default -> throw new IllegalStateException("customer state '" + value + "' is not a valid value");
        };
    }
}
