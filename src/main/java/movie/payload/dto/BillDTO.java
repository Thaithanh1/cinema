package movie.payload.dto;

import java.util.Date;
import java.util.List;

public class BillDTO {
    private String name;
    private Date createTime;
    private Date updateTime;
    private double totalMoney;
    private String tradingCode;
    private boolean isActive;
    private int billStatusID;
    private int promotionID;
    private int customerID;
    private List<BillFoodDTO> billFoodDTOList;

    private List<BillTicketDTO> billTicketDTOList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getBillStatusID() {
        return billStatusID;
    }

    public void setBillStatusID(int billStatusID) {
        this.billStatusID = billStatusID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public List<BillFoodDTO> getBillFoodDTOList() {
        return billFoodDTOList;
    }

    public void setBillFoodDTOList(List<BillFoodDTO> billFoodDTOList) {
        this.billFoodDTOList = billFoodDTOList;
    }

    public List<BillTicketDTO> getBillTicketDTOList() {
        return billTicketDTOList;
    }

    public void setBillTicketDTOList(List<BillTicketDTO> billTicketDTOList) {
        this.billTicketDTOList = billTicketDTOList;
    }
}
