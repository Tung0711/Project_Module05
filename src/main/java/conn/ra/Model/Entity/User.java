package conn.ra.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 100)
    @JsonFormat(pattern = "^[a-zA-Z0-9]+$")
    private String userName;

    @Column(length = 100)
    private String fullName;

    private String images;

    private String email;

    private Boolean status;

    @Column(unique = true)
    private String password;

    @Column(unique = true, length = 15)
    @JsonFormat(pattern = "^0[1-9]\\d{8}$")
    private String phone;

    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<> ();
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Orders> orders;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<ShoppingCart> shoppingCarts;
}
