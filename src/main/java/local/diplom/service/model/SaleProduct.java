package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Created  by david on 06.03.16
 */
@Entity
@NamedQuery(name = "SaleProduct.getAll", query = "SELECT c from SaleProduct c")
@NamedNativeQuery(name = "SaleProduct.complex",
        query = "select s.id, s.productId, s.cost, p.name AS name from saleproduct s, product p where s.productId = p.id",
        resultClass = SaleProduct.class)
public class SaleProduct implements EntityInterface {
    private Long id;
    private String name;
    private Long productId;
    private double cost;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
