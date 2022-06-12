package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String username;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    @Email
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String telephoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private Address address;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Item> items;
    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Rating> issuedRatings;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Rating> referredRatings;
    @OneToMany(mappedBy = "sender", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Message> sentMessages;
    @OneToMany(mappedBy = "user")
    private Set<TransactionApproval> transactionApprovals;

    public Double getRating() {
        return referredRatings.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
    }

    public Set<Item> getSoldItems() {
        return transactionApprovals.stream().map(TransactionApproval::getTransaction).filter(transaction -> transaction.getStatus()==Transaction.Status.ACCEPTED).flatMap(t->t.getItems().stream()).collect(Collectors.toSet());
    }

    public Integer getSoldItemsCount() {
        return getSoldItems().size();
    }
}
