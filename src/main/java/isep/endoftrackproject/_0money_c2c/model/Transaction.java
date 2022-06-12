package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Message> getMessagesOrdered() {
        List<Message> messages = new ArrayList<>(this.messages);
        messages.sort(Comparator.comparing(Message::getDate));
        return messages;
    }

    public String getNameForUser(User user) {
        //returns a name for the transaction, depending on the user requesting the name
        //it fetches the most valuable item it is selling in the transaction
        List<Item> ownedItems = new ArrayList<>(getItemsForUser(user));
        ownedItems.sort(Comparator.comparing(Item::getPrice));
        return "Selling " + ownedItems.get(0).getName();
    }

    public Set<User> getUsers() {
        //returns all the users of the transaction
        return items.stream().map(Item::getOwner).collect(Collectors.toSet());
    }

    public Set<Item> getItemsForUser(User user) {
        //returns the item owned by the given user and present in the transaction
        return items.stream().filter(item -> item.getOwner() == user).collect(Collectors.toSet());
    }

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
