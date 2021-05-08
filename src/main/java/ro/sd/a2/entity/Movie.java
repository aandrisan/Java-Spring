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
@Table (name = "movie")
public class Movie {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private Date releaseDate;

    @Column
    private String image;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movie_actor",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;

    @ManyToOne
    @JoinColumn(name = "idDirector")
    private Director director;

    @OneToMany(mappedBy = "movie")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;
}
