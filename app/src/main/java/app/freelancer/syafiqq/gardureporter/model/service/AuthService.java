package app.freelancer.syafiqq.gardureporter.model.service;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 29 July 2017, 7:11 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import app.freelancer.syafiqq.gardureporter.model.orm.TokenOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.UserOrm;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthService
{
    @POST("/api/mobile/auth/login/{group}?lang=en")
    Call<ResponseBody> login(@Path("group") String group, @Body UserOrm user);

    @POST("/api/mobile/auth/check?lang=en")
    Call<ResponseBody> tokenCheck(@Body TokenOrm token);
}
