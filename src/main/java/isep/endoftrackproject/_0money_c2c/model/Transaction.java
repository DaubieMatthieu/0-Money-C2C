package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @ManyToMany
    @JoinTable(name = "transaction_item",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Message> messages;
    @OneToMany(mappedBy = "transaction")
    private Set<TransactionApproval> transactionApprovals;

    public Status getStatus() {
        int i = 0;
        for (TransactionApproval ta : transactionApprovals) {
            if (ta.getStatus() == Status.REJECTED) return Status.REJECTED;
            if (ta.getStatus() == Status.ACCEPTED) i += 1;
        }
        return (i == transactionApprovals.size()) ? Status.ACCEPTED : Status.PENDING;
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
