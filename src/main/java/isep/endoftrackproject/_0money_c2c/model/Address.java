package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String line1;
    @NotNull
    @NotEmpty
    private String city;
    private String zipCode;
    @NotNull
    @NotEmpty
    private String country;
    private Double longitude;
    private Double latitude;

    public String getText() {
        return line1 + " " + city + " " + zipCode + " " + country;
    }
}
