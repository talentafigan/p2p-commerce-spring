package p2p.commerce.commerceapi.web.model;

import lombok.*;
import p2p.commerce.commerceapi.configuration.auditing.DateAll;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_authentication_log")
public class UserAuthenticationLog extends DateAll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_authentication_log_id")
    private int userAuthenticationLogId;

    @Column(name = "access_token", columnDefinition = "VARCHAR(100)", nullable = false)
    private String accessToken;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
