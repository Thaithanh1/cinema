package movie.controller;

import movie.payload.dto.CinemaDTO;
import movie.payload.dto.MovieDTO;
import movie.payload.request.CinemaRequest;
import movie.payload.response.ResponseObject;
import movie.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cinema/")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @RequestMapping(value = "addcinema", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> addCinema(@RequestBody CinemaRequest request) {
        return cinemaService.addCinema(request);
    }
    @RequestMapping(value = "editcinema", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> editCinema(@RequestParam int cinemaID, @RequestBody CinemaRequest request) {
        return cinemaService.editCinema(cinemaID, request);
    }
    @RequestMapping(value = "deletecinema", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> deleteCinema(@RequestParam int cinemaID) {
        return cinemaService.deleteCinema(cinemaID);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Object[]>> searchCinema(
            @RequestParam String startDate,
            @RequestParam String endDate) throws ParseException {
            List<Object[]> result = cinemaService.getCinemaSalesReport(convertStringToDate(startDate), convertStringToDate(endDate));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    public static Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return formatter.parse(dateString);
    }
}

