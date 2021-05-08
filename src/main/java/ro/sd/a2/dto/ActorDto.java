package ro.sd.a2.dto;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ActorDto {
    private String id;
    private String name;
    private String birthDate;
    private String image;
}
