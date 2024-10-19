package movie.service.interfaceservice;

import movie.payload.dto.CinemaDTO;
import movie.payload.request.CinemaRequest;
import movie.payload.response.ResponseObject;

import java.util.Date;
import java.util.List;

public interface iCinemaService {
    public ResponseObject<CinemaDTO> addCinema(CinemaRequest request);
    public ResponseObject<CinemaDTO> editCinema(int cinemaID, CinemaRequest request);
    public ResponseObject<CinemaDTO> deleteCinema(int cinemaID);
    public List<Object[]> getCinemaSalesReport(Date startDate, Date endDate);
}
