package ro.sd.a2.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table (name = "rating")
public class Rating {
    @Id
    private String id;

    @Column
    private Integer rating;

    @Column
    private Date ratingDate;

    @ManyToOne
    @JoinColumn(name = "idMovie")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
