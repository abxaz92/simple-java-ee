package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Created by david on 27.02.16.
 */
@Entity
@NamedQuery(name = "ProductCategory.getAll", query = "SELECT c from ProductCategory c")
public class ProductCategory implements EntityInterface {
    private Long id;
    private String name;
    private double markup;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public double getMarkup() {
        return markup;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }
}
