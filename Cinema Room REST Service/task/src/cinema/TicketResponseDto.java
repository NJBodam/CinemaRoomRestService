package cinema;

public class TicketResponseDto {
    private Long row;
    private Long column;
    private Long price;

    public TicketResponseDto(Long row, Long column, Long price) {
        this.row = row;
        this.column = column;
        this.price = price;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TicketResponseDto{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                '}';
    }
}
