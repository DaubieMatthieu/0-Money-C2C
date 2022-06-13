package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionApproval {
    @EmbeddedId
    TransactionApprovalKey id = new TransactionApprovalKey();
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @MapsId("transactionId")
    private Transaction transaction;
    @Enumerated(EnumType.STRING)
    private Transaction.Status status = Transaction.Status.PENDING;

    public TransactionApproval(User user, Transaction transaction) {
        this.user = user;
        this.transaction = transaction;
    }
}
