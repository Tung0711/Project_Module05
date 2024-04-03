package conn.ra.Model.Dto.Request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookRequest {
    private String images;
    private String bookName;
    private String author;
    private Double price;
    private String description;
    private Long catalogId;
}
