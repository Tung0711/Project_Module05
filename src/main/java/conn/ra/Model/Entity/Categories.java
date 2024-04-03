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
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private Long id;
    @Column(name = "catalog_name",unique = true,length = 75)
    private String catalogName;
    private String description;
    private Boolean status;

    @OneToMany(mappedBy = "categories")
    @JsonIgnore
    List<Book> books;
}
