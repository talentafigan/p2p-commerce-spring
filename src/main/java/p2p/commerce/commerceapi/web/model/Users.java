package p2p.commerce.commerceapi.web.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import p2p.commerce.commerceapi.configuration.auditing.DateAll;
import p2p.commerce.commerceapi.web.repository.AdminRepository;
import p2p.commerce.commerceapi.web.repository.ClientRepository;
import p2p.commerce.commerceapi.web.repository.SellesRepository;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users extends DateAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_type_id")
    private UserType userType;

    @JsonIgnore
    @Column(name = "username")
    private String username;
    @JsonIgnore
    @Column(name = "password")
    private String password;

}
