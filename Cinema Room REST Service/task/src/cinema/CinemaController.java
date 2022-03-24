package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CinemaController {

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {

        return new ResponseEntity<>(ticketRequestDto, HttpStatus.CREATED);
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() throws Exception {
        Seat seat;
        List<Seat> seats = new ArrayList<>();

        int[][] cinemaSeats = new int[9][9];

        int num = 1;
        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                cinemaSeats[i][j] = num++;
            }
            num = 1;
        }

        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                seat = new Seat();
                seat.setRow((long) num);
                seat.setColumn((long) cinemaSeats[i][j]);
                seats.add(seat);

            }
            num++;
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setTotalColumns(9L);
        responseDto.setTotalRows(9L);
        responseDto.setAvailableSeats(seats);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
