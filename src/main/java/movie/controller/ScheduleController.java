package movie.controller;

import movie.entity.Schedule;
import movie.payload.dto.ScheduleDTO;
import movie.payload.request.ScheduleRequest;
import movie.payload.response.ResponseObject;
import movie.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<ScheduleDTO> createSchedule(@RequestBody ScheduleRequest request) {
        return scheduleService.addSchedule(request);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<ScheduleDTO> updateSchedule(@RequestParam int scheduleID, @RequestBody ScheduleRequest request) {
        return scheduleService.updateSchedule(scheduleID, request);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<ScheduleDTO> deleteSchedule(@RequestParam int scheduleID) {
        return scheduleService.deleteSchedule(scheduleID);
    }
}
