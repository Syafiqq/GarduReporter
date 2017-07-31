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

public class GarduOrm
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

    public GarduOrm()
    {
    }

    public GarduOrm(@NotNull GarduIndukOrm garduInduk, @NotNull GarduPenyulangOrm garduPenyulang, @NotNull JenisGarduOrm jenis, @Nullable String no, @Nullable String alamat, @Nullable String merk, @Nullable String serial, @Nullable Integer daya, @Nullable String fasa, @Nullable Integer tap, @Nullable Integer jurusan, @NotNull LocationOrm location)
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


}
