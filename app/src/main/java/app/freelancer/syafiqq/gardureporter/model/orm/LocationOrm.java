package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 31 July 2017, 7:46 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.location.Location;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LocationOrm
{
    @Nullable @SerializedName("lat")
    private Double latitude;
    @Nullable @SerializedName("long")
    private Double longitude;

    public LocationOrm()
    {
    }

    public LocationOrm(@Nullable Double latitude, @Nullable Double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationOrm(@NotNull Location location)
    {
        this(location.getLatitude(), location.getLongitude());
    }

    public Double getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    @Override public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof LocationOrm))
        {
            return false;
        }

        LocationOrm that = (LocationOrm) o;

        if(getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null)
        {
            return false;
        }
        return getLongitude() != null ? getLongitude().equals(that.getLongitude()) : that.getLongitude() == null;

    }

    @Override public int hashCode()
    {
        int result = getLatitude() != null ? getLatitude().hashCode() : 0;
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("LocationOrm{");
        sb.append("latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
