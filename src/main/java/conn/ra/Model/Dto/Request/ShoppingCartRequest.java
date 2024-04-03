package conn.ra.Model.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ShoppingCartRequest {
    private Long bookId;
    private int quantity;
}
