package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 29 July 2017, 6:54 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class UserOrm
{
    @Nullable @SerializedName("id")
    private Integer id;
    @Nullable @SerializedName("identity")
    private String email;
    @Nullable @SerializedName("password")
    private String password;

    public UserOrm()
    {
    }

    public UserOrm(@Nullable Integer id, @Nullable String email, @Nullable String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if(!(o instanceof UserOrm))
        {
            return false;
        }

        UserOrm userOrm = (UserOrm) o;

        if(getId() != null ? !getId().equals(userOrm.getId()) : userOrm.getId() != null)
        {
            return false;
        }
        if(getEmail() != null ? !getEmail().equals(userOrm.getEmail()) : userOrm.getEmail() != null)
        {
            return false;
        }
        return getPassword() != null ? getPassword().equals(userOrm.getPassword()) : userOrm.getPassword() == null;

    }

    @Override public int hashCode()
    {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("UserOrm{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
