package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ticketID;

    @Column(name = "code")
    private String code;

    @Column(name = "priceticket")
    private double priceTicket;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "scheduleid", insertable = false, updatable = false)
    @JsonBackReference
    private Schedule schedule;
    @Column(name = "scheduleid")
    private int scheduleID;

    @ManyToOne
    @JoinColumn(name = "seatid", insertable = false, updatable = false)
    @JsonBackReference
    private Seat seat;
    @Column(name = "seatid")
    private int seatID;

    @OneToMany(mappedBy = "ticket")
    @JsonManagedReference
    private List<BillTicket> billTickets;

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public List<BillTicket> getBillTickets() {
        return billTickets;
    }

    public void setBillTickets(List<BillTicket> billTickets) {
        this.billTickets = billTickets;
    }
}
