package movie.service.interfaceservice;

import movie.entity.Schedule;
import movie.payload.dto.ScheduleDTO;
import movie.payload.request.ScheduleRequest;
import movie.payload.response.ResponseObject;

public interface iScheduleService {
    public ResponseObject<ScheduleDTO> addSchedule(ScheduleRequest request);
    public ResponseObject<ScheduleDTO> updateSchedule(int scheduleID, ScheduleRequest request);
    public ResponseObject<ScheduleDTO> deleteSchedule(int scheduleID);
}
