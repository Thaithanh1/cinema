package movie.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int seatID;

    @Column(name = "number")
    private int number;

    @Column(name = "line")
    private String line;

    @Column(name = "isactive", columnDefinition = "bit")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "seatstatusid", insertable = false, updatable = false)
    @JsonBackReference
    private SeatStatus seatStatus;
    @Column(name = "seatstatusid")
    private int seatStatusID;

    @ManyToOne
    @JoinColumn(name = "roomid", insertable = false, updatable = false)
    @JsonBackReference
    private Room room;
    @Column(name = "roomid")
    private int roomID;

    @ManyToOne
    @JoinColumn(name = "seattypeid", insertable = false, updatable = false)
    @JsonBackReference
    private SeatType seatType;
    @Column(name = "seattypeid")
    private int seatTypeID;

    @OneToMany(mappedBy = "seat")
    @JsonManagedReference
    private List<Ticket> tickets;

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getSeatStatusID() {
        return seatStatusID;
    }

    public void setSeatStatusID(int seatStatusID) {
        this.seatStatusID = seatStatusID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getSeatTypeID() {
        return seatTypeID;
    }

    public void setSeatTypeID(int seatTypeID) {
        this.seatTypeID = seatTypeID;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}