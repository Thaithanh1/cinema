package movie.payload.converter;

import movie.entity.Seat;
import movie.payload.dto.SeatDTO;
import movie.payload.request.SeatRequest;

public class SeatConverter {
    public SeatDTO entityToSeatDTO(Seat seat) {
        SeatDTO dto = new SeatDTO();
        dto.setNumber(seat.getNumber());
        dto.setLine(seat.getLine());
        dto.setIsActive(seat.isActive());
        dto.setSeatStatusID(seat.getSeatStatusID());
        dto.setRoomID(seat.getRoomID());
        dto.setSeatTypeID(seat.getSeatTypeID());
        return dto;
    }

    public Seat addSeat(SeatRequest reqpuest) {
        Seat seat = new Seat();
        seat.setNumber(reqpuest.getNumber());
        seat.setLine(reqpuest.getLine());
        seat.setIsActive(reqpuest.isActive());
        seat.setSeatStatusID(reqpuest.getSeatStatusID());
        seat.setRoomID(reqpuest.getRoomID());
        seat.setSeatTypeID(reqpuest.getSeatTypeID());
        return seat;
    }

    public Seat editSeat(Seat seat, SeatRequest reqpuest) {
        seat.setNumber(reqpuest.getNumber());
        seat.setLine(reqpuest.getLine());
        seat.setIsActive(reqpuest.isActive());
        seat.setSeatStatusID(reqpuest.getSeatStatusID());
        seat.setRoomID(reqpuest.getRoomID());
        seat.setSeatTypeID(reqpuest.getSeatTypeID());
        return seat;
    }
}
