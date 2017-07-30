package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 30 July 2017, 11:34 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class GarduPenyulangOrm
{
    @Nullable @SerializedName("id")
    private Integer id;
    @Nullable @SerializedName("name")
    private String name;

    public GarduPenyulangOrm()
    {
    }

    public GarduPenyulangOrm(@Nullable Integer id, @Nullable String name)
    {
        this.id = id;
        this.name = name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof GarduPenyulangOrm))
        {
            return false;
        }

        GarduPenyulangOrm that = (GarduPenyulangOrm) o;

        if(getId() != null ? !getId().equals(that.getId()) : that.getId() != null)
        {
            return false;
        }
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("GarduPenyulangOrm{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
