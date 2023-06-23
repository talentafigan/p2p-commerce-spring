package p2p.commerce.commerceapi.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password", columnDefinition = "VARCHAR(100)", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;
}
