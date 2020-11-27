package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "user_resume")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getUserResumesByUserIdAndSpecialistId",
                query = "{? = call USER_RESUME_PAC.GETUSERRESUMESBYUSERIDANDSPECIALISTID(:userId, :specId)}",
                resultClass = UserResume.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "addUserResume",
                procedureName = "USER_RESUME_PAC.ADDUSERRESUME",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_specId"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_userId")
                }
        ),
        @NamedStoredProcedureQuery(
                name = "deleteUserResume",
                procedureName = "USER_RESUME_PAC.DELETEUSERRESUME",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id")
                }
        )
})
public class UserResume {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_resume_generator")
    @SequenceGenerator(name = "user_resume_generator", sequenceName = "user_resume_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Specialists specialists;

    public UserResume() {
    }

    public UserResume(User user, Specialists specialists) {
        this.user = user;
        this.specialists = specialists;
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
}
