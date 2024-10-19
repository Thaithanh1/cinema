package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "billfood")
public class BillFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int billFoodID;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "billid", insertable = false, updatable = false)
    @JsonBackReference
    private Bill bill;
    @Column(name = "billid")
    private int billID;

    @ManyToOne
    @JoinColumn(name = "foodid", insertable = false, updatable = false)
    @JsonBackReference
    private Food food;

    @Column(name = "foodid")
    private int foodID;

    public int getBillFoodID() {
        return billFoodID;
    }

    public void setBillFoodID(int billFoodID) {
        this.billFoodID = billFoodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }
}
