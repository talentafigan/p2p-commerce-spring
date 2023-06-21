package p2p.commerce.commerceapi.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import p2p.commerce.commerceapi.configuration.auditing.DateCreate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "consultations")
public class Consultations extends DateCreate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_id")
    private int consultationId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admins admin;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "conversation_id")
    private String conversationId;
}
