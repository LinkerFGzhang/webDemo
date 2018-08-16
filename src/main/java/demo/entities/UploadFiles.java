package demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
//@JsonIgnoreProperties(value = {"usersById", "handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIgnoreProperties(value = {"usersById", "handler", "hibernateLazyInitializer", "fieldHandler"})
public class UploadFiles implements Serializable {
    private int id;
    private String type;
    private String name;
    private String fsName;
    private String suffix;
    private Timestamp createTime;
    private Users usersByUserId;
    private Collection<Users> usersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 16)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 40)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "fs_name", nullable = false, length = 16)
    public String getFsName() {
        return fsName;
    }

    public void setFsName(String fsName) {
        this.fsName = fsName;
    }

    @Basic
    @Column(name = "suffix", nullable = false, length = 8)
    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
        UploadFiles that = (UploadFiles) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(fsName, that.fsName) &&
                Objects.equals(suffix, that.suffix) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, name, fsName, suffix, createTime);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @OneToMany(mappedBy = "uploadFilesByHeadId")
    public Collection<Users> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<Users> usersById) {
        this.usersById = usersById;
    }

    public UploadFiles() {
    }

    public UploadFiles(int id, String type, String name, String fsName, String suffix, Timestamp createTime) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.fsName = fsName;
        this.suffix = suffix;
        this.createTime = createTime;
    }

    public UploadFiles(int id, String type, String name, String fsName, String suffix, Timestamp createTime, Users usersByUserId) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.fsName = fsName;
        this.suffix = suffix;
        this.createTime = createTime;
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString() {
        return "UploadFiles{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", fsName='" + fsName + '\'' +
                ", suffix='" + suffix + '\'' +
                ", createTime=" + createTime +
                ", usersByUserId=" + usersByUserId +
                '}';
    }
}
