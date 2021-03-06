package isep.endoftrackproject._0money_c2c.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private User owner;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Quality quality;
    private Boolean available = true;
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

    public enum Quality {
        NEW("New"),
        GOOD_QUALITY("Good Quality"),
        DAMAGED("Damaged");
        public final String text;

        Quality(String text) {
            this.text = text;
        }
    }
}
