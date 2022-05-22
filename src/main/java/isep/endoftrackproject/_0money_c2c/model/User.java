package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String telephoneNumber;
    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
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
}