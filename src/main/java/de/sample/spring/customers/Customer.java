package de.sample.spring.customers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity(name = "Customer")
@Table(name = "CUSTOMERS")
@Slf4j
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    @Size(min = 3)
    private String name;
    private LocalDate birthdate;
    /*
     * By default, JPA stores enums using ordinal value.
     * Be careful! When adding literals, the value will interpreted by ordinal and may be changed.
     */
    // @Enumerated(EnumType.STRING)
    // best solution: we use a converter here
    @JsonProperty(defaultValue = "ACTIVE")
    private CustomerState state = CustomerState.ACTIVE;
    @ElementCollection(fetch = FetchType.EAGER) // do not load lazily, because session is already closed on view rendering
    private List<String> interests;
    // https://www.baeldung.com/jpa-one-to-one
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@PrimaryKeyJoinColumn // the primary key of the Customer entity is used as the foreign key value for the associated Avatar entity
    @JsonIgnore
    private Avatar avatar;

    @PrePersist
    @PreUpdate
    public void setAvatarId() {
        if(null != avatar) {
            log.info("Setting customer to avatar during saving the customer.");
            avatar.setCustomer(this);
        }
    }


}
