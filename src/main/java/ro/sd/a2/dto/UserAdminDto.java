package ro.sd.a2.dto;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAdminDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String creationDate;
}
