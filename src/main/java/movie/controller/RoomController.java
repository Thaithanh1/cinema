package movie.controller;

import movie.payload.dto.RoomDTO;
import movie.payload.request.RoomRequest;
import movie.payload.response.ResponseObject;
import movie.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room/")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "addroom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RoomDTO> addRoom(@RequestBody RoomRequest request) {
        return roomService.addRoom(request);
    }
    @RequestMapping(value = "editroom", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RoomDTO> editRoom(@RequestParam int roomID, @RequestBody RoomRequest request) {
        return roomService.editRoom(roomID, request);
    }
    @RequestMapping(value = "deleteroom", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RoomDTO> deleteRoom(@RequestParam int roomID) {
        return roomService.deleteRoom(roomID);
    }
}