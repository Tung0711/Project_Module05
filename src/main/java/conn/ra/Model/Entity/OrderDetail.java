package conn.ra.Model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_detail")
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(length = 100)
    private String name;

    @Column(name = "unit_price")
    private Double price;

    @Column(name = "order_quantity")
    @Min(1)
    private Integer orderQuantity;
}
