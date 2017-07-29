package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 29 July 2017, 7:28 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class TokenOrm
{
    @Nullable @SerializedName("token")
    private String token;
    @Nullable @SerializedName("refresh")
    private String refresh;

    public TokenOrm()
    {
    }

    public TokenOrm(@Nullable String token, @Nullable String refresh)
    {
        this.token = token;
        this.refresh = refresh;
    }

    public String getToken()
    {
        return this.token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getRefresh()
    {
        return this.refresh;
    }

    public void setRefresh(String refresh)
    {
        this.refresh = refresh;
    }

    @Override public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof TokenOrm))
        {
            return false;
        }

        TokenOrm tokenOrm = (TokenOrm) o;

        if(getToken() != null ? !getToken().equals(tokenOrm.getToken()) : tokenOrm.getToken() != null)
        {
            return false;
        }
        return getRefresh() != null ? getRefresh().equals(tokenOrm.getRefresh()) : tokenOrm.getRefresh() == null;

    }

    @Override public int hashCode()
    {
        int result = getToken() != null ? getToken().hashCode() : 0;
        result = 31 * result + (getRefresh() != null ? getRefresh().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("TokenOrm{");
        sb.append("token='").append(token).append('\'');
        sb.append(", refresh='").append(refresh).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
