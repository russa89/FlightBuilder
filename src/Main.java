import com.gridnine.testing.*;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FlightFilterImpl();

        System.out.println("All Flights:" + flights);

        List<Flight> filteredFlights = filter.filter(flights);
        System.out.println("\nFiltered Flights:" + filteredFlights);
            }
        }

