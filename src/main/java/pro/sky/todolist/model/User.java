package pro.sky.todolist.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 10)
    private String name;

    @Column(nullable = false,length = 30)
    private  String email;

    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;

}
