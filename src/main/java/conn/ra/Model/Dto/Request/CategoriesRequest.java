package conn.ra.Model.Dto.Request;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoriesRequest {
    private String catalogName;
    private String description;
}
