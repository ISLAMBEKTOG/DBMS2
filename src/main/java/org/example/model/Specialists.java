package org.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "specialists")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "findAllSpecialists",
                procedureName = "SPECIALIST_PAC.FINDALL",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "findSpecialistsByCategoriesId",
                procedureName = "SPECIALIST_PAC.FINDSPECIALISTSBYCATEGORIESID",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "findSpecialistsByUserId",
                procedureName = "SPECIALIST_PAC.FINDSPECIALISTSBYUSERID",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getAllSpecialistFromUserResumeByUserId",
                procedureName = "SPECIALIST_PAC.GETALLSPECIALISTFROMUSERRESUMEBYUSERID",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "findSpecialistsById",
                procedureName = "SPECIALIST_PAC.FINDSPECIALISTSBYID",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class)
                }
        ),
        @NamedStoredProcedureQuery(
                name = "getCountOfAllSpecialistsByCategoryId",
                procedureName = "SPECIALIST_PAC.GETCOUNTOFALLSPECIALISTSBYCATEGORYID",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "catId"),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "result")
                }
        ),
        @NamedStoredProcedureQuery(
                name = "addSpecialist",
                procedureName = "SPECIALIST_PAC.ADDSPECIALIST",
                resultClasses = Specialists.class,
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_content"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Date.class, name = "p_created_date"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_image"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_price"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_title"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_category_id"),
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_city_id"),
                        @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class, name = "result")
                }
        ),
        @NamedStoredProcedureQuery(
                    name = "deleteSpecialist",
                procedureName = "SPECIALIST_PAC.DELETESPECIALIST",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "p_id"),
                }
        )
})
public class Specialists {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialists_generator")
    @SequenceGenerator(name = "specialists_generator", sequenceName = "specialists_seq", allocationSize = 1)
    private Long id;

    private String title;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "city_id")
    private City city;

    private String content;

    private Integer price;

    @Column(name = "created_date")
    private Date date;

    private String image;

    public Specialists() {
    }

    public Specialists(String title, Category category, String content, Integer price, City city, Date date, String image) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.city = city;
        this.date = date;
        this.image = image;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
