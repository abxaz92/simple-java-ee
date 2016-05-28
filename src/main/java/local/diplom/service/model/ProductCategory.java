package local.diplom.service.model;

import local.diplom.service.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Сущность категории
 */
@Entity

// Запрос для получения всех категорий из БД
@NamedQuery(name = "ProductCategory.getAll", query = "SELECT c from ProductCategory c")
public class ProductCategory implements EntityInterface {

    // Идентификатор категорий целочисленное число
    private Long id;

    // строка нзвания категрии
    private String name;

    // наценка
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
