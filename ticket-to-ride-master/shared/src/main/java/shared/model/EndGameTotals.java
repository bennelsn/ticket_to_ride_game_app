package shared.model;

/**
 * Created by mikeporet on 3/24/18.
 */

public class EndGameTotals {
    private int destination_card_points;
    private int unclaimed_destination_points;
    private int claimed_route_points;
    private int longest_route_bonus;
    private int total_points;

    public EndGameTotals() {
        destination_card_points = 0;
        unclaimed_destination_points = 0;
        claimed_route_points = 0;
        longest_route_bonus = 0;
        total_points = 0;
    }

    public int getDestination_card_points() {
        return destination_card_points;
    }

    public int getUnclaimed_destination_points() {
        return unclaimed_destination_points;
    }

    public int getClaimed_route_points() {
        return claimed_route_points;
    }

    public int getLongest_route_bonus() {
        return longest_route_bonus;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setDestination_card_points(int destination_card_points) {
        this.destination_card_points = destination_card_points;
    }

    public void setUnclaimed_destination_points(int unclaimed_destination_points) {
        this.unclaimed_destination_points = unclaimed_destination_points;
    }

    public void setClaimed_route_points(int claimed_route_points) {
        this.claimed_route_points = claimed_route_points;
    }

    public void setLongest_route_bonus(int longest_route_bonus) {
        this.longest_route_bonus = longest_route_bonus;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }
}
