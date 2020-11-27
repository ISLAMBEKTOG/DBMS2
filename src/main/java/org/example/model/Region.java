package org.example.model;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_generator")
    @SequenceGenerator(name="region_generator", sequenceName = "region_seq", allocationSize = 1)
    private Long id;

    private String name;

    public Region() {
    }

    public Region(String name) {
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
