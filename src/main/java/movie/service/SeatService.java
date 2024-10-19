package movie.service;

import movie.entity.Seat;
import movie.payload.converter.SeatConverter;
import movie.payload.dto.SeatDTO;
import movie.payload.request.SeatRequest;
import movie.payload.response.ResponseObject;
import movie.repository.SeatRepository;
import movie.service.interfaceservice.iSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class SeatService implements iSeatService {
    @Autowired
    private SeatRepository seatRepository;
    private SeatConverter _converter;
    private ResponseObject<SeatDTO> _seatResponseObject;
    public SeatService() {
        _converter = new SeatConverter();
        _seatResponseObject = new ResponseObject<SeatDTO>();
    }

    public ResponseObject<SeatDTO> addSeat(SeatRequest reqpuest) {
        Seat st = _converter.addSeat(reqpuest);
        seatRepository.save(st);
        return _seatResponseObject.responseSuccess("Them moi seat thanh cong", _converter.entityToSeatDTO(st));
    }

    public ResponseObject<SeatDTO> editSeat(int seatID, SeatRequest reqpuest) {
        var checkSeat = seatRepository.findById(seatID);
        if (checkSeat.isEmpty()) {
            return _seatResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Seat khong ton tai", null);
        } else {
            Seat st = _converter.editSeat(checkSeat.get(), reqpuest);
            seatRepository.save(st);
            return _seatResponseObject.responseSuccess("Sua seat thanh cong", _converter.entityToSeatDTO(st));
        }
    }

    public ResponseObject<SeatDTO> deleteSeat(int seatID) {
        var checkSeat = seatRepository.findById(seatID);
        if (checkSeat.isEmpty()) {
            return _seatResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST,"Seat khong ton tai", null);
        } else {
            seatRepository.delete(checkSeat.get());
            return _seatResponseObject.responseSuccess("Xoa seat thanh cong", null);
        }
    }
}