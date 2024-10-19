package movie.payload.converter;

import movie.entity.Schedule;
import movie.payload.dto.ScheduleDTO;
import movie.payload.request.ScheduleRequest;

public class ScheduleConverter {
    public ScheduleDTO entityToSchedule(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setPrice(schedule.getPrice());
        dto.setStartAt(schedule.getStartAt());
        dto.setEndAt(schedule.getEndAt());
        dto.setCode(schedule.getCode());
        dto.setName(schedule.getName());
        dto.setActive(schedule.isActive());
        dto.setMovieID(schedule.getMovieID());
        dto.setRoomID(schedule.getRoomID());
        return dto;
    }

    public Schedule addSchedule(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setPrice(request.getPrice());
        schedule.setStartAt(request.getStartAt());
        schedule.setEndAt(request.getEndAt());
        schedule.setCode(request.getCode());
        schedule.setName(request.getName());
        schedule.setActive(request.isActive());
        schedule.setMovieID(request.getMovieID());
        schedule.setRoomID(request.getRoomID());
        return schedule;
    }

    public Schedule editSchedule(Schedule schedule, ScheduleRequest request) {
        schedule.setPrice(request.getPrice());
        schedule.setStartAt(request.getStartAt());
        schedule.setEndAt(request.getEndAt());
        schedule.setCode(request.getCode());
        schedule.setName(request.getName());
        schedule.setActive(request.isActive());
        schedule.setMovieID(request.getMovieID());
        schedule.setRoomID(request.getRoomID());
        return schedule;
    }


}
