package de.sample.spring.customers.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    @Size(min = 3)
    private String name;
    private LocalDate birthdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_uuid", referencedColumnName = "uuid")
    private Avatar avatar;
}
