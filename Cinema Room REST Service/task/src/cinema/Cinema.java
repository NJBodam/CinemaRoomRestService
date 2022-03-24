package cinema;

import java.util.List;

public class Cinema {
    private Long totalRows;
    private Long totalColumns;
    private List<Seat> availableSeats;

    public Cinema(Long totalRows, Long totalColumns, List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public Cinema() {

    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Long getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(Long totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getSeatList() {
        return availableSeats;
    }

    public void setSeatList(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "total_rows=" + totalRows +
                ", total_columns=" + totalColumns +
                ", available_seats=" + availableSeats +
                '}';
    }
}
