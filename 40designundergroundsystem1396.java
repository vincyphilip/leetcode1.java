import java.util.*;

class UndergroundSystem {

    class CheckIn {
        String stationName;
        int time;

        CheckIn(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    class Trip {
        int totalTime;
        int count;

        Trip(int totalTime, int count) {
            this.totalTime = totalTime;
            this.count = count;
        }
    }

    Map<Integer, CheckIn> checkInMap;
    Map<String, Trip> tripMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        tripMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkInMap.get(id);

        String route = checkIn.stationName + "-" + stationName;
        int travelTime = t - checkIn.time;

        Trip trip = tripMap.getOrDefault(route, new Trip(0, 0));
        trip.totalTime += travelTime;
        trip.count++;

        tripMap.put(route, trip);
        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        Trip trip = tripMap.get(route);
        return (double) trip.totalTime / trip.count;
    }
}
