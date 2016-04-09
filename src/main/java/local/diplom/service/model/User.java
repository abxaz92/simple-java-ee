package local.diplom.service.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created  by david on 09.04.16
 */
@Entity(name = "users")
@Table(name = "users")
@NamedQuery(name = "User.getAll", query = "SELECT c from users c")
public class User {
    private String username;
    private String type;
    private String password;

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
