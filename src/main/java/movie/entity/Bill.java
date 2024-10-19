package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int billID;

    @Column(name = "totalmoney")
    private double totalMoney;

    @Column(name = "tradingcode")
    private String tradingCode;

    @Column(name = "createtime")
    private Date createTime;

    @Column(name = "name")
    private String name;

    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "customerid", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "customerid")
    private int customerID;

    @ManyToOne
    @JoinColumn(name = "promotionid", insertable = false, updatable = false)
    @JsonBackReference
    private Promotion promotion;

    @Column(name = "promotionid")
    private int promotionID;

    @ManyToOne
    @JoinColumn(name = "billstatusid", insertable = false, updatable = false)
    @JsonBackReference
    private BillStatus billStatus;
    @Column(name = "billstatusid")
    private int billStatusID;

    @OneToMany(mappedBy = "bill")
    @JsonManagedReference
    private List<BillFood> billFoods;

    @OneToMany(mappedBy = "bill")
    @JsonManagedReference
    private List<BillTicket> billTickets;

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode = tradingCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public int getBillStatusID() {
        return billStatusID;
    }

    public void setBillStatusID(int billStatusID) {
        this.billStatusID = billStatusID;
    }

    public List<BillFood> getBillFoods() {
        return billFoods;
    }

    public void setBillFoods(List<BillFood> billFoods) {
        this.billFoods = billFoods;
    }

    public List<BillTicket> getBillTickets() {
        return billTickets;
    }

    public void setBillTickets(List<BillTicket> billTickets) {
        this.billTickets = billTickets;
    }
}