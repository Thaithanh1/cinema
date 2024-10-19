package movie.service;

import movie.entity.Cinema;
import movie.payload.converter.CinemaConverter;
import movie.payload.dto.CinemaDTO;
import movie.payload.request.CinemaRequest;
import movie.payload.response.ResponseObject;
import movie.repository.CinemaRepository;
import movie.service.interfaceservice.iCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

@Service
public class CinemaService implements iCinemaService {
    @Autowired
    private CinemaRepository cinemaRepository;
    public CinemaConverter _converter;
    public ResponseObject<CinemaDTO> _cinemaResponseObject;

    public CinemaService() {
        _converter = new CinemaConverter();
        _cinemaResponseObject = new ResponseObject<CinemaDTO>();
    }

    public ResponseObject<CinemaDTO> addCinema(CinemaRequest request) {
        Cinema cn = _converter.addCinema(request);
        cinemaRepository.save(cn);
        return _cinemaResponseObject.responseSuccess("Them moi Cinema thanh cong", _converter.entityToCenemaDTO(cn));
    }

    public ResponseObject<CinemaDTO> editCinema(int cinemaID, CinemaRequest request) {
        var checkCinema = cinemaRepository.findById(cinemaID);
        if (checkCinema.isEmpty()) {
            return _cinemaResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Cinema khong ton tai", null);
        } else {
            Cinema cn = _converter.editCinema(checkCinema.get(), request);
            cinemaRepository.save(cn);
            return _cinemaResponseObject.responseSuccess("Sua Cinema thanh cong", _converter.entityToCenemaDTO(cn));
        }
    }

    public ResponseObject<CinemaDTO> deleteCinema(int cinemaID) {
        var checkCinema = cinemaRepository.findById(cinemaID);
        if (checkCinema.isEmpty()) {
            return _cinemaResponseObject.responseError(HttpURLConnection.HTTP_BAD_REQUEST, "Cinema khong ton tai", null);
        } else {
            cinemaRepository.delete(checkCinema.get());
            return _cinemaResponseObject.responseSuccess("Xoa cinema thanh cong", null);
        }
    }

    public List<Object[]>  getCinemaSalesReport(Date startDate, Date endDate) {
        return cinemaRepository.getCinemaSalesReport(startDate, endDate);
    }
}
