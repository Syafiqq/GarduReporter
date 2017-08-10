package app.freelancer.syafiqq.gardureporter.model.custom.android.location;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 15 July 2017, 9:34 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.location.Location;
import java.util.Observable;
import org.jetbrains.annotations.Nullable;

public class ObservableLocation extends Observable
{
    @Nullable Location location;

    public ObservableLocation(@Nullable Location location)
    {
        this.location = location;
    }

    public ObservableLocation()
    {
        this(null);
    }

    @Nullable public Location getLocation()
    {
        return this.location;
    }

    public void setLocation(@Nullable Location location)
    {
        this.location = location;
        super.setChanged();
        super.notifyObservers(location);
    }
}
