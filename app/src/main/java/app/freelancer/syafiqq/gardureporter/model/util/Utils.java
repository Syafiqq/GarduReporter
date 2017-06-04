package app.freelancer.syafiqq.gardureporter.model.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * This <GarduReporter> project created by :
 * Name         : syafiq
 * Date / Time  : 04 June 2017, 2:36 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class Utils
{
    public static final String KEY_REQUESTING_LOCATION_UPDATES = "requesting_locaction_updates";

    /**
     * Returns true if requesting location updates, otherwise returns false.
     *
     * @param context The {@link Context}.
     */
    public static boolean requestingLocationUpdates(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_REQUESTING_LOCATION_UPDATES, false);
    }

    /**
     * Stores the location updates state in SharedPreferences.
     *
     * @param requestingLocationUpdates The location updates state.
     */
    public static void setRequestingLocationUpdates(Context context, boolean requestingLocationUpdates)
    {
        PreferenceManager.getDefaultSharedPreferences(context)
                         .edit()
                         .putBoolean(KEY_REQUESTING_LOCATION_UPDATES, requestingLocationUpdates)
                         .apply();
    }
}
