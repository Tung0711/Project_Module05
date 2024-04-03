package conn.ra.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_images")
    private String images;
    @Column(name = "book_name", unique = true, length = 50)
    private String bookName;
    @Column(name = "book_author")
    private String author;
    private Double price;
    @Column(name = "book_description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id")
    private Categories categories;
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;
}
