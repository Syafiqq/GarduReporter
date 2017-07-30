package app.freelancer.syafiqq.gardureporter.model.service;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 30 July 2017, 8:51 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GarduService
{
    @GET("/api/mobile/gardu/induk/find?code=C41AF")
    Call<ResponseBody> findGarduInduk();

    @GET("/api/mobile/gardu/penyulang/find?code=B28FE")
    Call<ResponseBody> findGarduPenyulang();
}
