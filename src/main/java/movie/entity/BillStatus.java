package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "billstatus")
public class BillStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int billStatusID;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "billStatus")
    @JsonManagedReference
    private List<Bill> bills;

    public int getBillStatusID() {
        return billStatusID;
    }

    public void setBillStatusID(int billStatusID) {
        this.billStatusID = billStatusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}