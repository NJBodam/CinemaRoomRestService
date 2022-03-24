package cinema;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class ResponseDto {
    private Long totalRows;
    private Long totalColumns;
    private List<Seat> availableSeats;

    public ResponseDto(Long totalRows, Long totalColumns, List<Seat> availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public ResponseDto() {

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
    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "{" +
                "total_rows=" + totalRows +
                ", total_columns=" + totalColumns +
                ", available_seats=" + availableSeats +
                '}';
    }
}
