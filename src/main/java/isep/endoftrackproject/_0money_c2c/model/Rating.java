package isep.endoftrackproject._0money_c2c.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private User subject;
    private Double rating;
    private String review;
}
