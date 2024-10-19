package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rankcustomer")
public class RankCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int rankCustomerID;

    @Column(name = "point")
    private int point;

    @Column(name = "description")
    private String desciption;

    @Column(name = "name")
    private String name;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @OneToMany(mappedBy = "rankCustomer")
    @JsonManagedReference
    private List<Promotion> promotions;

    @OneToMany(mappedBy = "rankCustomer")
    @JsonManagedReference
    private List<User> users;

    public int getRankCustomerID() {
        return rankCustomerID;
    }

    public void setRankCustomerID(int rankCustomerID) {
        this.rankCustomerID = rankCustomerID;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
