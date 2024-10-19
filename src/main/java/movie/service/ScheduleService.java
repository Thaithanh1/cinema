package movie.service;

import movie.entity.Schedule;
import movie.payload.converter.ScheduleConverter;
import movie.payload.dto.FoodDTO;
import movie.payload.dto.ScheduleDTO;
import movie.payload.request.ScheduleRequest;
import movie.payload.response.ResponseObject;
import movie.repository.ScheduleRepository;
import movie.service.interfaceservice.iScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.Date;

@Service
public class ScheduleService implements iScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    private ScheduleConverter _converter;
    private ResponseObject<ScheduleDTO> _scheduleResponseObject;

    public ScheduleService() {
        _converter = new ScheduleConverter();
        _scheduleResponseObject = new ResponseObject<ScheduleDTO>();
    }

    public ResponseObject<ScheduleDTO> addSchedule(ScheduleRequest request) {
        if (isRoomAvailable(request.getRoomID(), request.getStartAt(), request.getEndAt())) {
            Schedule ch = _converter.addSchedule(request);
            scheduleRepository.save(ch);
            return _scheduleResponseObject.responseSuccess("Them moi schedule thanh cong", _converter.entityToSchedule(ch));
        } else {
            return _scheduleResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Room is already booked for the given time", null);
        }
    }

    public ResponseObject<ScheduleDTO> updateSchedule(int scheduleID, ScheduleRequest request) {
        var checkSchedule = scheduleRepository.findById(scheduleID);
        if (checkSchedule.isEmpty()) {
            return _scheduleResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Schedule khong ton tai", null);
        }else {
            if (isRoomAvailable(request.getRoomID(), request.getStartAt(), request.getEndAt())) {
                Schedule ch = _converter.editSchedule(checkSchedule.get(), request);
                scheduleRepository.save(ch);
                return _scheduleResponseObject.responseSuccess("Cap nhat thanh cong", _converter.entityToSchedule(ch));
            } else {
                return _scheduleResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Room is already booked for the given time", null);
            }
        }
    }

    public ResponseObject<ScheduleDTO> deleteSchedule(int scheduleID) {
        var checkSchedule = scheduleRepository.findById(scheduleID);
        if (checkSchedule.isEmpty()) {
            return _scheduleResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Schedule khong ton tai", null);
        } else {
            scheduleRepository.delete(checkSchedule.get());
            return _scheduleResponseObject.responseSuccess("Xoa schedule thanh cong", null);
        }
    }

    private boolean isRoomAvailable(Integer roomId, Date startAt, Date endAt) {
        return !scheduleRepository.existsByRoomIDAndStartAtLessThanEqualAndEndAtGreaterThanEqual(roomId, endAt, startAt);
    }
}
