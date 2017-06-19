package pl.maciej_nowak.mycv.navigate.location;

import com.squareup.otto.Bus;

/**
 * Created by Maciej on 19.06.2017.
 */

public final class BusProvider {

    private static Bus BUS;

    public static Bus getInstance() {
        if(BUS == null) BUS = new Bus();
        return BUS;
    }

    private BusProvider() {}

}
