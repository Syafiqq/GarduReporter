package app.freelancer.syafiqq.gardureporter.model.dao;

import android.support.annotation.Nullable;

/**
 * This <GarduReporter> project created by :
 * Name         : syafiq
 * Date / Time  : 04 June 2017, 4:31 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class SubStationReport
{
    private String substation;
    private double voltage;
    private double current;
    @Nullable private Location location;

    public SubStationReport(String substation, double voltage, double current, @Nullable Location location)
    {
        this.substation = substation;
        this.voltage = voltage;
        this.current = current;
        this.location = location;
    }

    public SubStationReport()
    {
    }

    public String getSubstation()
    {
        return this.substation;
    }

    public void setSubstation(String substation)
    {
        this.substation = substation;
    }

    public double getVoltage()
    {
        return this.voltage;
    }

    public void setVoltage(double voltage)
    {
        this.voltage = voltage;
    }

    public double getCurrent()
    {
        return this.current;
    }

    public void setCurrent(double current)
    {
        this.current = current;
    }

    @Nullable
    public Location getLocation()
    {
        return this.location;
    }

    public void setLocation(@Nullable Location location)
    {
        this.location = location;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof SubStationReport))
        {
            return false;
        }

        SubStationReport that = (SubStationReport) o;

        if(Double.compare(that.getVoltage(), getVoltage()) != 0)
        {
            return false;
        }
        if(Double.compare(that.getCurrent(), getCurrent()) != 0)
        {
            return false;
        }
        if(getSubstation() != null ? !getSubstation().equals(that.getSubstation()) : that.getSubstation() != null)
        {
            return false;
        }
        return getLocation() != null ? getLocation().equals(that.getLocation()) : that.getLocation() == null;

    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = getSubstation() != null ? getSubstation().hashCode() : 0;
        temp = Double.doubleToLongBits(getVoltage());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getCurrent());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("SubStationReport{");
        sb.append("substation='").append(substation).append('\'');
        sb.append(", voltage=").append(voltage);
        sb.append(", current=").append(current);
        sb.append(", location=").append(location);
        sb.append('}');
        return sb.toString();
    }
}
