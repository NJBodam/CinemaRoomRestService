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
    private static final List<Integer> soldTickets = new ArrayList<>();

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        if (ticketRequestDto.getRow()  > 9
                || ticketRequestDto.getColumn() > 9
                || ticketRequestDto.getRow() < 1
                || ticketRequestDto.getColumn() < 1) {
            return getErrorDtoResponseEntity("The number of a row or a column is out of bounds!");
        }

        int ticketIndex = seatIdentifier(ticketRequestDto.getRow(), ticketRequestDto.getColumn());
        Seat seat = cinemaRoom.getAvailableSeats().get(seatIdentifier(ticketRequestDto.getRow(), ticketRequestDto.getColumn()));
        if (soldTickets.contains(ticketIndex)) {
            return getErrorDtoResponseEntity("The ticket has been already purchased!");
        }
        soldTickets.add(ticketIndex);
        return new ResponseEntity<>(seat, HttpStatus.OK);


    }

    private int seatIdentifier(long row, long column) {
        return row > 1 ? Math.toIntExact((row - 1) * 9 + column - 1) : Math.toIntExact(row * column - 1);
    }

    private ResponseEntity<?> getErrorDtoResponseEntity(String s) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(s);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
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
