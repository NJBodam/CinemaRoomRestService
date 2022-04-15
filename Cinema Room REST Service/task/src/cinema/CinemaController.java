package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class CinemaController {

    private static Cinema cinemaRoom;
    private static final List<Integer> soldTickets = new ArrayList<>();

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        if (ticketRequestDto.getRow()  > 9
                || ticketRequestDto.getColumn() > 9
                || ticketRequestDto.getRow() < 1
                || ticketRequestDto.getColumn() < 1) {
            return getErrorDtoResponseEntity("The number of a row or a column is out of bounds!");
        }

        int ticketIndex = ticketIdentifier(ticketRequestDto.getRow(), ticketRequestDto.getColumn());
        Ticket ticket = cinemaRoom.getAvailableTickets().get(ticketIdentifier(ticketRequestDto.getRow(), ticketRequestDto.getColumn()));
        if (soldTickets.contains(ticketIndex)) {
            return getErrorDtoResponseEntity("The ticket has been already purchased!");
        }
        soldTickets.add(ticketIndex);
        return new ResponseEntity<>(ticket, HttpStatus.OK);


    }

    private int ticketIdentifier(long row, long column) {
        return row > 1 ? Math.toIntExact((row - 1) * 9 + column - 1) : Math.toIntExact(row * column - 1);
    }

    private ResponseEntity<?> getErrorDtoResponseEntity(String s) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(s);
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() throws Exception {
        Ticket ticket;
        List<Ticket> tickets = new ArrayList<>();

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
                        ticket = new Ticket();
                        ticket.setRow((long) num);
                        if (num <= 4) {
                            ticket.setPrice(10L);
                        } else {
                            ticket.setPrice(8L);
                        }
                        ticket.setColumn((long) cinemaSeats[i][j]);
                        tickets.add(ticket);

                    }
                    num++;
                }
                Cinema cinema = new Cinema();
                cinema.setTotalColumns(9L);
                cinema.setTotalRows(9L);
                cinema.setAvailableTickets(tickets);
                cinemaRoom = cinema;
            }

        } catch (NullPointerException e) {
            System.err.println(e.getClass().getSimpleName());
        }

        return new ResponseEntity<>(cinemaRoom, HttpStatus.OK);
    }
}
