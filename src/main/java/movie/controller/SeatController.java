package movie.controller;

import movie.payload.dto.SeatDTO;
import movie.payload.request.SeatRequest;
import movie.payload.response.ResponseObject;
import movie.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat/")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @RequestMapping(value = "addseat", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SeatDTO> addSeat(@RequestBody SeatRequest request) {
        return seatService.addSeat(request);
    }

    @RequestMapping(value = "editseat", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SeatDTO> editSeat(@RequestParam int seatID, @RequestBody SeatRequest request) {
        return seatService.editSeat(seatID, request);
    }

    @RequestMapping(value = "deleteseat", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SeatDTO> deleteSeat(@RequestParam int seatID) {
        return seatService.deleteSeat(seatID);
    }
}