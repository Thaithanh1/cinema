package movie.payload.converter;

import movie.entity.Cinema;
import movie.payload.dto.CinemaDTO;
import movie.payload.request.CinemaRequest;

public class CinemaConverter {
    public CinemaDTO entityToCenemaDTO(Cinema cinema) {
        CinemaDTO dto = new CinemaDTO();
        dto.setAddress(cinema.getAddress());
        dto.setDescription(cinema.getDescription());
        dto.setCode(cinema.getCode());
        dto.setNameOfCinema(cinema.getNameOfCinema());
        dto.setIsActive(cinema.isActive());
        return dto;
    }

    public Cinema addCinema(CinemaRequest request) {
        Cinema cinema = new Cinema();
        cinema.setAddress(request.getAddress());
        cinema.setDescription(request.getDescription());
        cinema.setCode(request.getCode());
        cinema.setNameOfCinema(request.getNameOfCinema());
        cinema.setIsActive(request.isActive());
        return cinema;
    }

    public Cinema editCinema(Cinema cinema, CinemaRequest request) {
        cinema.setAddress(request.getAddress());
        cinema.setDescription(request.getDescription());
        cinema.setCode(request.getCode());
        cinema.setNameOfCinema(request.getNameOfCinema());
        cinema.setIsActive(request.isActive());
        return cinema;
    }
}
