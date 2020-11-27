package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAllCities",
                query = "{? = call CITY_PAC.FINDALL()}",
                resultClass = City.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")}),
        @NamedNativeQuery(
                name = "findCityByName",
                query = "{? = call CITY_PAC.FINDBYNAME(:s)}",
                resultClass = City.class,
                hints = {@QueryHint(name = "org.hibernate.callable", value = "true")})
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
    @SequenceGenerator(name="city_generator", sequenceName = "city_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "region_id")
    private Region region;

    public City() {
    }

    public City(String name, Region region) {
        this.name = name;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
