package movie.service.interfaceservice;

import movie.payload.dto.SeatDTO;
import movie.payload.request.SeatRequest;
import movie.payload.response.ResponseObject;

public interface iSeatService {
    public ResponseObject<SeatDTO> addSeat(SeatRequest reqpuest);
    public ResponseObject<SeatDTO> editSeat(int seatID, SeatRequest reqpuest);
    public ResponseObject<SeatDTO> deleteSeat(int seatID);
}
