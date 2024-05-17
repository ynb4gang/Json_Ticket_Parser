import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static List<Ticket> filterTickets(List<Ticket> tickets, String origin, String destination) {
        return tickets.stream()
                .filter(ticket -> origin.equals(ticket.getOrigin()) && destination.equals(ticket.getDestination()))
                .collect(Collectors.toList());
    }

    public static Map<String, Long> calculateMinFlightDurationByCarrier(List<Ticket> tickets) throws ParseException {
        Map<String, Long> minFlightDurationByCarrier = new HashMap<>();
        for (Ticket ticket : tickets) {
            String carrier = ticket.getCarrier();
            long flightDuration = ticket.getFlightDuration();
            minFlightDurationByCarrier.put(carrier, Math.min(minFlightDurationByCarrier.getOrDefault(carrier, Long.MAX_VALUE), flightDuration));
        }
        return minFlightDurationByCarrier;
    }

    public static double calculateAveragePrice(List<Ticket> tickets) {
        return tickets.stream()
                .mapToInt(Ticket::getPrice)
                .average()
                .orElse(0);
    }

    public static double calculateMedianPrice(List<Ticket> tickets) {
        List<Integer> prices = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }
}
