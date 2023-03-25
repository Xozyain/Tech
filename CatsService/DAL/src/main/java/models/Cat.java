package models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String breed;
    private String color;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Owner owner;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Cat> catFriends;
    private void AddFriend(Cat cat){
        catFriends.add(cat);
    }
    private void RemoveCat(Cat cat){
        catFriends.remove(cat);
    }
}