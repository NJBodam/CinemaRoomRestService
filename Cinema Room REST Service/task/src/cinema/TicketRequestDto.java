package cinema;

public class TicketRequestDto {
    private Long row;
    private Long column;

    public TicketRequestDto(Long row, Long column) {
        this.row = row;
        this.column = column;
    }

    public TicketRequestDto() {
    }

    public Long getRow() {
        return row;
    }

    public void setRow(Long row) {
        this.row = row;
    }

    public Long getColumn() {
        return column;
    }

    public void setColumn(Long column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "TicketRequestDto{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
