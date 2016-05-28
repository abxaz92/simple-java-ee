package local.diplom.service.abstracts;

/**
 * Интерфейс сущности, от которой все остальные сущности наследуются
 * чтобы каркас работы с БД мог с ними работать
 */
public interface EntityInterface {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

}
