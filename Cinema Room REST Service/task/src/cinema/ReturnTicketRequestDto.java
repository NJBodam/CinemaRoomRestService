package cinema;

public class ReturnTicketRequestDto {
    private String token;

    public ReturnTicketRequestDto(String token) {
        this.token = token;
    }

    public ReturnTicketRequestDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ReturnTicketRequestDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
