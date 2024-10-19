package movie.payload.dto;

import movie.entity.BillFood;
import movie.entity.BillTicket;

import java.util.List;

public class OrderDTO {
    private String name;
    private String tradingCode;
    private boolean isActive;
    private int billStatusID;
    private int customerID;
    private int promotionID;
    private List<BillTicket> billTickets;
    private List<BillFood> billFoods;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode = tradingCode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public int getBillStatusID() {
        return billStatusID;
    }

    public void setBillStatusID(int billStatusID) {
        this.billStatusID = billStatusID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public List<BillTicket> getBillTickets() {
        return billTickets;
    }

    public void setBillTickets(List<BillTicket> billTickets) {
        this.billTickets = billTickets;
    }

    public List<BillFood> getBillFoods() {
        return billFoods;
    }

    public void setBillFoods(List<BillFood> billFoods) {
        this.billFoods = billFoods;
    }
}
