package conn.ra.Model.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordRequest {
    private String oldPass;
    private String newPass;
    private String confirmNewPass;
}