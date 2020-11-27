package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllCategories",
                query = "{? = call CATEGORY_PAC.FINDALL()}",
                resultClass = Category.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "findCategoryByName",
                query = "{? = call CATEGORY_PAC.FINDBYNAME(:s)}",
                resultClass = Category.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getCategoriesByIds",
                query = "{? = call CATEGORY_PAC.GETCATEGORIESBYIDS(:first, :second)}",
                resultClass = Category.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "getAllCategoriesByUserId",
                query = "{? = call CATEGORY_PAC.GETALLCATEGORIESBYUSERID(:user_id)}",
                resultClass = Category.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
