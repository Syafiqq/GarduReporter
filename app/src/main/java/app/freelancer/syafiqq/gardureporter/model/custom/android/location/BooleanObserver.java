package app.freelancer.syafiqq.gardureporter.model.custom.android.location;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 15 July 2017, 11:18 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import java.util.Observable;

public class BooleanObserver extends Observable
{
    public boolean bool;

    public BooleanObserver(boolean bool)
    {
        this.bool = bool;
    }

    public BooleanObserver()
    {
        this(false);
    }

    public boolean isBool()
    {
        return this.bool;
    }

    public void setBool(boolean bool)
    {
        this.bool = bool;
        super.setChanged();
        super.notifyObservers();
    }
}
