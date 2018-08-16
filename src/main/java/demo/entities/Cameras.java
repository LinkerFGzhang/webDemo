package demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Cameras implements Serializable {
    private int id;
    private String name;
    private String description;
    private String url;
    private int width;
    private int height;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Users usersByUserId;

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

    @Basic
    @Column(name = "url", nullable = false, length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "width", nullable = false)
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = false)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "longitude", nullable = false, precision = 6)
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 6)
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cameras cameras = (Cameras) o;
        return id == cameras.id &&
                width == cameras.width &&
                height == cameras.height &&
                Objects.equals(name, cameras.name) &&
                Objects.equals(description, cameras.description) &&
                Objects.equals(url, cameras.url) &&
                Objects.equals(address, cameras.address) &&
                Objects.equals(longitude, cameras.longitude) &&
                Objects.equals(latitude, cameras.latitude);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, url, width, height, address, longitude, latitude);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @Override
    public String toString() {
        return "Cameras{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", address='" + address + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", usersByUserId=" + usersByUserId +
                '}';
    }

    public Cameras() {
    }

    public Cameras(int id, String name, String description, String url, int width, int height, String address, BigDecimal longitude, BigDecimal latitude, Users usersByUserId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.width = width;
        this.height = height;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.usersByUserId = usersByUserId;
    }

    public Cameras(int id, String name, String description, String url, int width, int height, String address, BigDecimal longitude, BigDecimal latitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.width = width;
        this.height = height;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
