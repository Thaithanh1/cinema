package movie.payload.dto;

public class SeatDTO {
    private int number;
    private String line;
    private boolean isActive;
    private int seatStatusID;
    private int roomID;
    private int seatTypeID;

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

    public int getSeatStatusID() {
        return seatStatusID;
    }

    public void setSeatStatusID(int seatStatusID) {
        this.seatStatusID = seatStatusID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getSeatTypeID() {
        return seatTypeID;
    }

    public void setSeatTypeID(int seatTypeID) {
        this.seatTypeID = seatTypeID;
    }
}
