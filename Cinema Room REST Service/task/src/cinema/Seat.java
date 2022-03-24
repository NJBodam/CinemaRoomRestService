package cinema;

public class Seat {
    private Long row;
    private Long column;

    private Seat(Long row, Long column) {
        this.row = row;
        this.column = column;
    }
    Seat() {

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
        return "{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
