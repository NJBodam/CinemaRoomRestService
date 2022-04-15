package cinema;

import java.util.UUID;

public class Seat {

    private String token;
    private Ticket ticket;

    public Seat(String token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public Seat() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "token='" + token + '\'' +
                ", ticket=" + ticket +
                '}';
    }
}
