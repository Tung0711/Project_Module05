package conn.ra.Model.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User users;

    @Column(name = "full_address")
    private String address;
    @Column(length = 15)
    private String phone;

    @Column(name = "receive_name", length = 50)
    private String receiveName;
}