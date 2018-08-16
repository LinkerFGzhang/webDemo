package demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"usersByOwnerId", "hibernateLazyInitializer", "handler", "fieldHandler"})
public class Groups implements Serializable {
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

    public Groups() {
    }

    public Groups(int id) {
        this.id = id;
    }

    public Groups(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Groups(int id, String name, String description, Users usersByOwnerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.usersByOwnerId = usersByOwnerId;
    }
}
