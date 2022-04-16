package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class CinemaController {

    private static Cinema cinemaRoom;
    private static final List<Seat> soldSeats = new ArrayList<>();
    private static final List<Seat> availableSeats = new ArrayList<>();
    private static final Statistics stats = new Statistics(0, 81, 0);

    private static final String ERR_ALREADY_PURCHASED = "The ticket has been already purchased!";
    private static final String ERR_OUT_OF_BOUNDS = "The number of a row or a column is out of bounds!";
    private static final String ERR_WRONG_PASSWORD = "The password is wrong!";
    private static final String ERR_WRONG_TOKEN = "Wrong token!";
    private static final String PASSWORD = "super_secret";

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        if (ticketRequestDto.getRow()  > 9
                || ticketRequestDto.getColumn() > 9
                || ticketRequestDto.getRow() < 1
                || ticketRequestDto.getColumn() < 1) {
            return getErrorDtoResponseEntity(ERR_OUT_OF_BOUNDS, HttpStatus.BAD_REQUEST);
        }

        Seat seat = availableSeats.get(ticketIdentifier(ticketRequestDto.getRow(), ticketRequestDto.getColumn()));
        if (soldSeats.contains(seat)) {
            return getErrorDtoResponseEntity(ERR_ALREADY_PURCHASED,  HttpStatus.BAD_REQUEST);
        }
        soldSeats.add(seat);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    private int ticketIdentifier(long row, long column) {
        return row > 1 ? Math.toIntExact((row - 1) * 9 + column - 1) : Math.toIntExact(row * column - 1);
    }

    private ResponseEntity<?> getErrorDtoResponseEntity(String s, HttpStatus h) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError(s);
        return new ResponseEntity<>(errorDto, h);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody ReturnTicketRequestDto returnTicketReq) {
        Optional<Seat> optionalSeat = soldSeats.stream().filter(x -> x.getToken().equals(returnTicketReq.getToken())).findFirst();
        ReturnedTicket rt = new ReturnedTicket();
        if(optionalSeat.isEmpty()) {
            return getErrorDtoResponseEntity(ERR_WRONG_TOKEN, HttpStatus.BAD_REQUEST);
        }
        soldSeats.remove(optionalSeat.get());
        rt.setReturnedTicket(optionalSeat.orElseThrow().getTicket());
        return new ResponseEntity<>(rt, HttpStatus.OK);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> returnTicket(@RequestParam(value = "password", required = false) String password) {
        Optional<String> pw = Optional.ofNullable(password);

        if (pw.isPresent() && pw.get().equals(PASSWORD)) {
            // return getErrorDtoResponseEntity(ERR_WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
            stats.setNumberOfAvailableSeats(81 - soldSeats.size());
            int totalIncome = soldSeats.parallelStream().mapToInt(x -> Math.toIntExact(x.getTicket().getPrice())).sum();
            stats.setCurrentIncome(totalIncome);
            stats.setNumberOfPurchasedTickets(soldSeats.size());
            return new ResponseEntity<>(stats, HttpStatus.OK);
        }
        return getErrorDtoResponseEntity(ERR_WRONG_PASSWORD, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() throws Exception {
        Ticket ticket;
        List<Ticket> tickets = new ArrayList<>();
        Seat seat;

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
                        seat = new Seat();
                        seat.setTicket(ticket);
                        seat.setToken(UUID.randomUUID().toString());
                        availableSeats.add(seat);
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
