package ro.sd.a2.dto;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDto {
    private String id;
    private String name;
    private String releaseDate;
    private String image;
}
