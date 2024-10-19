package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "seatstatus")
public class SeatStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int seatStatusID;

    @Column(name = "code")
    private String code;

    @Column(name = "namestatus")
    private String nameStatus;

    @OneToMany(mappedBy = "seatStatus")
    @JsonManagedReference
    private List<Seat> seats;

    public int getSeatStatusID() {
        return seatStatusID;
    }

    public void setSeatStatusID(int seatStatusID) {
        this.seatStatusID = seatStatusID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
