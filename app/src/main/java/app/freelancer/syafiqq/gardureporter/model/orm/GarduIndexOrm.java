package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 31 July 2017, 7:35 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GarduIndexOrm
{
    @Nullable @SerializedName("induk_id")
    private Integer garduInduk;
    @Nullable @SerializedName("penyulang_id")
    private Integer garduPenyulang;
    @Nullable @SerializedName("jenis")
    private String jenis;
    @Nullable @SerializedName("no")
    private String no;
    @Nullable @SerializedName("lokasi")
    private String alamat;
    @Nullable @SerializedName("merk")
    private String merk;
    @Nullable @SerializedName("serial")
    private String serial;
    @Nullable @SerializedName("daya")
    private Integer daya;
    @Nullable @SerializedName("fasa")
    private String fasa;
    @Nullable @SerializedName("tap")
    private Integer tap;
    @Nullable @SerializedName("jurusan")
    private Integer jurusan;
    @Nullable @SerializedName("lat")
    private Double latitude;
    @Nullable @SerializedName("long")
    private Double longitude;

    public GarduIndexOrm()
    {
    }

    public GarduIndexOrm(@NotNull GarduIndukOrm garduInduk, @NotNull GarduPenyulangOrm garduPenyulang, @NotNull JenisGarduOrm jenis, @Nullable String no, @Nullable String alamat, @Nullable String merk, @Nullable String serial, @Nullable Integer daya, @Nullable String fasa, @Nullable Integer tap, @Nullable Integer jurusan, @NotNull LocationOrm location)
    {
        this.garduInduk = garduInduk.getId();
        this.garduPenyulang = garduPenyulang.getId();
        this.jenis = jenis.getCode();
        this.no = no;
        this.alamat = alamat;
        this.merk = merk;
        this.serial = serial;
        this.daya = daya;
        this.fasa = fasa;
        this.tap = tap;
        this.jurusan = jurusan;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public Integer getGarduInduk()
    {
        return this.garduInduk;
    }

    public void setGarduInduk(@NotNull GarduIndukOrm garduInduk)
    {
        this.garduInduk = garduInduk.getId();
    }

    public void setGarduInduk(@NotNull Integer garduInduk)
    {
        this.garduInduk = garduInduk;
    }

    public Integer getGarduPenyulang()
    {
        return this.garduPenyulang;
    }

    public void setGarduPenyulang(@NotNull Integer garduPenyulang)
    {
        this.garduPenyulang = garduPenyulang;
    }

    public void setGarduPenyulang(@NotNull GarduPenyulangOrm garduPenyulang)
    {
        this.garduPenyulang = garduPenyulang.getId();
    }

    public String getJenis()
    {
        return this.jenis;
    }

    public void setJenis(@NotNull String jenis)
    {
        this.jenis = jenis;
    }

    public void setJenis(@NotNull JenisGarduOrm jenis)
    {
        this.jenis = jenis.getCode();
    }

    public String getNo()
    {
        return this.no;
    }

    public void setNo(String no)
    {
        this.no = no;
    }

    public String getAlamat()
    {
        return this.alamat;
    }

    public void setAlamat(String alamat)
    {
        this.alamat = alamat;
    }

    public String getMerk()
    {
        return this.merk;
    }

    public void setMerk(String merk)
    {
        this.merk = merk;
    }

    public String getSerial()
    {
        return this.serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public Integer getDaya()
    {
        return this.daya;
    }

    public void setDaya(Integer daya)
    {
        this.daya = daya;
    }

    public String getFasa()
    {
        return this.fasa;
    }

    public void setFasa(String fasa)
    {
        this.fasa = fasa;
    }

    public Integer getTap()
    {
        return this.tap;
    }

    public void setTap(Integer tap)
    {
        this.tap = tap;
    }

    public Integer getJurusan()
    {
        return this.jurusan;
    }

    public void setJurusan(Integer jurusan)
    {
        this.jurusan = jurusan;
    }

    public void setLocation(@NotNull LocationOrm location)
    {
        this.setLatitude(location.getLatitude());
        this.setLongitude(location.getLongitude());
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
        if(!(o instanceof GarduIndexOrm))
        {
            return false;
        }

        GarduIndexOrm garduIndexOrm = (GarduIndexOrm) o;

        if(getGarduInduk() != null ? !getGarduInduk().equals(garduIndexOrm.getGarduInduk()) : garduIndexOrm.getGarduInduk() != null)
        {
            return false;
        }
        if(getGarduPenyulang() != null ? !getGarduPenyulang().equals(garduIndexOrm.getGarduPenyulang()) : garduIndexOrm.getGarduPenyulang() != null)
        {
            return false;
        }
        if(getJenis() != null ? !getJenis().equals(garduIndexOrm.getJenis()) : garduIndexOrm.getJenis() != null)
        {
            return false;
        }
        if(getNo() != null ? !getNo().equals(garduIndexOrm.getNo()) : garduIndexOrm.getNo() != null)
        {
            return false;
        }
        if(getAlamat() != null ? !getAlamat().equals(garduIndexOrm.getAlamat()) : garduIndexOrm.getAlamat() != null)
        {
            return false;
        }
        if(getMerk() != null ? !getMerk().equals(garduIndexOrm.getMerk()) : garduIndexOrm.getMerk() != null)
        {
            return false;
        }
        if(getSerial() != null ? !getSerial().equals(garduIndexOrm.getSerial()) : garduIndexOrm.getSerial() != null)
        {
            return false;
        }
        if(getDaya() != null ? !getDaya().equals(garduIndexOrm.getDaya()) : garduIndexOrm.getDaya() != null)
        {
            return false;
        }
        if(getFasa() != null ? !getFasa().equals(garduIndexOrm.getFasa()) : garduIndexOrm.getFasa() != null)
        {
            return false;
        }
        if(getTap() != null ? !getTap().equals(garduIndexOrm.getTap()) : garduIndexOrm.getTap() != null)
        {
            return false;
        }
        if(getJurusan() != null ? !getJurusan().equals(garduIndexOrm.getJurusan()) : garduIndexOrm.getJurusan() != null)
        {
            return false;
        }
        if(getLatitude() != null ? !getLatitude().equals(garduIndexOrm.getLatitude()) : garduIndexOrm.getLatitude() != null)
        {
            return false;
        }
        return getLongitude() != null ? getLongitude().equals(garduIndexOrm.getLongitude()) : garduIndexOrm.getLongitude() == null;

    }

    @Override public int hashCode()
    {
        int result = getGarduInduk() != null ? getGarduInduk().hashCode() : 0;
        result = 31 * result + (getGarduPenyulang() != null ? getGarduPenyulang().hashCode() : 0);
        result = 31 * result + (getJenis() != null ? getJenis().hashCode() : 0);
        result = 31 * result + (getNo() != null ? getNo().hashCode() : 0);
        result = 31 * result + (getAlamat() != null ? getAlamat().hashCode() : 0);
        result = 31 * result + (getMerk() != null ? getMerk().hashCode() : 0);
        result = 31 * result + (getSerial() != null ? getSerial().hashCode() : 0);
        result = 31 * result + (getDaya() != null ? getDaya().hashCode() : 0);
        result = 31 * result + (getFasa() != null ? getFasa().hashCode() : 0);
        result = 31 * result + (getTap() != null ? getTap().hashCode() : 0);
        result = 31 * result + (getJurusan() != null ? getJurusan().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("GarduIndexOrm{");
        sb.append("garduInduk=").append(garduInduk);
        sb.append(", garduPenyulang=").append(garduPenyulang);
        sb.append(", jenis='").append(jenis).append('\'');
        sb.append(", no='").append(no).append('\'');
        sb.append(", alamat='").append(alamat).append('\'');
        sb.append(", merk='").append(merk).append('\'');
        sb.append(", serial='").append(serial).append('\'');
        sb.append(", daya=").append(daya);
        sb.append(", fasa='").append(fasa).append('\'');
        sb.append(", tap=").append(tap);
        sb.append(", jurusan=").append(jurusan);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
