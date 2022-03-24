package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CinemaController {



    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() throws Exception {
        int[][] cinemaSeats = new int[9][9];
        Seat seat;
        Cinema cinema = new Cinema();
        cinema.setTotalRows(9L);
        cinema.setTotalColumns(9L);
        List<Seat> seats = new ArrayList<>();

        int[][] dimArr = new int[9][9];

        int num = 1;
        for (int i = 0; i < dimArr.length; i++) {
            for (int j = 0; j < dimArr[i].length; j++) {
                dimArr[i][j] = num++;
            }
            num = 1;
        }

        for (int i = 0; i < dimArr.length; i++) {
            for (int j = 0; j < dimArr[i].length; j++) {
                seat = new Seat();
                //System.out.println("row:" + num);
                seat.setRow((long) num);
                //  System.out.println("column:" + dimArr[i][j]);
                seat.setColumn((long) dimArr[i][j]);
                seats.add(seat);

            }
            num++;
        }
        cinema.setSeatList(seats);
       // System.out.println(cinema);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setTotalColumns(9L);
        responseDto.setTotalRows(9L);
        responseDto.setAvailableSeats(seats);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
