package isep.endoftrackproject._0money_c2c.model;

import isep.endoftrackproject._0money_c2c.validation.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// User class extended with the password confirmation field for the User Interface
// This is not an entity and cannot not be used for persistence
@PasswordMatches
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String confirmPassword;
    @NotNull
    @NotEmpty
    private String telephoneNumber;
    @NotNull
    private Address address;
    private Double rating;
    private Integer soldItemsCount;
}
