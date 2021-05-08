package ro.sd.a2.dto;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DirectorDto {
    private String id;
    private String name;
    private String image;
    private String birthDate;
}
