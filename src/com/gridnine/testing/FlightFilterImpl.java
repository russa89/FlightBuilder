package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFilterImpl implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>(flights);

        // departure time is before recent time
        filteredFlights.removeIf(flight -> flight.getSegments().get(0).getDepartureDate()
                .isBefore(LocalDateTime.now()));

        //arrival segments	are earlier than departure date
        filteredFlights.removeIf(flight -> flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));

        //flights with total landing time is more than 2 hours
        filteredFlights.removeIf(flight -> {
            long totalGroundTime = 0;
            List<Segment> segments = flight.getSegments();

            for (int i = 0; i < segments.size() - 1; i++) {
                totalGroundTime = totalGroundTime + Duration.between(segments.get(i).getArrivalDate(),
                        segments.get(i + 1).getDepartureDate()).toHours();
            }

            return totalGroundTime > 2;
        });

        return filteredFlights;

    }
}
