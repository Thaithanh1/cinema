package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "billticket")
public class BillTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billticket")
    private int billTicketID;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "billid", insertable = false, updatable = false)
    @JsonBackReference
    private Bill bill;
    @Column(name = "billid")
    private int billID;

    @ManyToOne
    @JoinColumn(name = "ticketid", insertable = false, updatable = false)
    @JsonBackReference
    private Ticket ticket;
    @Column(name = "ticketid")
    private int ticketID;

    public int getBillTicketID() {
        return billTicketID;
    }

    public void setBillTicketID(int billTicketID) {
        this.billTicketID = billTicketID;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }
}
