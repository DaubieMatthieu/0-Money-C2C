package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date(System.currentTimeMillis());
    @ManyToMany
    @JoinTable(name = "transaction_item",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.MERGE)
    private Set<Message> messages;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Set<TransactionApproval> transactionApprovals = new HashSet<>();

    public List<Message> getMessagesOrdered() {
        List<Message> messages = new ArrayList<>(this.messages);
        messages.sort(Comparator.comparing(Message::getDate));
        return messages;
    }

    public String getNameForUser(User user) {
        //returns a name for the transaction, depending on the user requesting the name
        //it fetches the most valuable item it is selling in the transaction
        List<Item> ownedItems = new ArrayList<>(getItemsOfUser(user));
        ownedItems.sort(Comparator.comparing(Item::getPrice));
        return (ownedItems.size()>0)?"Selling " + ownedItems.get(0).getName():"error";
    }

    public Set<Item> getItemsOfUser(User user) {
        //returns the item owned by the given user and present in the transaction
        return items.stream().filter(item -> item.getOwner() == user).collect(Collectors.toSet());
    }

    public Set<User> getInvolvedUsers() {
        //Returns all the users having an item in the transaction
        return transactionApprovals.stream().map(TransactionApproval::getUser).collect(Collectors.toSet());
    }

    public Set<Item> getPotentialItems() {
        //Returns all the items of all the involved users
        return getInvolvedUsers().stream().flatMap(user -> user.getAvailableItems().stream()).collect(Collectors.toSet());
    }

    public Set<Item> getPotentialItemsForUser(User user) {
        //Returns all the potential items except those of the given user
        return getPotentialItems().stream().filter(item -> item.getOwner()!=user).collect(Collectors.toSet());
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

    public Transaction(Collection<Item> items) {
        this.items.addAll(items);
        Set<User> involvedUsers = getInvolvedUsers();
        involvedUsers.forEach(user -> transactionApprovals.add(new TransactionApproval(user, this)));
    }
}
