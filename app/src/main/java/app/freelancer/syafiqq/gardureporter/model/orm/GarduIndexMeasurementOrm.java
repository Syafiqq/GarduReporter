package app.freelancer.syafiqq.gardureporter.model.orm;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 04 August 2017, 7:51 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import app.freelancer.syafiqq.gardureporter.model.util.Resetable;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

public class GarduIndexMeasurementOrm implements Resetable
{
    @Nullable @SerializedName("no_gardu")
    private String noGardu;
    @Nullable @SerializedName("petugas1")
    private String petugas1;
    @Nullable @SerializedName("petugas2")
    private String petugas2;
    @Nullable @SerializedName("no_kontrak")
    private String noKontrak;

    @Nullable @SerializedName("ir")
    private String arusRUtama;
    @Nullable @SerializedName("is")
    private String arusSUtama;
    @Nullable @SerializedName("it")
    private String arusTUtama;
    @Nullable @SerializedName("in")
    private String arusNUtama;
    @Nullable @SerializedName("vrn")
    private String teganganRNUtama;
    @Nullable @SerializedName("vsn")
    private String teganganSNUtama;
    @Nullable @SerializedName("vtn")
    private String teganganTNUtama;
    @Nullable @SerializedName("vrs")
    private String teganganRSUtama;
    @Nullable @SerializedName("vrt")
    private String teganganRTUtama;
    @Nullable @SerializedName("vst")
    private String teganganSTUtama;

    @Nullable @SerializedName("j_u1")
    private String idJurusanUmum1;
    @Nullable @SerializedName("ir_u1")
    private String arusRJurusanUmum1;
    @Nullable @SerializedName("is_u1")
    private String arusSJurusanUmum1;
    @Nullable @SerializedName("it_u1")
    private String arusTJurusanUmum1;
    @Nullable @SerializedName("in_u1")
    private String arusNJurusanUmum1;
    @Nullable @SerializedName("vrn_u1")
    private String teganganRNJurusanUmum1;
    @Nullable @SerializedName("vsn_u1")
    private String teganganSNJurusanUmum1;
    @Nullable @SerializedName("vtn_u1")
    private String teganganTNJurusanUmum1;
    @Nullable @SerializedName("vrs_u1")
    private String teganganRSJurusanUmum1;
    @Nullable @SerializedName("vrt_u1")
    private String teganganRTJurusanUmum1;
    @Nullable @SerializedName("vst_u1")
    private String teganganSTJurusanUmum1;

    @Nullable @SerializedName("j_u2")
    private String idJurusanUmum2;
    @Nullable @SerializedName("ir_u2")
    private String arusRJurusanUmum2;
    @Nullable @SerializedName("is_u2")
    private String arusSJurusanUmum2;
    @Nullable @SerializedName("it_u2")
    private String arusTJurusanUmum2;
    @Nullable @SerializedName("in_u2")
    private String arusNJurusanUmum2;
    @Nullable @SerializedName("vrn_u2")
    private String teganganRNJurusanUmum2;
    @Nullable @SerializedName("vsn_u2")
    private String teganganSNJurusanUmum2;
    @Nullable @SerializedName("vtn_u2")
    private String teganganTNJurusanUmum2;
    @Nullable @SerializedName("vrs_u2")
    private String teganganRSJurusanUmum2;
    @Nullable @SerializedName("vrt_u2")
    private String teganganRTJurusanUmum2;
    @Nullable @SerializedName("vst_u2")
    private String teganganSTJurusanUmum2;

    @Nullable @SerializedName("j_u3")
    private String idJurusanUmum3;
    @Nullable @SerializedName("ir_u3")
    private String arusRJurusanUmum3;
    @Nullable @SerializedName("is_u3")
    private String arusSJurusanUmum3;
    @Nullable @SerializedName("it_u3")
    private String arusTJurusanUmum3;
    @Nullable @SerializedName("in_u3")
    private String arusNJurusanUmum3;
    @Nullable @SerializedName("vrn_u3")
    private String teganganRNJurusanUmum3;
    @Nullable @SerializedName("vsn_u3")
    private String teganganSNJurusanUmum3;
    @Nullable @SerializedName("vtn_u3")
    private String teganganTNJurusanUmum3;
    @Nullable @SerializedName("vrs_u3")
    private String teganganRSJurusanUmum3;
    @Nullable @SerializedName("vrt_u3")
    private String teganganRTJurusanUmum3;
    @Nullable @SerializedName("vst_u3")
    private String teganganSTJurusanUmum3;

    @Nullable @SerializedName("j_u4")
    private String idJurusanUmum4;
    @Nullable @SerializedName("ir_u4")
    private String arusRJurusanUmum4;
    @Nullable @SerializedName("is_u4")
    private String arusSJurusanUmum4;
    @Nullable @SerializedName("it_u4")
    private String arusTJurusanUmum4;
    @Nullable @SerializedName("in_u4")
    private String arusNJurusanUmum4;
    @Nullable @SerializedName("vrn_u4")
    private String teganganRNJurusanUmum4;
    @Nullable @SerializedName("vsn_u4")
    private String teganganSNJurusanUmum4;
    @Nullable @SerializedName("vtn_u4")
    private String teganganTNJurusanUmum4;
    @Nullable @SerializedName("vrs_u4")
    private String teganganRSJurusanUmum4;
    @Nullable @SerializedName("vrt_u4")
    private String teganganRTJurusanUmum4;
    @Nullable @SerializedName("vst_u4")
    private String teganganSTJurusanUmum4;

    @Nullable @SerializedName("j_k1")
    private String idJurusanKhusus1;
    @Nullable @SerializedName("ir_k1")
    private String arusRJurusanKhusus1;
    @Nullable @SerializedName("is_k1")
    private String arusSJurusanKhusus1;
    @Nullable @SerializedName("it_k1")
    private String arusTJurusanKhusus1;
    @Nullable @SerializedName("in_k1")
    private String arusNJurusanKhusus1;
    @Nullable @SerializedName("vrn_k1")
    private String teganganRNJurusanKhusus1;
    @Nullable @SerializedName("vsn_k1")
    private String teganganSNJurusanKhusus1;
    @Nullable @SerializedName("vtn_k1")
    private String teganganTNJurusanKhusus1;
    @Nullable @SerializedName("vrs_k1")
    private String teganganRSJurusanKhusus1;
    @Nullable @SerializedName("vrt_k1")
    private String teganganRTJurusanKhusus1;
    @Nullable @SerializedName("vst_k1")
    private String teganganSTJurusanKhusus1;

    @Nullable @SerializedName("j_k2")
    private String idJurusanKhusus2;
    @Nullable @SerializedName("ir_k2")
    private String arusRJurusanKhusus2;
    @Nullable @SerializedName("is_k2")
    private String arusSJurusanKhusus2;
    @Nullable @SerializedName("it_k2")
    private String arusTJurusanKhusus2;
    @Nullable @SerializedName("in_k2")
    private String arusNJurusanKhusus2;
    @Nullable @SerializedName("vrn_k2")
    private String teganganRNJurusanKhusus2;
    @Nullable @SerializedName("vsn_k2")
    private String teganganSNJurusanKhusus2;
    @Nullable @SerializedName("vtn_k2")
    private String teganganTNJurusanKhusus2;
    @Nullable @SerializedName("vrs_k2")
    private String teganganRSJurusanKhusus2;
    @Nullable @SerializedName("vrt_k2")
    private String teganganRTJurusanKhusus2;
    @Nullable @SerializedName("vst_k2")
    private String teganganSTJurusanKhusus2;

    @Nullable @SerializedName("lat")
    private Double latitude;
    @Nullable @SerializedName("lng")
    private Double longitude;

    public GarduIndexMeasurementOrm()
    {
    }

    public GarduIndexMeasurementOrm(@Nullable String noGardu, @Nullable String petugas1, @Nullable String petugas2, @Nullable String noKontrak, @Nullable String arusRUtama, @Nullable String arusSUtama, @Nullable String arusTUtama, @Nullable String arusNUtama, @Nullable String teganganRNUtama, @Nullable String teganganSNUtama, @Nullable String teganganTNUtama, @Nullable String teganganRSUtama, @Nullable String teganganRTUtama, @Nullable String teganganSTUtama, @Nullable String idJurusanUmum1, @Nullable String arusRJurusanUmum1, @Nullable String arusSJurusanUmum1, @Nullable String arusTJurusanUmum1, @Nullable String arusNJurusanUmum1, @Nullable String teganganRNJurusanUmum1, @Nullable String teganganSNJurusanUmum1, @Nullable String teganganTNJurusanUmum1, @Nullable String teganganRSJurusanUmum1, @Nullable String teganganRTJurusanUmum1, @Nullable String teganganSTJurusanUmum1, @Nullable String idJurusanUmum2, @Nullable String arusRJurusanUmum2, @Nullable String arusSJurusanUmum2, @Nullable String arusTJurusanUmum2, @Nullable String arusNJurusanUmum2, @Nullable String teganganRNJurusanUmum2, @Nullable String teganganSNJurusanUmum2, @Nullable String teganganTNJurusanUmum2, @Nullable String teganganRSJurusanUmum2, @Nullable String teganganRTJurusanUmum2, @Nullable String teganganSTJurusanUmum2, @Nullable String idJurusanUmum3, @Nullable String arusRJurusanUmum3, @Nullable String arusSJurusanUmum3, @Nullable String arusTJurusanUmum3, @Nullable String arusNJurusanUmum3, @Nullable String teganganRNJurusanUmum3, @Nullable String teganganSNJurusanUmum3, @Nullable String teganganTNJurusanUmum3, @Nullable String teganganRSJurusanUmum3, @Nullable String teganganRTJurusanUmum3, @Nullable String teganganSTJurusanUmum3, @Nullable String idJurusanUmum4, @Nullable String arusRJurusanUmum4, @Nullable String arusSJurusanUmum4, @Nullable String arusTJurusanUmum4, @Nullable String arusNJurusanUmum4, @Nullable String teganganRNJurusanUmum4, @Nullable String teganganSNJurusanUmum4, @Nullable String teganganTNJurusanUmum4, @Nullable String teganganRSJurusanUmum4, @Nullable String teganganRTJurusanUmum4, @Nullable String teganganSTJurusanUmum4, @Nullable String idJurusanKhusus1, @Nullable String arusRJurusanKhusus1, @Nullable String arusSJurusanKhusus1, @Nullable String arusTJurusanKhusus1, @Nullable String arusNJurusanKhusus1, @Nullable String teganganRNJurusanKhusus1, @Nullable String teganganSNJurusanKhusus1, @Nullable String teganganTNJurusanKhusus1, @Nullable String teganganRSJurusanKhusus1, @Nullable String teganganRTJurusanKhusus1, @Nullable String teganganSTJurusanKhusus1, @Nullable String idJurusanKhusus2, @Nullable String arusRJurusanKhusus2, @Nullable String arusSJurusanKhusus2, @Nullable String arusTJurusanKhusus2, @Nullable String arusNJurusanKhusus2, @Nullable String teganganRNJurusanKhusus2, @Nullable String teganganSNJurusanKhusus2, @Nullable String teganganTNJurusanKhusus2, @Nullable String teganganRSJurusanKhusus2, @Nullable String teganganRTJurusanKhusus2, @Nullable String teganganSTJurusanKhusus2, @Nullable Double latitude, @Nullable Double longitude)
    {
        this.noGardu = noGardu;
        this.petugas1 = petugas1;
        this.petugas2 = petugas2;
        this.noKontrak = noKontrak;
        this.arusRUtama = arusRUtama;
        this.arusSUtama = arusSUtama;
        this.arusTUtama = arusTUtama;
        this.arusNUtama = arusNUtama;
        this.teganganRNUtama = teganganRNUtama;
        this.teganganSNUtama = teganganSNUtama;
        this.teganganTNUtama = teganganTNUtama;
        this.teganganRSUtama = teganganRSUtama;
        this.teganganRTUtama = teganganRTUtama;
        this.teganganSTUtama = teganganSTUtama;
        this.idJurusanUmum1 = idJurusanUmum1;
        this.arusRJurusanUmum1 = arusRJurusanUmum1;
        this.arusSJurusanUmum1 = arusSJurusanUmum1;
        this.arusTJurusanUmum1 = arusTJurusanUmum1;
        this.arusNJurusanUmum1 = arusNJurusanUmum1;
        this.teganganRNJurusanUmum1 = teganganRNJurusanUmum1;
        this.teganganSNJurusanUmum1 = teganganSNJurusanUmum1;
        this.teganganTNJurusanUmum1 = teganganTNJurusanUmum1;
        this.teganganRSJurusanUmum1 = teganganRSJurusanUmum1;
        this.teganganRTJurusanUmum1 = teganganRTJurusanUmum1;
        this.teganganSTJurusanUmum1 = teganganSTJurusanUmum1;
        this.idJurusanUmum2 = idJurusanUmum2;
        this.arusRJurusanUmum2 = arusRJurusanUmum2;
        this.arusSJurusanUmum2 = arusSJurusanUmum2;
        this.arusTJurusanUmum2 = arusTJurusanUmum2;
        this.arusNJurusanUmum2 = arusNJurusanUmum2;
        this.teganganRNJurusanUmum2 = teganganRNJurusanUmum2;
        this.teganganSNJurusanUmum2 = teganganSNJurusanUmum2;
        this.teganganTNJurusanUmum2 = teganganTNJurusanUmum2;
        this.teganganRSJurusanUmum2 = teganganRSJurusanUmum2;
        this.teganganRTJurusanUmum2 = teganganRTJurusanUmum2;
        this.teganganSTJurusanUmum2 = teganganSTJurusanUmum2;
        this.idJurusanUmum3 = idJurusanUmum3;
        this.arusRJurusanUmum3 = arusRJurusanUmum3;
        this.arusSJurusanUmum3 = arusSJurusanUmum3;
        this.arusTJurusanUmum3 = arusTJurusanUmum3;
        this.arusNJurusanUmum3 = arusNJurusanUmum3;
        this.teganganRNJurusanUmum3 = teganganRNJurusanUmum3;
        this.teganganSNJurusanUmum3 = teganganSNJurusanUmum3;
        this.teganganTNJurusanUmum3 = teganganTNJurusanUmum3;
        this.teganganRSJurusanUmum3 = teganganRSJurusanUmum3;
        this.teganganRTJurusanUmum3 = teganganRTJurusanUmum3;
        this.teganganSTJurusanUmum3 = teganganSTJurusanUmum3;
        this.idJurusanUmum4 = idJurusanUmum4;
        this.arusRJurusanUmum4 = arusRJurusanUmum4;
        this.arusSJurusanUmum4 = arusSJurusanUmum4;
        this.arusTJurusanUmum4 = arusTJurusanUmum4;
        this.arusNJurusanUmum4 = arusNJurusanUmum4;
        this.teganganRNJurusanUmum4 = teganganRNJurusanUmum4;
        this.teganganSNJurusanUmum4 = teganganSNJurusanUmum4;
        this.teganganTNJurusanUmum4 = teganganTNJurusanUmum4;
        this.teganganRSJurusanUmum4 = teganganRSJurusanUmum4;
        this.teganganRTJurusanUmum4 = teganganRTJurusanUmum4;
        this.teganganSTJurusanUmum4 = teganganSTJurusanUmum4;
        this.idJurusanKhusus1 = idJurusanKhusus1;
        this.arusRJurusanKhusus1 = arusRJurusanKhusus1;
        this.arusSJurusanKhusus1 = arusSJurusanKhusus1;
        this.arusTJurusanKhusus1 = arusTJurusanKhusus1;
        this.arusNJurusanKhusus1 = arusNJurusanKhusus1;
        this.teganganRNJurusanKhusus1 = teganganRNJurusanKhusus1;
        this.teganganSNJurusanKhusus1 = teganganSNJurusanKhusus1;
        this.teganganTNJurusanKhusus1 = teganganTNJurusanKhusus1;
        this.teganganRSJurusanKhusus1 = teganganRSJurusanKhusus1;
        this.teganganRTJurusanKhusus1 = teganganRTJurusanKhusus1;
        this.teganganSTJurusanKhusus1 = teganganSTJurusanKhusus1;
        this.idJurusanKhusus2 = idJurusanKhusus2;
        this.arusRJurusanKhusus2 = arusRJurusanKhusus2;
        this.arusSJurusanKhusus2 = arusSJurusanKhusus2;
        this.arusTJurusanKhusus2 = arusTJurusanKhusus2;
        this.arusNJurusanKhusus2 = arusNJurusanKhusus2;
        this.teganganRNJurusanKhusus2 = teganganRNJurusanKhusus2;
        this.teganganSNJurusanKhusus2 = teganganSNJurusanKhusus2;
        this.teganganTNJurusanKhusus2 = teganganTNJurusanKhusus2;
        this.teganganRSJurusanKhusus2 = teganganRSJurusanKhusus2;
        this.teganganRTJurusanKhusus2 = teganganRTJurusanKhusus2;
        this.teganganSTJurusanKhusus2 = teganganSTJurusanKhusus2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override public void reset()
    {
        Timber.d("reset");

        this.noGardu = null;
        this.petugas1 = null;
        this.petugas2 = null;
        this.noKontrak = null;
        this.arusRUtama = null;
        this.arusSUtama = null;
        this.arusTUtama = null;
        this.arusNUtama = null;
        this.teganganRNUtama = null;
        this.teganganSNUtama = null;
        this.teganganTNUtama = null;
        this.teganganRSUtama = null;
        this.teganganRTUtama = null;
        this.teganganSTUtama = null;
        this.idJurusanUmum1 = null;
        this.arusRJurusanUmum1 = null;
        this.arusSJurusanUmum1 = null;
        this.arusTJurusanUmum1 = null;
        this.arusNJurusanUmum1 = null;
        this.teganganRNJurusanUmum1 = null;
        this.teganganSNJurusanUmum1 = null;
        this.teganganTNJurusanUmum1 = null;
        this.teganganRSJurusanUmum1 = null;
        this.teganganRTJurusanUmum1 = null;
        this.teganganSTJurusanUmum1 = null;
        this.idJurusanUmum2 = null;
        this.arusRJurusanUmum2 = null;
        this.arusSJurusanUmum2 = null;
        this.arusTJurusanUmum2 = null;
        this.arusNJurusanUmum2 = null;
        this.teganganRNJurusanUmum2 = null;
        this.teganganSNJurusanUmum2 = null;
        this.teganganTNJurusanUmum2 = null;
        this.teganganRSJurusanUmum2 = null;
        this.teganganRTJurusanUmum2 = null;
        this.teganganSTJurusanUmum2 = null;
        this.idJurusanUmum3 = null;
        this.arusRJurusanUmum3 = null;
        this.arusSJurusanUmum3 = null;
        this.arusTJurusanUmum3 = null;
        this.arusNJurusanUmum3 = null;
        this.teganganRNJurusanUmum3 = null;
        this.teganganSNJurusanUmum3 = null;
        this.teganganTNJurusanUmum3 = null;
        this.teganganRSJurusanUmum3 = null;
        this.teganganRTJurusanUmum3 = null;
        this.teganganSTJurusanUmum3 = null;
        this.idJurusanUmum4 = null;
        this.arusRJurusanUmum4 = null;
        this.arusSJurusanUmum4 = null;
        this.arusTJurusanUmum4 = null;
        this.arusNJurusanUmum4 = null;
        this.teganganRNJurusanUmum4 = null;
        this.teganganSNJurusanUmum4 = null;
        this.teganganTNJurusanUmum4 = null;
        this.teganganRSJurusanUmum4 = null;
        this.teganganRTJurusanUmum4 = null;
        this.teganganSTJurusanUmum4 = null;
        this.idJurusanKhusus1 = null;
        this.arusRJurusanKhusus1 = null;
        this.arusSJurusanKhusus1 = null;
        this.arusTJurusanKhusus1 = null;
        this.arusNJurusanKhusus1 = null;
        this.teganganRNJurusanKhusus1 = null;
        this.teganganSNJurusanKhusus1 = null;
        this.teganganTNJurusanKhusus1 = null;
        this.teganganRSJurusanKhusus1 = null;
        this.teganganRTJurusanKhusus1 = null;
        this.teganganSTJurusanKhusus1 = null;
        this.idJurusanKhusus2 = null;
        this.arusRJurusanKhusus2 = null;
        this.arusSJurusanKhusus2 = null;
        this.arusTJurusanKhusus2 = null;
        this.arusNJurusanKhusus2 = null;
        this.teganganRNJurusanKhusus2 = null;
        this.teganganSNJurusanKhusus2 = null;
        this.teganganTNJurusanKhusus2 = null;
        this.teganganRSJurusanKhusus2 = null;
        this.teganganRTJurusanKhusus2 = null;
        this.teganganSTJurusanKhusus2 = null;
        this.latitude = null;
        this.longitude = null;
    }

    public String getNoGardu()
    {
        return this.noGardu;
    }

    public void setNoGardu(String noGardu)
    {
        this.noGardu = noGardu;
    }

    public String getPetugas1()
    {
        return this.petugas1;
    }

    public void setPetugas1(String petugas1)
    {
        this.petugas1 = petugas1;
    }

    public String getPetugas2()
    {
        return this.petugas2;
    }

    public void setPetugas2(String petugas2)
    {
        this.petugas2 = petugas2;
    }

    public String getNoKontrak()
    {
        return this.noKontrak;
    }

    public void setNoKontrak(String noKontrak)
    {
        this.noKontrak = noKontrak;
    }

    public String getArusRUtama()
    {
        return this.arusRUtama;
    }

    public void setArusRUtama(String arusRUtama)
    {
        this.arusRUtama = arusRUtama;
    }

    public String getArusSUtama()
    {
        return this.arusSUtama;
    }

    public void setArusSUtama(String arusSUtama)
    {
        this.arusSUtama = arusSUtama;
    }

    public String getArusTUtama()
    {
        return this.arusTUtama;
    }

    public void setArusTUtama(String arusTUtama)
    {
        this.arusTUtama = arusTUtama;
    }

    public String getArusNUtama()
    {
        return this.arusNUtama;
    }

    public void setArusNUtama(String arusNUtama)
    {
        this.arusNUtama = arusNUtama;
    }

    public String getTeganganRNUtama()
    {
        return this.teganganRNUtama;
    }

    public void setTeganganRNUtama(String teganganRNUtama)
    {
        this.teganganRNUtama = teganganRNUtama;
    }

    public String getTeganganSNUtama()
    {
        return this.teganganSNUtama;
    }

    public void setTeganganSNUtama(String teganganSNUtama)
    {
        this.teganganSNUtama = teganganSNUtama;
    }

    public String getTeganganTNUtama()
    {
        return this.teganganTNUtama;
    }

    public void setTeganganTNUtama(String teganganTNUtama)
    {
        this.teganganTNUtama = teganganTNUtama;
    }

    public String getTeganganRSUtama()
    {
        return this.teganganRSUtama;
    }

    public void setTeganganRSUtama(String teganganRSUtama)
    {
        this.teganganRSUtama = teganganRSUtama;
    }

    public String getTeganganRTUtama()
    {
        return this.teganganRTUtama;
    }

    public void setTeganganRTUtama(String teganganRTUtama)
    {
        this.teganganRTUtama = teganganRTUtama;
    }

    public String getTeganganSTUtama()
    {
        return this.teganganSTUtama;
    }

    public void setTeganganSTUtama(String teganganSTUtama)
    {
        this.teganganSTUtama = teganganSTUtama;
    }

    public String getIdJurusanUmum1()
    {
        return this.idJurusanUmum1;
    }

    public void setIdJurusanUmum1(String idJurusanUmum1)
    {
        this.idJurusanUmum1 = idJurusanUmum1;
    }

    public String getArusRJurusanUmum1()
    {
        return this.arusRJurusanUmum1;
    }

    public void setArusRJurusanUmum1(String arusRJurusanUmum1)
    {
        this.arusRJurusanUmum1 = arusRJurusanUmum1;
    }

    public String getArusSJurusanUmum1()
    {
        return this.arusSJurusanUmum1;
    }

    public void setArusSJurusanUmum1(String arusSJurusanUmum1)
    {
        this.arusSJurusanUmum1 = arusSJurusanUmum1;
    }

    public String getArusTJurusanUmum1()
    {
        return this.arusTJurusanUmum1;
    }

    public void setArusTJurusanUmum1(String arusTJurusanUmum1)
    {
        this.arusTJurusanUmum1 = arusTJurusanUmum1;
    }

    public String getArusNJurusanUmum1()
    {
        return this.arusNJurusanUmum1;
    }

    public void setArusNJurusanUmum1(String arusNJurusanUmum1)
    {
        this.arusNJurusanUmum1 = arusNJurusanUmum1;
    }

    public String getTeganganRNJurusanUmum1()
    {
        return this.teganganRNJurusanUmum1;
    }

    public void setTeganganRNJurusanUmum1(String teganganRNJurusanUmum1)
    {
        this.teganganRNJurusanUmum1 = teganganRNJurusanUmum1;
    }

    public String getTeganganSNJurusanUmum1()
    {
        return this.teganganSNJurusanUmum1;
    }

    public void setTeganganSNJurusanUmum1(String teganganSNJurusanUmum1)
    {
        this.teganganSNJurusanUmum1 = teganganSNJurusanUmum1;
    }

    public String getTeganganTNJurusanUmum1()
    {
        return this.teganganTNJurusanUmum1;
    }

    public void setTeganganTNJurusanUmum1(String teganganTNJurusanUmum1)
    {
        this.teganganTNJurusanUmum1 = teganganTNJurusanUmum1;
    }

    public String getTeganganRSJurusanUmum1()
    {
        return this.teganganRSJurusanUmum1;
    }

    public void setTeganganRSJurusanUmum1(String teganganRSJurusanUmum1)
    {
        this.teganganRSJurusanUmum1 = teganganRSJurusanUmum1;
    }

    public String getTeganganRTJurusanUmum1()
    {
        return this.teganganRTJurusanUmum1;
    }

    public void setTeganganRTJurusanUmum1(String teganganRTJurusanUmum1)
    {
        this.teganganRTJurusanUmum1 = teganganRTJurusanUmum1;
    }

    public String getTeganganSTJurusanUmum1()
    {
        return this.teganganSTJurusanUmum1;
    }

    public void setTeganganSTJurusanUmum1(String teganganSTJurusanUmum1)
    {
        this.teganganSTJurusanUmum1 = teganganSTJurusanUmum1;
    }

    public String getIdJurusanUmum2()
    {
        return this.idJurusanUmum2;
    }

    public void setIdJurusanUmum2(String idJurusanUmum2)
    {
        this.idJurusanUmum2 = idJurusanUmum2;
    }

    public String getArusRJurusanUmum2()
    {
        return this.arusRJurusanUmum2;
    }

    public void setArusRJurusanUmum2(String arusRJurusanUmum2)
    {
        this.arusRJurusanUmum2 = arusRJurusanUmum2;
    }

    public String getArusSJurusanUmum2()
    {
        return this.arusSJurusanUmum2;
    }

    public void setArusSJurusanUmum2(String arusSJurusanUmum2)
    {
        this.arusSJurusanUmum2 = arusSJurusanUmum2;
    }

    public String getArusTJurusanUmum2()
    {
        return this.arusTJurusanUmum2;
    }

    public void setArusTJurusanUmum2(String arusTJurusanUmum2)
    {
        this.arusTJurusanUmum2 = arusTJurusanUmum2;
    }

    public String getArusNJurusanUmum2()
    {
        return this.arusNJurusanUmum2;
    }

    public void setArusNJurusanUmum2(String arusNJurusanUmum2)
    {
        this.arusNJurusanUmum2 = arusNJurusanUmum2;
    }

    public String getTeganganRNJurusanUmum2()
    {
        return this.teganganRNJurusanUmum2;
    }

    public void setTeganganRNJurusanUmum2(String teganganRNJurusanUmum2)
    {
        this.teganganRNJurusanUmum2 = teganganRNJurusanUmum2;
    }

    public String getTeganganSNJurusanUmum2()
    {
        return this.teganganSNJurusanUmum2;
    }

    public void setTeganganSNJurusanUmum2(String teganganSNJurusanUmum2)
    {
        this.teganganSNJurusanUmum2 = teganganSNJurusanUmum2;
    }

    public String getTeganganTNJurusanUmum2()
    {
        return this.teganganTNJurusanUmum2;
    }

    public void setTeganganTNJurusanUmum2(String teganganTNJurusanUmum2)
    {
        this.teganganTNJurusanUmum2 = teganganTNJurusanUmum2;
    }

    public String getTeganganRSJurusanUmum2()
    {
        return this.teganganRSJurusanUmum2;
    }

    public void setTeganganRSJurusanUmum2(String teganganRSJurusanUmum2)
    {
        this.teganganRSJurusanUmum2 = teganganRSJurusanUmum2;
    }

    public String getTeganganRTJurusanUmum2()
    {
        return this.teganganRTJurusanUmum2;
    }

    public void setTeganganRTJurusanUmum2(String teganganRTJurusanUmum2)
    {
        this.teganganRTJurusanUmum2 = teganganRTJurusanUmum2;
    }

    public String getTeganganSTJurusanUmum2()
    {
        return this.teganganSTJurusanUmum2;
    }

    public void setTeganganSTJurusanUmum2(String teganganSTJurusanUmum2)
    {
        this.teganganSTJurusanUmum2 = teganganSTJurusanUmum2;
    }

    public String getIdJurusanUmum3()
    {
        return this.idJurusanUmum3;
    }

    public void setIdJurusanUmum3(String idJurusanUmum3)
    {
        this.idJurusanUmum3 = idJurusanUmum3;
    }

    public String getArusRJurusanUmum3()
    {
        return this.arusRJurusanUmum3;
    }

    public void setArusRJurusanUmum3(String arusRJurusanUmum3)
    {
        this.arusRJurusanUmum3 = arusRJurusanUmum3;
    }

    public String getArusSJurusanUmum3()
    {
        return this.arusSJurusanUmum3;
    }

    public void setArusSJurusanUmum3(String arusSJurusanUmum3)
    {
        this.arusSJurusanUmum3 = arusSJurusanUmum3;
    }

    public String getArusTJurusanUmum3()
    {
        return this.arusTJurusanUmum3;
    }

    public void setArusTJurusanUmum3(String arusTJurusanUmum3)
    {
        this.arusTJurusanUmum3 = arusTJurusanUmum3;
    }

    public String getArusNJurusanUmum3()
    {
        return this.arusNJurusanUmum3;
    }

    public void setArusNJurusanUmum3(String arusNJurusanUmum3)
    {
        this.arusNJurusanUmum3 = arusNJurusanUmum3;
    }

    public String getTeganganRNJurusanUmum3()
    {
        return this.teganganRNJurusanUmum3;
    }

    public void setTeganganRNJurusanUmum3(String teganganRNJurusanUmum3)
    {
        this.teganganRNJurusanUmum3 = teganganRNJurusanUmum3;
    }

    public String getTeganganSNJurusanUmum3()
    {
        return this.teganganSNJurusanUmum3;
    }

    public void setTeganganSNJurusanUmum3(String teganganSNJurusanUmum3)
    {
        this.teganganSNJurusanUmum3 = teganganSNJurusanUmum3;
    }

    public String getTeganganTNJurusanUmum3()
    {
        return this.teganganTNJurusanUmum3;
    }

    public void setTeganganTNJurusanUmum3(String teganganTNJurusanUmum3)
    {
        this.teganganTNJurusanUmum3 = teganganTNJurusanUmum3;
    }

    public String getTeganganRSJurusanUmum3()
    {
        return this.teganganRSJurusanUmum3;
    }

    public void setTeganganRSJurusanUmum3(String teganganRSJurusanUmum3)
    {
        this.teganganRSJurusanUmum3 = teganganRSJurusanUmum3;
    }

    public String getTeganganRTJurusanUmum3()
    {
        return this.teganganRTJurusanUmum3;
    }

    public void setTeganganRTJurusanUmum3(String teganganRTJurusanUmum3)
    {
        this.teganganRTJurusanUmum3 = teganganRTJurusanUmum3;
    }

    public String getTeganganSTJurusanUmum3()
    {
        return this.teganganSTJurusanUmum3;
    }

    public void setTeganganSTJurusanUmum3(String teganganSTJurusanUmum3)
    {
        this.teganganSTJurusanUmum3 = teganganSTJurusanUmum3;
    }

    public String getIdJurusanUmum4()
    {
        return this.idJurusanUmum4;
    }

    public void setIdJurusanUmum4(String idJurusanUmum4)
    {
        this.idJurusanUmum4 = idJurusanUmum4;
    }

    public String getArusRJurusanUmum4()
    {
        return this.arusRJurusanUmum4;
    }

    public void setArusRJurusanUmum4(String arusRJurusanUmum4)
    {
        this.arusRJurusanUmum4 = arusRJurusanUmum4;
    }

    public String getArusSJurusanUmum4()
    {
        return this.arusSJurusanUmum4;
    }

    public void setArusSJurusanUmum4(String arusSJurusanUmum4)
    {
        this.arusSJurusanUmum4 = arusSJurusanUmum4;
    }

    public String getArusTJurusanUmum4()
    {
        return this.arusTJurusanUmum4;
    }

    public void setArusTJurusanUmum4(String arusTJurusanUmum4)
    {
        this.arusTJurusanUmum4 = arusTJurusanUmum4;
    }

    public String getArusNJurusanUmum4()
    {
        return this.arusNJurusanUmum4;
    }

    public void setArusNJurusanUmum4(String arusNJurusanUmum4)
    {
        this.arusNJurusanUmum4 = arusNJurusanUmum4;
    }

    public String getTeganganRNJurusanUmum4()
    {
        return this.teganganRNJurusanUmum4;
    }

    public void setTeganganRNJurusanUmum4(String teganganRNJurusanUmum4)
    {
        this.teganganRNJurusanUmum4 = teganganRNJurusanUmum4;
    }

    public String getTeganganSNJurusanUmum4()
    {
        return this.teganganSNJurusanUmum4;
    }

    public void setTeganganSNJurusanUmum4(String teganganSNJurusanUmum4)
    {
        this.teganganSNJurusanUmum4 = teganganSNJurusanUmum4;
    }

    public String getTeganganTNJurusanUmum4()
    {
        return this.teganganTNJurusanUmum4;
    }

    public void setTeganganTNJurusanUmum4(String teganganTNJurusanUmum4)
    {
        this.teganganTNJurusanUmum4 = teganganTNJurusanUmum4;
    }

    public String getTeganganRSJurusanUmum4()
    {
        return this.teganganRSJurusanUmum4;
    }

    public void setTeganganRSJurusanUmum4(String teganganRSJurusanUmum4)
    {
        this.teganganRSJurusanUmum4 = teganganRSJurusanUmum4;
    }

    public String getTeganganRTJurusanUmum4()
    {
        return this.teganganRTJurusanUmum4;
    }

    public void setTeganganRTJurusanUmum4(String teganganRTJurusanUmum4)
    {
        this.teganganRTJurusanUmum4 = teganganRTJurusanUmum4;
    }

    public String getTeganganSTJurusanUmum4()
    {
        return this.teganganSTJurusanUmum4;
    }

    public void setTeganganSTJurusanUmum4(String teganganSTJurusanUmum4)
    {
        this.teganganSTJurusanUmum4 = teganganSTJurusanUmum4;
    }

    public String getIdJurusanKhusus1()
    {
        return this.idJurusanKhusus1;
    }

    public void setIdJurusanKhusus1(String idJurusanKhusus1)
    {
        this.idJurusanKhusus1 = idJurusanKhusus1;
    }

    public String getArusRJurusanKhusus1()
    {
        return this.arusRJurusanKhusus1;
    }

    public void setArusRJurusanKhusus1(String arusRJurusanKhusus1)
    {
        this.arusRJurusanKhusus1 = arusRJurusanKhusus1;
    }

    public String getArusSJurusanKhusus1()
    {
        return this.arusSJurusanKhusus1;
    }

    public void setArusSJurusanKhusus1(String arusSJurusanKhusus1)
    {
        this.arusSJurusanKhusus1 = arusSJurusanKhusus1;
    }

    public String getArusTJurusanKhusus1()
    {
        return this.arusTJurusanKhusus1;
    }

    public void setArusTJurusanKhusus1(String arusTJurusanKhusus1)
    {
        this.arusTJurusanKhusus1 = arusTJurusanKhusus1;
    }

    public String getArusNJurusanKhusus1()
    {
        return this.arusNJurusanKhusus1;
    }

    public void setArusNJurusanKhusus1(String arusNJurusanKhusus1)
    {
        this.arusNJurusanKhusus1 = arusNJurusanKhusus1;
    }

    public String getTeganganRNJurusanKhusus1()
    {
        return this.teganganRNJurusanKhusus1;
    }

    public void setTeganganRNJurusanKhusus1(String teganganRNJurusanKhusus1)
    {
        this.teganganRNJurusanKhusus1 = teganganRNJurusanKhusus1;
    }

    public String getTeganganSNJurusanKhusus1()
    {
        return this.teganganSNJurusanKhusus1;
    }

    public void setTeganganSNJurusanKhusus1(String teganganSNJurusanKhusus1)
    {
        this.teganganSNJurusanKhusus1 = teganganSNJurusanKhusus1;
    }

    public String getTeganganTNJurusanKhusus1()
    {
        return this.teganganTNJurusanKhusus1;
    }

    public void setTeganganTNJurusanKhusus1(String teganganTNJurusanKhusus1)
    {
        this.teganganTNJurusanKhusus1 = teganganTNJurusanKhusus1;
    }

    public String getTeganganRSJurusanKhusus1()
    {
        return this.teganganRSJurusanKhusus1;
    }

    public void setTeganganRSJurusanKhusus1(String teganganRSJurusanKhusus1)
    {
        this.teganganRSJurusanKhusus1 = teganganRSJurusanKhusus1;
    }

    public String getTeganganRTJurusanKhusus1()
    {
        return this.teganganRTJurusanKhusus1;
    }

    public void setTeganganRTJurusanKhusus1(String teganganRTJurusanKhusus1)
    {
        this.teganganRTJurusanKhusus1 = teganganRTJurusanKhusus1;
    }

    public String getTeganganSTJurusanKhusus1()
    {
        return this.teganganSTJurusanKhusus1;
    }

    public void setTeganganSTJurusanKhusus1(String teganganSTJurusanKhusus1)
    {
        this.teganganSTJurusanKhusus1 = teganganSTJurusanKhusus1;
    }

    public String getIdJurusanKhusus2()
    {
        return this.idJurusanKhusus2;
    }

    public void setIdJurusanKhusus2(String idJurusanKhusus2)
    {
        this.idJurusanKhusus2 = idJurusanKhusus2;
    }

    public String getArusRJurusanKhusus2()
    {
        return this.arusRJurusanKhusus2;
    }

    public void setArusRJurusanKhusus2(String arusRJurusanKhusus2)
    {
        this.arusRJurusanKhusus2 = arusRJurusanKhusus2;
    }

    public String getArusSJurusanKhusus2()
    {
        return this.arusSJurusanKhusus2;
    }

    public void setArusSJurusanKhusus2(String arusSJurusanKhusus2)
    {
        this.arusSJurusanKhusus2 = arusSJurusanKhusus2;
    }

    public String getArusTJurusanKhusus2()
    {
        return this.arusTJurusanKhusus2;
    }

    public void setArusTJurusanKhusus2(String arusTJurusanKhusus2)
    {
        this.arusTJurusanKhusus2 = arusTJurusanKhusus2;
    }

    public String getArusNJurusanKhusus2()
    {
        return this.arusNJurusanKhusus2;
    }

    public void setArusNJurusanKhusus2(String arusNJurusanKhusus2)
    {
        this.arusNJurusanKhusus2 = arusNJurusanKhusus2;
    }

    public String getTeganganRNJurusanKhusus2()
    {
        return this.teganganRNJurusanKhusus2;
    }

    public void setTeganganRNJurusanKhusus2(String teganganRNJurusanKhusus2)
    {
        this.teganganRNJurusanKhusus2 = teganganRNJurusanKhusus2;
    }

    public String getTeganganSNJurusanKhusus2()
    {
        return this.teganganSNJurusanKhusus2;
    }

    public void setTeganganSNJurusanKhusus2(String teganganSNJurusanKhusus2)
    {
        this.teganganSNJurusanKhusus2 = teganganSNJurusanKhusus2;
    }

    public String getTeganganTNJurusanKhusus2()
    {
        return this.teganganTNJurusanKhusus2;
    }

    public void setTeganganTNJurusanKhusus2(String teganganTNJurusanKhusus2)
    {
        this.teganganTNJurusanKhusus2 = teganganTNJurusanKhusus2;
    }

    public String getTeganganRSJurusanKhusus2()
    {
        return this.teganganRSJurusanKhusus2;
    }

    public void setTeganganRSJurusanKhusus2(String teganganRSJurusanKhusus2)
    {
        this.teganganRSJurusanKhusus2 = teganganRSJurusanKhusus2;
    }

    public String getTeganganRTJurusanKhusus2()
    {
        return this.teganganRTJurusanKhusus2;
    }

    public void setTeganganRTJurusanKhusus2(String teganganRTJurusanKhusus2)
    {
        this.teganganRTJurusanKhusus2 = teganganRTJurusanKhusus2;
    }

    public String getTeganganSTJurusanKhusus2()
    {
        return this.teganganSTJurusanKhusus2;
    }

    public void setTeganganSTJurusanKhusus2(String teganganSTJurusanKhusus2)
    {
        this.teganganSTJurusanKhusus2 = teganganSTJurusanKhusus2;
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
        if(!(o instanceof GarduIndexMeasurementOrm))
        {
            return false;
        }

        GarduIndexMeasurementOrm that = (GarduIndexMeasurementOrm) o;

        if(getNoGardu() != null ? !getNoGardu().equals(that.getNoGardu()) : that.getNoGardu() != null)
        {
            return false;
        }
        if(getPetugas1() != null ? !getPetugas1().equals(that.getPetugas1()) : that.getPetugas1() != null)
        {
            return false;
        }
        if(getPetugas2() != null ? !getPetugas2().equals(that.getPetugas2()) : that.getPetugas2() != null)
        {
            return false;
        }
        if(getNoKontrak() != null ? !getNoKontrak().equals(that.getNoKontrak()) : that.getNoKontrak() != null)
        {
            return false;
        }
        if(getArusRUtama() != null ? !getArusRUtama().equals(that.getArusRUtama()) : that.getArusRUtama() != null)
        {
            return false;
        }
        if(getArusSUtama() != null ? !getArusSUtama().equals(that.getArusSUtama()) : that.getArusSUtama() != null)
        {
            return false;
        }
        if(getArusTUtama() != null ? !getArusTUtama().equals(that.getArusTUtama()) : that.getArusTUtama() != null)
        {
            return false;
        }
        if(getArusNUtama() != null ? !getArusNUtama().equals(that.getArusNUtama()) : that.getArusNUtama() != null)
        {
            return false;
        }
        if(getTeganganRNUtama() != null ? !getTeganganRNUtama().equals(that.getTeganganRNUtama()) : that.getTeganganRNUtama() != null)
        {
            return false;
        }
        if(getTeganganSNUtama() != null ? !getTeganganSNUtama().equals(that.getTeganganSNUtama()) : that.getTeganganSNUtama() != null)
        {
            return false;
        }
        if(getTeganganTNUtama() != null ? !getTeganganTNUtama().equals(that.getTeganganTNUtama()) : that.getTeganganTNUtama() != null)
        {
            return false;
        }
        if(getTeganganRSUtama() != null ? !getTeganganRSUtama().equals(that.getTeganganRSUtama()) : that.getTeganganRSUtama() != null)
        {
            return false;
        }
        if(getTeganganRTUtama() != null ? !getTeganganRTUtama().equals(that.getTeganganRTUtama()) : that.getTeganganRTUtama() != null)
        {
            return false;
        }
        if(getTeganganSTUtama() != null ? !getTeganganSTUtama().equals(that.getTeganganSTUtama()) : that.getTeganganSTUtama() != null)
        {
            return false;
        }
        if(getIdJurusanUmum1() != null ? !getIdJurusanUmum1().equals(that.getIdJurusanUmum1()) : that.getIdJurusanUmum1() != null)
        {
            return false;
        }
        if(getArusRJurusanUmum1() != null ? !getArusRJurusanUmum1().equals(that.getArusRJurusanUmum1()) : that.getArusRJurusanUmum1() != null)
        {
            return false;
        }
        if(getArusSJurusanUmum1() != null ? !getArusSJurusanUmum1().equals(that.getArusSJurusanUmum1()) : that.getArusSJurusanUmum1() != null)
        {
            return false;
        }
        if(getArusTJurusanUmum1() != null ? !getArusTJurusanUmum1().equals(that.getArusTJurusanUmum1()) : that.getArusTJurusanUmum1() != null)
        {
            return false;
        }
        if(getArusNJurusanUmum1() != null ? !getArusNJurusanUmum1().equals(that.getArusNJurusanUmum1()) : that.getArusNJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanUmum1() != null ? !getTeganganRNJurusanUmum1().equals(that.getTeganganRNJurusanUmum1()) : that.getTeganganRNJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanUmum1() != null ? !getTeganganSNJurusanUmum1().equals(that.getTeganganSNJurusanUmum1()) : that.getTeganganSNJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanUmum1() != null ? !getTeganganTNJurusanUmum1().equals(that.getTeganganTNJurusanUmum1()) : that.getTeganganTNJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanUmum1() != null ? !getTeganganRSJurusanUmum1().equals(that.getTeganganRSJurusanUmum1()) : that.getTeganganRSJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanUmum1() != null ? !getTeganganRTJurusanUmum1().equals(that.getTeganganRTJurusanUmum1()) : that.getTeganganRTJurusanUmum1() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanUmum1() != null ? !getTeganganSTJurusanUmum1().equals(that.getTeganganSTJurusanUmum1()) : that.getTeganganSTJurusanUmum1() != null)
        {
            return false;
        }
        if(getIdJurusanUmum2() != null ? !getIdJurusanUmum2().equals(that.getIdJurusanUmum2()) : that.getIdJurusanUmum2() != null)
        {
            return false;
        }
        if(getArusRJurusanUmum2() != null ? !getArusRJurusanUmum2().equals(that.getArusRJurusanUmum2()) : that.getArusRJurusanUmum2() != null)
        {
            return false;
        }
        if(getArusSJurusanUmum2() != null ? !getArusSJurusanUmum2().equals(that.getArusSJurusanUmum2()) : that.getArusSJurusanUmum2() != null)
        {
            return false;
        }
        if(getArusTJurusanUmum2() != null ? !getArusTJurusanUmum2().equals(that.getArusTJurusanUmum2()) : that.getArusTJurusanUmum2() != null)
        {
            return false;
        }
        if(getArusNJurusanUmum2() != null ? !getArusNJurusanUmum2().equals(that.getArusNJurusanUmum2()) : that.getArusNJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanUmum2() != null ? !getTeganganRNJurusanUmum2().equals(that.getTeganganRNJurusanUmum2()) : that.getTeganganRNJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanUmum2() != null ? !getTeganganSNJurusanUmum2().equals(that.getTeganganSNJurusanUmum2()) : that.getTeganganSNJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanUmum2() != null ? !getTeganganTNJurusanUmum2().equals(that.getTeganganTNJurusanUmum2()) : that.getTeganganTNJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanUmum2() != null ? !getTeganganRSJurusanUmum2().equals(that.getTeganganRSJurusanUmum2()) : that.getTeganganRSJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanUmum2() != null ? !getTeganganRTJurusanUmum2().equals(that.getTeganganRTJurusanUmum2()) : that.getTeganganRTJurusanUmum2() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanUmum2() != null ? !getTeganganSTJurusanUmum2().equals(that.getTeganganSTJurusanUmum2()) : that.getTeganganSTJurusanUmum2() != null)
        {
            return false;
        }
        if(getIdJurusanUmum3() != null ? !getIdJurusanUmum3().equals(that.getIdJurusanUmum3()) : that.getIdJurusanUmum3() != null)
        {
            return false;
        }
        if(getArusRJurusanUmum3() != null ? !getArusRJurusanUmum3().equals(that.getArusRJurusanUmum3()) : that.getArusRJurusanUmum3() != null)
        {
            return false;
        }
        if(getArusSJurusanUmum3() != null ? !getArusSJurusanUmum3().equals(that.getArusSJurusanUmum3()) : that.getArusSJurusanUmum3() != null)
        {
            return false;
        }
        if(getArusTJurusanUmum3() != null ? !getArusTJurusanUmum3().equals(that.getArusTJurusanUmum3()) : that.getArusTJurusanUmum3() != null)
        {
            return false;
        }
        if(getArusNJurusanUmum3() != null ? !getArusNJurusanUmum3().equals(that.getArusNJurusanUmum3()) : that.getArusNJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanUmum3() != null ? !getTeganganRNJurusanUmum3().equals(that.getTeganganRNJurusanUmum3()) : that.getTeganganRNJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanUmum3() != null ? !getTeganganSNJurusanUmum3().equals(that.getTeganganSNJurusanUmum3()) : that.getTeganganSNJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanUmum3() != null ? !getTeganganTNJurusanUmum3().equals(that.getTeganganTNJurusanUmum3()) : that.getTeganganTNJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanUmum3() != null ? !getTeganganRSJurusanUmum3().equals(that.getTeganganRSJurusanUmum3()) : that.getTeganganRSJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanUmum3() != null ? !getTeganganRTJurusanUmum3().equals(that.getTeganganRTJurusanUmum3()) : that.getTeganganRTJurusanUmum3() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanUmum3() != null ? !getTeganganSTJurusanUmum3().equals(that.getTeganganSTJurusanUmum3()) : that.getTeganganSTJurusanUmum3() != null)
        {
            return false;
        }
        if(getIdJurusanUmum4() != null ? !getIdJurusanUmum4().equals(that.getIdJurusanUmum4()) : that.getIdJurusanUmum4() != null)
        {
            return false;
        }
        if(getArusRJurusanUmum4() != null ? !getArusRJurusanUmum4().equals(that.getArusRJurusanUmum4()) : that.getArusRJurusanUmum4() != null)
        {
            return false;
        }
        if(getArusSJurusanUmum4() != null ? !getArusSJurusanUmum4().equals(that.getArusSJurusanUmum4()) : that.getArusSJurusanUmum4() != null)
        {
            return false;
        }
        if(getArusTJurusanUmum4() != null ? !getArusTJurusanUmum4().equals(that.getArusTJurusanUmum4()) : that.getArusTJurusanUmum4() != null)
        {
            return false;
        }
        if(getArusNJurusanUmum4() != null ? !getArusNJurusanUmum4().equals(that.getArusNJurusanUmum4()) : that.getArusNJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanUmum4() != null ? !getTeganganRNJurusanUmum4().equals(that.getTeganganRNJurusanUmum4()) : that.getTeganganRNJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanUmum4() != null ? !getTeganganSNJurusanUmum4().equals(that.getTeganganSNJurusanUmum4()) : that.getTeganganSNJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanUmum4() != null ? !getTeganganTNJurusanUmum4().equals(that.getTeganganTNJurusanUmum4()) : that.getTeganganTNJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanUmum4() != null ? !getTeganganRSJurusanUmum4().equals(that.getTeganganRSJurusanUmum4()) : that.getTeganganRSJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanUmum4() != null ? !getTeganganRTJurusanUmum4().equals(that.getTeganganRTJurusanUmum4()) : that.getTeganganRTJurusanUmum4() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanUmum4() != null ? !getTeganganSTJurusanUmum4().equals(that.getTeganganSTJurusanUmum4()) : that.getTeganganSTJurusanUmum4() != null)
        {
            return false;
        }
        if(getIdJurusanKhusus1() != null ? !getIdJurusanKhusus1().equals(that.getIdJurusanKhusus1()) : that.getIdJurusanKhusus1() != null)
        {
            return false;
        }
        if(getArusRJurusanKhusus1() != null ? !getArusRJurusanKhusus1().equals(that.getArusRJurusanKhusus1()) : that.getArusRJurusanKhusus1() != null)
        {
            return false;
        }
        if(getArusSJurusanKhusus1() != null ? !getArusSJurusanKhusus1().equals(that.getArusSJurusanKhusus1()) : that.getArusSJurusanKhusus1() != null)
        {
            return false;
        }
        if(getArusTJurusanKhusus1() != null ? !getArusTJurusanKhusus1().equals(that.getArusTJurusanKhusus1()) : that.getArusTJurusanKhusus1() != null)
        {
            return false;
        }
        if(getArusNJurusanKhusus1() != null ? !getArusNJurusanKhusus1().equals(that.getArusNJurusanKhusus1()) : that.getArusNJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanKhusus1() != null ? !getTeganganRNJurusanKhusus1().equals(that.getTeganganRNJurusanKhusus1()) : that.getTeganganRNJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanKhusus1() != null ? !getTeganganSNJurusanKhusus1().equals(that.getTeganganSNJurusanKhusus1()) : that.getTeganganSNJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanKhusus1() != null ? !getTeganganTNJurusanKhusus1().equals(that.getTeganganTNJurusanKhusus1()) : that.getTeganganTNJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanKhusus1() != null ? !getTeganganRSJurusanKhusus1().equals(that.getTeganganRSJurusanKhusus1()) : that.getTeganganRSJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanKhusus1() != null ? !getTeganganRTJurusanKhusus1().equals(that.getTeganganRTJurusanKhusus1()) : that.getTeganganRTJurusanKhusus1() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanKhusus1() != null ? !getTeganganSTJurusanKhusus1().equals(that.getTeganganSTJurusanKhusus1()) : that.getTeganganSTJurusanKhusus1() != null)
        {
            return false;
        }
        if(getIdJurusanKhusus2() != null ? !getIdJurusanKhusus2().equals(that.getIdJurusanKhusus2()) : that.getIdJurusanKhusus2() != null)
        {
            return false;
        }
        if(getArusRJurusanKhusus2() != null ? !getArusRJurusanKhusus2().equals(that.getArusRJurusanKhusus2()) : that.getArusRJurusanKhusus2() != null)
        {
            return false;
        }
        if(getArusSJurusanKhusus2() != null ? !getArusSJurusanKhusus2().equals(that.getArusSJurusanKhusus2()) : that.getArusSJurusanKhusus2() != null)
        {
            return false;
        }
        if(getArusTJurusanKhusus2() != null ? !getArusTJurusanKhusus2().equals(that.getArusTJurusanKhusus2()) : that.getArusTJurusanKhusus2() != null)
        {
            return false;
        }
        if(getArusNJurusanKhusus2() != null ? !getArusNJurusanKhusus2().equals(that.getArusNJurusanKhusus2()) : that.getArusNJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganRNJurusanKhusus2() != null ? !getTeganganRNJurusanKhusus2().equals(that.getTeganganRNJurusanKhusus2()) : that.getTeganganRNJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganSNJurusanKhusus2() != null ? !getTeganganSNJurusanKhusus2().equals(that.getTeganganSNJurusanKhusus2()) : that.getTeganganSNJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganTNJurusanKhusus2() != null ? !getTeganganTNJurusanKhusus2().equals(that.getTeganganTNJurusanKhusus2()) : that.getTeganganTNJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganRSJurusanKhusus2() != null ? !getTeganganRSJurusanKhusus2().equals(that.getTeganganRSJurusanKhusus2()) : that.getTeganganRSJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganRTJurusanKhusus2() != null ? !getTeganganRTJurusanKhusus2().equals(that.getTeganganRTJurusanKhusus2()) : that.getTeganganRTJurusanKhusus2() != null)
        {
            return false;
        }
        if(getTeganganSTJurusanKhusus2() != null ? !getTeganganSTJurusanKhusus2().equals(that.getTeganganSTJurusanKhusus2()) : that.getTeganganSTJurusanKhusus2() != null)
        {
            return false;
        }
        if(getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null)
        {
            return false;
        }
        return getLongitude() != null ? getLongitude().equals(that.getLongitude()) : that.getLongitude() == null;

    }

    @Override public int hashCode()
    {
        int result = getNoGardu() != null ? getNoGardu().hashCode() : 0;
        result = 31 * result + (getPetugas1() != null ? getPetugas1().hashCode() : 0);
        result = 31 * result + (getPetugas2() != null ? getPetugas2().hashCode() : 0);
        result = 31 * result + (getNoKontrak() != null ? getNoKontrak().hashCode() : 0);
        result = 31 * result + (getArusRUtama() != null ? getArusRUtama().hashCode() : 0);
        result = 31 * result + (getArusSUtama() != null ? getArusSUtama().hashCode() : 0);
        result = 31 * result + (getArusTUtama() != null ? getArusTUtama().hashCode() : 0);
        result = 31 * result + (getArusNUtama() != null ? getArusNUtama().hashCode() : 0);
        result = 31 * result + (getTeganganRNUtama() != null ? getTeganganRNUtama().hashCode() : 0);
        result = 31 * result + (getTeganganSNUtama() != null ? getTeganganSNUtama().hashCode() : 0);
        result = 31 * result + (getTeganganTNUtama() != null ? getTeganganTNUtama().hashCode() : 0);
        result = 31 * result + (getTeganganRSUtama() != null ? getTeganganRSUtama().hashCode() : 0);
        result = 31 * result + (getTeganganRTUtama() != null ? getTeganganRTUtama().hashCode() : 0);
        result = 31 * result + (getTeganganSTUtama() != null ? getTeganganSTUtama().hashCode() : 0);
        result = 31 * result + (getIdJurusanUmum1() != null ? getIdJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getArusRJurusanUmum1() != null ? getArusRJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getArusSJurusanUmum1() != null ? getArusSJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getArusTJurusanUmum1() != null ? getArusTJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getArusNJurusanUmum1() != null ? getArusNJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanUmum1() != null ? getTeganganRNJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanUmum1() != null ? getTeganganSNJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanUmum1() != null ? getTeganganTNJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanUmum1() != null ? getTeganganRSJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanUmum1() != null ? getTeganganRTJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanUmum1() != null ? getTeganganSTJurusanUmum1().hashCode() : 0);
        result = 31 * result + (getIdJurusanUmum2() != null ? getIdJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getArusRJurusanUmum2() != null ? getArusRJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getArusSJurusanUmum2() != null ? getArusSJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getArusTJurusanUmum2() != null ? getArusTJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getArusNJurusanUmum2() != null ? getArusNJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanUmum2() != null ? getTeganganRNJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanUmum2() != null ? getTeganganSNJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanUmum2() != null ? getTeganganTNJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanUmum2() != null ? getTeganganRSJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanUmum2() != null ? getTeganganRTJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanUmum2() != null ? getTeganganSTJurusanUmum2().hashCode() : 0);
        result = 31 * result + (getIdJurusanUmum3() != null ? getIdJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getArusRJurusanUmum3() != null ? getArusRJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getArusSJurusanUmum3() != null ? getArusSJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getArusTJurusanUmum3() != null ? getArusTJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getArusNJurusanUmum3() != null ? getArusNJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanUmum3() != null ? getTeganganRNJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanUmum3() != null ? getTeganganSNJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanUmum3() != null ? getTeganganTNJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanUmum3() != null ? getTeganganRSJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanUmum3() != null ? getTeganganRTJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanUmum3() != null ? getTeganganSTJurusanUmum3().hashCode() : 0);
        result = 31 * result + (getIdJurusanUmum4() != null ? getIdJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getArusRJurusanUmum4() != null ? getArusRJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getArusSJurusanUmum4() != null ? getArusSJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getArusTJurusanUmum4() != null ? getArusTJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getArusNJurusanUmum4() != null ? getArusNJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanUmum4() != null ? getTeganganRNJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanUmum4() != null ? getTeganganSNJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanUmum4() != null ? getTeganganTNJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanUmum4() != null ? getTeganganRSJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanUmum4() != null ? getTeganganRTJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanUmum4() != null ? getTeganganSTJurusanUmum4().hashCode() : 0);
        result = 31 * result + (getIdJurusanKhusus1() != null ? getIdJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getArusRJurusanKhusus1() != null ? getArusRJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getArusSJurusanKhusus1() != null ? getArusSJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getArusTJurusanKhusus1() != null ? getArusTJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getArusNJurusanKhusus1() != null ? getArusNJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanKhusus1() != null ? getTeganganRNJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanKhusus1() != null ? getTeganganSNJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanKhusus1() != null ? getTeganganTNJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanKhusus1() != null ? getTeganganRSJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanKhusus1() != null ? getTeganganRTJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanKhusus1() != null ? getTeganganSTJurusanKhusus1().hashCode() : 0);
        result = 31 * result + (getIdJurusanKhusus2() != null ? getIdJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getArusRJurusanKhusus2() != null ? getArusRJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getArusSJurusanKhusus2() != null ? getArusSJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getArusTJurusanKhusus2() != null ? getArusTJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getArusNJurusanKhusus2() != null ? getArusNJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganRNJurusanKhusus2() != null ? getTeganganRNJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganSNJurusanKhusus2() != null ? getTeganganSNJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganTNJurusanKhusus2() != null ? getTeganganTNJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganRSJurusanKhusus2() != null ? getTeganganRSJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganRTJurusanKhusus2() != null ? getTeganganRTJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getTeganganSTJurusanKhusus2() != null ? getTeganganSTJurusanKhusus2().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        final StringBuilder sb = new StringBuilder("GarduIndexMeasurementOrm{");
        sb.append("noGardu='").append(noGardu).append('\'');
        sb.append(", petugas1='").append(petugas1).append('\'');
        sb.append(", petugas2='").append(petugas2).append('\'');
        sb.append(", noKontrak='").append(noKontrak).append('\'');
        sb.append(", arusRUtama='").append(arusRUtama).append('\'');
        sb.append(", arusSUtama='").append(arusSUtama).append('\'');
        sb.append(", arusTUtama='").append(arusTUtama).append('\'');
        sb.append(", arusNUtama='").append(arusNUtama).append('\'');
        sb.append(", teganganRNUtama='").append(teganganRNUtama).append('\'');
        sb.append(", teganganSNUtama='").append(teganganSNUtama).append('\'');
        sb.append(", teganganTNUtama='").append(teganganTNUtama).append('\'');
        sb.append(", teganganRSUtama='").append(teganganRSUtama).append('\'');
        sb.append(", teganganRTUtama='").append(teganganRTUtama).append('\'');
        sb.append(", teganganSTUtama='").append(teganganSTUtama).append('\'');
        sb.append(", idJurusanUmum1='").append(idJurusanUmum1).append('\'');
        sb.append(", arusRJurusanUmum1='").append(arusRJurusanUmum1).append('\'');
        sb.append(", arusSJurusanUmum1='").append(arusSJurusanUmum1).append('\'');
        sb.append(", arusTJurusanUmum1='").append(arusTJurusanUmum1).append('\'');
        sb.append(", arusNJurusanUmum1='").append(arusNJurusanUmum1).append('\'');
        sb.append(", teganganRNJurusanUmum1='").append(teganganRNJurusanUmum1).append('\'');
        sb.append(", teganganSNJurusanUmum1='").append(teganganSNJurusanUmum1).append('\'');
        sb.append(", teganganTNJurusanUmum1='").append(teganganTNJurusanUmum1).append('\'');
        sb.append(", teganganRSJurusanUmum1='").append(teganganRSJurusanUmum1).append('\'');
        sb.append(", teganganRTJurusanUmum1='").append(teganganRTJurusanUmum1).append('\'');
        sb.append(", teganganSTJurusanUmum1='").append(teganganSTJurusanUmum1).append('\'');
        sb.append(", idJurusanUmum2='").append(idJurusanUmum2).append('\'');
        sb.append(", arusRJurusanUmum2='").append(arusRJurusanUmum2).append('\'');
        sb.append(", arusSJurusanUmum2='").append(arusSJurusanUmum2).append('\'');
        sb.append(", arusTJurusanUmum2='").append(arusTJurusanUmum2).append('\'');
        sb.append(", arusNJurusanUmum2='").append(arusNJurusanUmum2).append('\'');
        sb.append(", teganganRNJurusanUmum2='").append(teganganRNJurusanUmum2).append('\'');
        sb.append(", teganganSNJurusanUmum2='").append(teganganSNJurusanUmum2).append('\'');
        sb.append(", teganganTNJurusanUmum2='").append(teganganTNJurusanUmum2).append('\'');
        sb.append(", teganganRSJurusanUmum2='").append(teganganRSJurusanUmum2).append('\'');
        sb.append(", teganganRTJurusanUmum2='").append(teganganRTJurusanUmum2).append('\'');
        sb.append(", teganganSTJurusanUmum2='").append(teganganSTJurusanUmum2).append('\'');
        sb.append(", idJurusanUmum3='").append(idJurusanUmum3).append('\'');
        sb.append(", arusRJurusanUmum3='").append(arusRJurusanUmum3).append('\'');
        sb.append(", arusSJurusanUmum3='").append(arusSJurusanUmum3).append('\'');
        sb.append(", arusTJurusanUmum3='").append(arusTJurusanUmum3).append('\'');
        sb.append(", arusNJurusanUmum3='").append(arusNJurusanUmum3).append('\'');
        sb.append(", teganganRNJurusanUmum3='").append(teganganRNJurusanUmum3).append('\'');
        sb.append(", teganganSNJurusanUmum3='").append(teganganSNJurusanUmum3).append('\'');
        sb.append(", teganganTNJurusanUmum3='").append(teganganTNJurusanUmum3).append('\'');
        sb.append(", teganganRSJurusanUmum3='").append(teganganRSJurusanUmum3).append('\'');
        sb.append(", teganganRTJurusanUmum3='").append(teganganRTJurusanUmum3).append('\'');
        sb.append(", teganganSTJurusanUmum3='").append(teganganSTJurusanUmum3).append('\'');
        sb.append(", idJurusanUmum4='").append(idJurusanUmum4).append('\'');
        sb.append(", arusRJurusanUmum4='").append(arusRJurusanUmum4).append('\'');
        sb.append(", arusSJurusanUmum4='").append(arusSJurusanUmum4).append('\'');
        sb.append(", arusTJurusanUmum4='").append(arusTJurusanUmum4).append('\'');
        sb.append(", arusNJurusanUmum4='").append(arusNJurusanUmum4).append('\'');
        sb.append(", teganganRNJurusanUmum4='").append(teganganRNJurusanUmum4).append('\'');
        sb.append(", teganganSNJurusanUmum4='").append(teganganSNJurusanUmum4).append('\'');
        sb.append(", teganganTNJurusanUmum4='").append(teganganTNJurusanUmum4).append('\'');
        sb.append(", teganganRSJurusanUmum4='").append(teganganRSJurusanUmum4).append('\'');
        sb.append(", teganganRTJurusanUmum4='").append(teganganRTJurusanUmum4).append('\'');
        sb.append(", teganganSTJurusanUmum4='").append(teganganSTJurusanUmum4).append('\'');
        sb.append(", idJurusanKhusus1='").append(idJurusanKhusus1).append('\'');
        sb.append(", arusRJurusanKhusus1='").append(arusRJurusanKhusus1).append('\'');
        sb.append(", arusSJurusanKhusus1='").append(arusSJurusanKhusus1).append('\'');
        sb.append(", arusTJurusanKhusus1='").append(arusTJurusanKhusus1).append('\'');
        sb.append(", arusNJurusanKhusus1='").append(arusNJurusanKhusus1).append('\'');
        sb.append(", teganganRNJurusanKhusus1='").append(teganganRNJurusanKhusus1).append('\'');
        sb.append(", teganganSNJurusanKhusus1='").append(teganganSNJurusanKhusus1).append('\'');
        sb.append(", teganganTNJurusanKhusus1='").append(teganganTNJurusanKhusus1).append('\'');
        sb.append(", teganganRSJurusanKhusus1='").append(teganganRSJurusanKhusus1).append('\'');
        sb.append(", teganganRTJurusanKhusus1='").append(teganganRTJurusanKhusus1).append('\'');
        sb.append(", teganganSTJurusanKhusus1='").append(teganganSTJurusanKhusus1).append('\'');
        sb.append(", idJurusanKhusus2='").append(idJurusanKhusus2).append('\'');
        sb.append(", arusRJurusanKhusus2='").append(arusRJurusanKhusus2).append('\'');
        sb.append(", arusSJurusanKhusus2='").append(arusSJurusanKhusus2).append('\'');
        sb.append(", arusTJurusanKhusus2='").append(arusTJurusanKhusus2).append('\'');
        sb.append(", arusNJurusanKhusus2='").append(arusNJurusanKhusus2).append('\'');
        sb.append(", teganganRNJurusanKhusus2='").append(teganganRNJurusanKhusus2).append('\'');
        sb.append(", teganganSNJurusanKhusus2='").append(teganganSNJurusanKhusus2).append('\'');
        sb.append(", teganganTNJurusanKhusus2='").append(teganganTNJurusanKhusus2).append('\'');
        sb.append(", teganganRSJurusanKhusus2='").append(teganganRSJurusanKhusus2).append('\'');
        sb.append(", teganganRTJurusanKhusus2='").append(teganganRTJurusanKhusus2).append('\'');
        sb.append(", teganganSTJurusanKhusus2='").append(teganganSTJurusanKhusus2).append('\'');
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }
}
