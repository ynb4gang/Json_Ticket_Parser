import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TicketsWrapper ticketsWrapper = objectMapper.readValue(new File("src/main/resources/tickets.json"), TicketsWrapper.class);
            List<Ticket> tickets = ticketsWrapper.getTickets();

            List<Ticket> vvoToTlvTickets = Utils.filterTickets(tickets, "VVO", "TLV");

            Map<String, Long> minFlightDurationByCarrier = Utils.calculateMinFlightDurationByCarrier(vvoToTlvTickets);

            System.out.println("Minimum flight times for each airline carrier:");
            for (Map.Entry<String, Long> entry : minFlightDurationByCarrier.entrySet()) {
                System.out.println(entry.getKey() + ": " + (entry.getValue() / (60 * 1000)) + " minutes");
            }

            double averagePrice = Utils.calculateAveragePrice(vvoToTlvTickets);
            double medianPrice = Utils.calculateMedianPrice(vvoToTlvTickets);

            System.out.println("Average price: " + averagePrice);
            System.out.println("Median price: " + medianPrice);
            System.out.println("The difference between the average price and the median: " + (averagePrice - medianPrice));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
