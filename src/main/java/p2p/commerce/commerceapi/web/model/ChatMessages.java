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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_messages")
public class ChatMessages extends DateCreate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_message_id")
    private int chatMessageId;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "conversation_id")
    private String conversationId;

    @Column(name = "attachment")
    private String attachment;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Users creator;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}

