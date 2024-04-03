package conn.ra.Model.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WishlistRequest {
    private Long bookId;
}
