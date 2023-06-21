package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name= "phone", columnDefinition = "VARCHAR(20)")
    private String phone;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private Users user;
}
