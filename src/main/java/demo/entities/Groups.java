package demo.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author FangJ
 * @create 2018-07-27-22:01
 */
@Entity
public class Groups {
    private int id;
    private String name;
    private String description;
    private Users usersByOwnerId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 40)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return id == groups.id &&
                Objects.equals(name, groups.name) &&
                Objects.equals(description, groups.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description);
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    public Users getUsersByOwnerId() {
        return usersByOwnerId;
    }

    public void setUsersByOwnerId(Users usersByOwnerId) {
        this.usersByOwnerId = usersByOwnerId;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
