package isep.endoftrackproject._0money_c2c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TransactionApprovalKey implements Serializable {
    private Long userId;
    private Long transactionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionApprovalKey that = (TransactionApprovalKey) o;
        return userId.equals(that.userId) && transactionId.equals(that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, transactionId);
    }
}