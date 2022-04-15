package cinema;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class Cinema {
    private Long totalRows;
    private Long totalColumns;
    private List<Ticket> availableTickets;

    public Cinema(Long totalRows, Long totalColumns, List<Ticket> availableTickets) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableTickets = availableTickets;
    }

    public Cinema() {

    }

    @JsonGetter(value = "total_rows")
    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    @JsonGetter(value = "total_columns")
    public Long getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(Long totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonGetter(value = "available_seats")
    public List<Ticket> getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(List<Ticket> availableTickets) {
        this.availableTickets = availableTickets;
    }

    @Override
    public String toString() {
        return "{" +
                "total_rows=" + totalRows +
                ", total_columns=" + totalColumns +
                ", available_seats=" + availableTickets +
                '}';
    }
}
