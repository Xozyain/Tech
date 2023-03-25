package models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "cat_list", columnDefinition="TEXT")
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Cat> cats;

    private void AddCat(Cat cat){
        cat.setOwner(this);
        cats.add(cat);
    }

    private void RemoveCat(Cat cat){
        cat.setOwner(null);
        cats.remove(cat);
    }
}
