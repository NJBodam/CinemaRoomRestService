package cinema;

import com.fasterxml.jackson.annotation.JsonGetter;

public class ReturnedTicket {
    private Ticket returnedTicket;

    public ReturnedTicket(Ticket returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public ReturnedTicket() {
    }

    @JsonGetter(value = "returned_ticket")
    public Ticket getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Ticket returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    @Override
    public String toString() {
        return "ReturnedTicket{" +
                "returnedTicket=" + returnedTicket +
                '}';
    }
}
