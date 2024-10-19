package movie.service;

import movie.entity.Room;
import movie.payload.converter.RoomConverter;
import movie.payload.dto.RoomDTO;
import movie.payload.request.RoomRequest;
import movie.payload.response.ResponseObject;
import movie.repository.RoomRepository;
import movie.service.interfaceservice.iRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class RoomService implements iRoomService {
    @Autowired
    private RoomRepository roomRepository;
    public RoomConverter _converter;
    public ResponseObject<RoomDTO> _roomResponseObject;
    public RoomService() {
        _converter = new RoomConverter();
        _roomResponseObject = new ResponseObject<RoomDTO>();
    }

    public ResponseObject<RoomDTO> addRoom(RoomRequest request) {
        Room rm = _converter.addRoom(request);
        roomRepository.save(rm);
        return _roomResponseObject.responseSuccess("Them moi room thanh cong", _converter.entityToRoomDTO(rm));
    }

    public ResponseObject<RoomDTO> editRoom(int roomID, RoomRequest request) {
        var checkRoom = roomRepository.findById(roomID);
        if (checkRoom.isEmpty()) {
            return  _roomResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Room khong ton tai", null);
        } else {
            Room rm = _converter.editRoom(checkRoom.get(), request);
            roomRepository.save(rm);
            return _roomResponseObject.responseSuccess("Cap nhat room thanh cong", _converter.entityToRoomDTO(rm));
        }
    }

    public ResponseObject<RoomDTO> deleteRoom(int roomID) {
        var checkRoom = roomRepository.findById(roomID);
        if (checkRoom.isEmpty()) {
            return  _roomResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Room khong ton tai", null);
        } else {
            roomRepository.delete(checkRoom.get());
            return _roomResponseObject.responseSuccess("Xoa room thanh cong", null);
        }
    }
}
