package ro.sd.a2.entity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table (name = "director")
public class Director {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private Date birthDate;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;
}
