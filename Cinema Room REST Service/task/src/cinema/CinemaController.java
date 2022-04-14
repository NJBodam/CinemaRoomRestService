package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CinemaController {

    private static ResponseDto cinemaRoom;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        Long ticketIndex = ticketRequestDto.getRow() * ticketRequestDto.getColumn();
        Seat seat = cinemaRoom.getAvailableSeats().get(Math.toIntExact(ticketIndex) - 1);
        Long seatIndex = seat.getRow() * seat.getColumn();
        if (!seatIndex.equals(ticketIndex)) {
            return
        }
        return new ResponseEntity<>(ticketRequestDto, HttpStatus.CREATED);
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() throws Exception {
        Seat seat;
        List<Seat> seats = new ArrayList<>();

        try {
            if (cinemaRoom == null) {
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
                        if (num <= 4) {
                            seat.setPrice(10L);
                        } else {
                            seat.setPrice(8L);
                        }
                        seat.setColumn((long) cinemaSeats[i][j]);
                        seats.add(seat);

                    }
                    num++;
                }
                ResponseDto responseDto = new ResponseDto();
                responseDto.setTotalColumns(9L);
                responseDto.setTotalRows(9L);
                responseDto.setAvailableSeats(seats);
                cinemaRoom = responseDto;
            }

        } catch (NullPointerException e) {
            System.err.println(e.getClass().getSimpleName());
        }

        return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }
}
