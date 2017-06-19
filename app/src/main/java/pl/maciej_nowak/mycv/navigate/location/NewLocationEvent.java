package pl.maciej_nowak.mycv.navigate.location;

import android.location.Location;

/**
 * Created by Maciej on 19.06.2017.
 */

public class NewLocationEvent {

    private Location location;

    public NewLocationEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
