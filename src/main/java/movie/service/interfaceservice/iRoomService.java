package movie.service.interfaceservice;

import movie.payload.dto.RoomDTO;
import movie.payload.request.RoomRequest;
import movie.payload.response.ResponseObject;

public interface iRoomService {
    public ResponseObject<RoomDTO> addRoom(RoomRequest request);
    public ResponseObject<RoomDTO> editRoom(int roomID, RoomRequest request);
    public ResponseObject<RoomDTO> deleteRoom(int roomID);
}
