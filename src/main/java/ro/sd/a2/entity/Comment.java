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
@Table (name = "comment")
public class Comment {
    @Id
    private String id;

    @Column
    private String comment;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "idMovie")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
