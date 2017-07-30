package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 31 July 2017, 5:56 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class JenisGarduOrm
{
    @Nullable @SerializedName("code")
    private String code;
    @Nullable @SerializedName("name")
    private String name;

    public JenisGarduOrm()
    {
    }

    public JenisGarduOrm(@Nullable String code, @Nullable String name)
    {
        this.code = code;
        this.name = name;
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
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
        if(!(o instanceof JenisGarduOrm))
        {
            return false;
        }

        JenisGarduOrm that = (JenisGarduOrm) o;

        if(getCode() != null ? !getCode().equals(that.getCode()) : that.getCode() != null)
        {
            return false;
        }
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;

    }

    @Override public int hashCode()
    {
        int result = getCode() != null ? getCode().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("JenisGarduOrm{");
        sb.append("code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
