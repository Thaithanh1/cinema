package movie.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "generalsetting")
public class GeneralSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int generalSettingID;

    @Column(name = "breaktime")
    private Date breakTime;

    @Column(name = "businesshours")
    private int businessHours;

    @Column(name = "closetime")
    private Date closeTime;

    @Column(name = "fixedticketprice")
    private double fixedTicketPrice;

    @Column(name = "percentday")
    private int percentDay;

    @Column(name = "percentweek")
    private int percentWeek;

    @Column(name = "timebegintochange")
    private Date timeBeginToChange;

    public int getGeneralSettingID() {
        return generalSettingID;
    }

    public void setGeneralSettingID(int generalSettingID) {
        this.generalSettingID = generalSettingID;
    }

    public Date getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Date breakTime) {
        this.breakTime = breakTime;
    }

    public int getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(int businessHours) {
        this.businessHours = businessHours;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public double getFixedTicketPrice() {
        return fixedTicketPrice;
    }

    public void setFixedTicketPrice(double fixedTicketPrice) {
        this.fixedTicketPrice = fixedTicketPrice;
    }

    public int getPercentDay() {
        return percentDay;
    }

    public void setPercentDay(int percentDay) {
        this.percentDay = percentDay;
    }

    public int getPercentWeek() {
        return percentWeek;
    }

    public void setPercentWeek(int percentWeek) {
        this.percentWeek = percentWeek;
    }

    public Date getTimeBeginToChange() {
        return timeBeginToChange;
    }

    public void setTimeBeginToChange(Date timeBeginToChange) {
        this.timeBeginToChange = timeBeginToChange;
    }
}
