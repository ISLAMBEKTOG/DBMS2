package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "user_bucket")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getUserBucketByUserIdAndSpecialistId",
                query = "{? = call USER_BUCKET_PAC.GETUSERBUCKETBYUSERIDANDSPECIALISTID(:userId, :specId)}",
                resultClass = UserBucket.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getAllUserBucketsBySpecialistId",
                query = "{? = call USER_BUCKET_PAC.GETALLUSERBUCKETSBYSPECIALISTID(:specId)}",
                resultClass = UserBucket.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "addUserBucket",
                procedureName = "USER_BUCKET_PAC.ADDUSERBUCKET",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_specId"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_userId")
                }
        ),
        @NamedStoredProcedureQuery(
                name = "deleteUserBucket",
                procedureName = "USER_BUCKET_PAC.DELETEUSERBUCKET",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id")
                }
        )
})
public class UserBucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_bucket_generator")
    @SequenceGenerator(name = "user_bucket_generator", sequenceName = "user_bucket_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Specialists specialists;

    public UserBucket() {
    }

    public UserBucket(User user, Specialists specialists) {
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
