package movie.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "seattype")
public class SeatType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int seatTypeID;

    @Column(name = "nametype")
    private String nameType;

    @OneToMany(mappedBy = "seatType")
    @JsonManagedReference
    private List<Seat> seats;

    public int getSeatTypeID() {
        return seatTypeID;
    }

    public void setSeatTypeID(int seatTypeID) {
        this.seatTypeID = seatTypeID;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
