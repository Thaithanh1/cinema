package movie.payload.converter;

import movie.entity.Room;
import movie.payload.dto.RoomDTO;
import movie.payload.request.RoomRequest;

public class RoomConverter {
    public RoomDTO entityToRoomDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setCapacity(room.getCapacity());
        dto.setType(room.getType());
        dto.setDescription(room.getDescription());
        dto.setCode(room.getCode());
        dto.setName(room.getName());
        dto.setIsActive(room.isActive());
        dto.setCinemaID(room.getCinemaID());
        return dto;
    }

    public Room addRoom(RoomRequest request) {
        Room room = new Room();
        room.setCapacity(request.getCapacity());
        room.setType(request.getType());
        room.setDescription(request.getDescription());
        room.setCode(request.getCode());
        room.setName(request.getName());
        room.setIsActive(request.isActive());
        room.setCinemaID(request.getCinemaID());
        return room;
    }

    public Room editRoom(Room room, RoomRequest request) {
        room.setCapacity(request.getCapacity());
        room.setType(request.getType());
        room.setDescription(request.getDescription());
        room.setCode(request.getCode());
        room.setName(request.getName());
        room.setIsActive(request.isActive());
        room.setCinemaID(request.getCinemaID());
        return room;
    }
}
