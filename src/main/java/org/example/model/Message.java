package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllMessages",
                query = "{? = call MESSAGE_PAC.FINDALL()}",
                resultClass = Message.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getAllMessagesByUserIdAndSpecId",
                query = "{? = call MESSAGE_PAC.GETALLMESSAGESBYUSERIDANDSPECID(:userId, :specId)}",
                resultClass = Message.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getAllMessagesBySpecId",
                query = "{? = call MESSAGE_PAC.GETALLMESSAGESBYSPECID(:specId)}",
                resultClass = Message.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})

})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "deleteMessage",
                procedureName = "MESSAGE_PAC.DELETEMESSAGE",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "messId")
                }
        ),
        @NamedStoredProcedureQuery(
                name = "addMessage",
                procedureName = "MESSAGE_PAC.ADDMESSAGE",
                resultClasses = Message.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_content"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "p_created_date"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_specId"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_userId"),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class, name = "result"),
                }
        )
})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_generator")
    @SequenceGenerator(name = "message_generator", sequenceName = "message_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Specialists specialists;

    private String content;

    @Column(name = "created_date")
    private Date date;

    public Message() {
    }

    public Message(User user, Specialists specialists, String content, Date date) {
        this.user = user;
        this.specialists = specialists;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specialists getSpecialists() {
        return specialists;
    }

    public void setSpecialists(Specialists specialists) {
        this.specialists = specialists;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
