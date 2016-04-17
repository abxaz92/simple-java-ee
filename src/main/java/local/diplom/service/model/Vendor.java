package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Created  by david on 17.04.16
 */
@Entity
@NamedQuery(name = "Vendor.getAll", query = "SELECT c from Vendor c")
public class Vendor implements EntityInterface {
    private Long id;
    private String name;

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

}
