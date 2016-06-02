package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Сущность Проданного Товара
 */
@Entity
// Запрос для получения всех проданных товаров из БД
@NamedQuery(name = "SaleProduct.getAll", query = "SELECT c from SaleProduct c")
@NamedNativeQuery(name = "SaleProduct.complex",
        query = "select s.id, s.productId, s.cost, p.name AS name from saleproduct s, product p where s.productId = p.id",
        resultClass = SaleProduct.class)
public class SaleProduct implements EntityInterface {

    // Идентификатор сущности целочисленное число
    private Long id;

    // Название сущности
    private String name;

    // Идентификато продукта
    private Long productId;

    // стоимость продукта
    private double cost;

    // ФИО покупателя
    private String fio;

    // номер телефона покупателя
    private String phone;

    // адрес покупателя
    private String address;

    private Long date;

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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
