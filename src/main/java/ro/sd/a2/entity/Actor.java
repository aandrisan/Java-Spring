package ro.sd.a2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table (name = "actor")
public class Actor {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String image;

    @Column
    private Date birthDate;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
