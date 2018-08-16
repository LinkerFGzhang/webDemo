package demo.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Users implements Serializable {
    private int id;
    private String name;
    private String password;
    private String genericName;
    private String token;
    private Timestamp createTime;
    private Groups groupsByGroupId;

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
    @Column(name = "password", nullable = false, length = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "generic_name", nullable = true, length = 40)
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 16)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(name, users.name) &&
                Objects.equals(password, users.password) &&
                Objects.equals(genericName, users.genericName) &&
                Objects.equals(token, users.token) &&
                Objects.equals(createTime, users.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, genericName, token, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public Groups getGroupsByGroupId() {
        return groupsByGroupId;
    }

    public void setGroupsByGroupId(Groups groupsByGroupId) {
        this.groupsByGroupId = groupsByGroupId;
    }

    public Users() {
    }

    public Users(int id) {
        this.id = id;
    }

    public Users(int id, String name, String password, String genericName, String token, Timestamp createTime, Groups groupsByGroupId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.genericName = genericName;
        this.token = token;
        this.createTime = createTime;
        this.groupsByGroupId = groupsByGroupId;
    }

    public Users(int id, String name, String password, String genericName, String token, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.genericName = genericName;
        this.token = token;
        this.createTime = createTime;
    }

    public Users(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", genericName='" + genericName + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                ", groupsByGroupId=" + groupsByGroupId +
                '}';
    }
}
