import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

class TicketsWrapper {
    @JsonProperty("tickets")
    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
