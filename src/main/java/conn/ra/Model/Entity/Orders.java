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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "serial_number", length = 100)
    private String orderNumber;

    @Column(name = "total_price")
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private EOrder status;

    @Column(length = 100)
    private String note;

    @Column(name = "receive_name", length = 100)
    private String receiveName;

    @Column(name = "receive_address")
    private String receiveAddress;

    @Column(name = "receive_phone", length = 15)
    private String receivePhone;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User users;

    @OneToMany(mappedBy = "orders")
    @JsonIgnore
    List<OrderDetail> orderDetails;
}
