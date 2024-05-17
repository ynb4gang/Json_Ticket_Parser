import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    void testTicketParsing() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("src/test/resources/fixtures/testFile.json"), TicketsWrapper.class).getTickets();

        assertEquals(15, tickets.size());

        Ticket firstTicket = tickets.get(0);
        assertEquals("LED", firstTicket.getOrigin());
        assertEquals("Санкт-Петербург", firstTicket.getOriginName());
        assertEquals("SVO", firstTicket.getDestination());
        assertEquals("Москва", firstTicket.getDestinationName());
        assertEquals("21.05.24", firstTicket.getDepartureDate());
        assertEquals("08:00", firstTicket.getDepartureTime());
        assertEquals("21.05.24", firstTicket.getArrivalDate());
        assertEquals("09:30", firstTicket.getArrivalTime());
        assertEquals("SU", firstTicket.getCarrier());
        assertEquals(0, firstTicket.getStops());
        assertEquals(5000, firstTicket.getPrice());


        Ticket lastTicket = tickets.get(tickets.size() - 1);
        assertEquals("VVO", lastTicket.getOrigin());
        assertEquals("Владивосток", lastTicket.getOriginName());
        assertEquals("TLV", lastTicket.getDestination());
        assertEquals("Тель-Авив", lastTicket.getDestinationName());
        assertEquals("12.05.18", lastTicket.getDepartureDate());
        assertEquals("06:10", lastTicket.getDepartureTime());
        assertEquals("12.05.18", lastTicket.getArrivalDate());
        assertEquals("12:15", lastTicket.getArrivalTime());
        assertEquals("S7", lastTicket.getCarrier());
        assertEquals(0, lastTicket.getStops());
        assertEquals(15400, lastTicket.getPrice());

    }
    @Test
    void testFilterTickets() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("src/test/resources/fixtures/testFile.json"), TicketsWrapper.class).getTickets();

        List<Ticket> filteredTickets = Utils.filterTickets(tickets, "VVO", "TLV");

        assertEquals(10, filteredTickets.size());
    }

    @Test
    void testCalculateMinFlightDurationByCarrier() throws IOException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("src/test/resources/fixtures/testFile.json"), TicketsWrapper.class).getTickets();

        Map<String, Long> minFlightDurationByCarrier = Utils.calculateMinFlightDurationByCarrier(tickets);


        assertEquals(180, minFlightDurationByCarrier.get("TK") / (60 * 1000));
        assertEquals(365, minFlightDurationByCarrier.get("S7")/ (60 * 1000));
        assertEquals(90, minFlightDurationByCarrier.get("SU")/ (60 * 1000));
    }

    @Test
    void testCalculateAveragePrice() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("src/test/resources/fixtures/testFile.json"), TicketsWrapper.class).getTickets();

        double averagePrice = Utils.calculateAveragePrice(tickets);

        assertEquals(13316.666666666666, averagePrice);
    }

    @Test
    void testCalculateMedianPrice() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Ticket> tickets = objectMapper.readValue(new File("src/test/resources/fixtures/testFile.json"), TicketsWrapper.class).getTickets();

        double medianPrice = Utils.calculateMedianPrice(tickets);


        assertEquals(14000.0, medianPrice);
    }
}
