package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Сущность Товара
 */
@Entity
@NamedQueries({
        // Запрос для получения всех товаров из БД
        @NamedQuery(name = "Product.getAll", query = "SELECT c from Product c"),

        // Запрос для получения всех товаров из БД для категории
        @NamedQuery(name = "Product.getByCategory", query = "select c from Product c where c.category.id = :categoryId")
})

public class Product implements EntityInterface {
    // Идентификатор продукта целочисленное число
    private Long id;

    // строка нзвания продукта
    private String name;

    // строка нзвания брэнда
    private String brand;

    // категория продукта
    private ProductCategory category;

    // количество товаров
    private int amount;

    // стоимость товаров
    private double cost;

    // id изображения в БД
    private String image;

    // Описпние товара
    private String description;

    // Производитель
    private Vendor vendor;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
