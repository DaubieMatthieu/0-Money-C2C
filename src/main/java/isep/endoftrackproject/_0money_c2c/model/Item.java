package isep.endoftrackproject._0money_c2c.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    public enum Quality {
        NEW,
        GOOD_QUALITY,
        DAMAGED
    }
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User owner;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Quality quality;
    private Boolean available;
    @Lob
    private Set<Blob> images;
    @ManyToMany(mappedBy = "items")
    private Set<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
