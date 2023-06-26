package p2p.commerce.commerceapi.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeOtp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_otp_id")
    private int codeOtpId;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "used", nullable = false)
    private boolean used;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
