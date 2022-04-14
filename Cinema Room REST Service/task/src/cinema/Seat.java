package cinema;

public class Seat {
    private Long row;
    private Long column;
    private Long price;

    private Seat(Long row, Long column, Long price) {
        this.row = row;
        this.column = column;
        this.price = column;
    }
    Seat() {

    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", column=" + column +
                ", price=" + price +
                '}';
    }
}
