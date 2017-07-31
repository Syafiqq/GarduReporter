package app.freelancer.syafiqq.gardureporter.model.dao;

/*
 * This <GarduReporter> created by : 
 * Name         : syafiq
 * Date / Time  : 30 July 2017, 8:57 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

import android.content.Context;
import android.support.annotation.NonNull;
import app.freelancer.syafiqq.gardureporter.R;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduIndukOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduOrm;
import app.freelancer.syafiqq.gardureporter.model.orm.GarduPenyulangOrm;
import app.freelancer.syafiqq.gardureporter.model.service.GarduService;
import app.freelancer.syafiqq.gardureporter.model.util.Setting;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class GarduDao
{
    public static void findAllInduk(final Context context, final GarduResponseListener<List<GarduIndukOrm>> listener)
    {
        Timber.d("findAllInduk");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.getOurInstance().getNetworking().getDomain())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Setting.Networking.getReservedClient(context, true))
                .build();
        @NotNull final GarduService services = retrofit.create(GarduService.class);
        services.findGarduInduk().enqueue(new Callback<ResponseBody>()
        {
            @Override public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                String message = null;
                int success = 0;
                List<GarduIndukOrm> garduInduk = new ArrayList<>();
                @Nullable ResponseBody body = response.body();
                if(body != null)
                {
                    try
                    {
                        JSONObject res = new JSONObject(body.string());
                        JSONObject data = res.optJSONObject("data");
                        if(data != null)
                        {
                            success = data.optInt("status");
                            JSONArray messages = data.optJSONArray("message");
                            if(messages != null)
                            {
                                message = messages.optString(0);
                            }
                            JSONArray garduObj = data.optJSONArray("gardu_induk");
                            if(garduObj != null)
                            {
                                Gson gson = new Gson();
                                for(int i = -1, is = garduObj.length(); ++i < is; )
                                {
                                    garduInduk.add(gson.fromJson(garduObj.optString(i), GarduIndukOrm.class));
                                }
                            }
                        }
                    }
                    catch(IOException | JSONException e)
                    {
                        Timber.e(e);
                    }
                }
                if((success > 0))
                {
                    listener.onResponseSuccessful(garduInduk, RequestState.SUCCESS, message);
                }
                else
                {
                    listener.onResponseFailed(RequestState.FAILED, message);
                }
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable)
            {
                Timber.e(throwable);
                listener.onResponseFailed(RequestState.FAILED, context.getResources().getString(R.string.global_toast_error_retrieve_from_server));
            }
        });
    }

    public static void findAllPenyulang(final Context context, final GarduResponseListener<List<GarduPenyulangOrm>> listener)
    {
        Timber.d("findAllPenyulang");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.getOurInstance().getNetworking().getDomain())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Setting.Networking.getReservedClient(context, true))
                .build();
        @NotNull final GarduService services = retrofit.create(GarduService.class);
        services.findGarduPenyulang().enqueue(new Callback<ResponseBody>()
        {
            @Override public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                String message = null;
                int success = 0;
                List<GarduPenyulangOrm> garduPenyulang = new ArrayList<>();
                @Nullable ResponseBody body = response.body();
                if(body != null)
                {
                    try
                    {
                        JSONObject res = new JSONObject(body.string());
                        JSONObject data = res.optJSONObject("data");
                        if(data != null)
                        {
                            success = data.optInt("status");
                            JSONArray messages = data.optJSONArray("message");
                            if(messages != null)
                            {
                                message = messages.optString(0);
                            }
                            JSONArray garduObj = data.optJSONArray("gardu_penyulang");
                            if(garduObj != null)
                            {
                                Gson gson = new Gson();
                                for(int i = -1, is = garduObj.length(); ++i < is; )
                                {
                                    garduPenyulang.add(gson.fromJson(garduObj.optString(i), GarduPenyulangOrm.class));
                                }
                            }
                        }
                    }
                    catch(IOException | JSONException e)
                    {
                        Timber.e(e);
                    }
                }
                if((success > 0))
                {
                    listener.onResponseSuccessful(garduPenyulang, RequestState.SUCCESS, message);
                }
                else
                {
                    listener.onResponseFailed(RequestState.FAILED, message);
                }
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable)
            {
                Timber.e(throwable);
                listener.onResponseFailed(RequestState.FAILED, context.getResources().getString(R.string.global_toast_error_retrieve_from_server));
            }
        });
    }

    public static void sendGardu(final Context context, GarduOrm report, final GarduDao.GarduRequestListener listener)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Setting.getOurInstance().getNetworking().getDomain())
                .addConverterFactory(GsonConverterFactory.create())
                .client(Setting.Networking.getReservedClient(context, true))
                .build();
        @NotNull final GarduService services = retrofit.create(GarduService.class);
        services.sendGardu(report).enqueue(new Callback<ResponseBody>()
        {
            @Override public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response)
            {
                String message = null;
                int success = 0;
                @Nullable ResponseBody body = response.body();
                if(body != null)
                {
                    try
                    {
                        JSONObject res = new JSONObject(body.string());
                        JSONObject data = res.optJSONObject("data");
                        if(data != null)
                        {
                            success = data.optInt("status");
                            JSONArray messages = data.optJSONArray("message");
                            if(messages != null)
                            {
                                message = messages.optString(0);
                            }
                        }
                    }
                    catch(IOException | JSONException e)
                    {
                        Timber.e(e);
                    }
                }
                if((success > 0))
                {
                    listener.onRequestSuccessful(RequestState.SUCCESS, message);
                }
                else
                {
                    listener.onRequestFailed(RequestState.FAILED, message);
                }
            }

            @Override public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable)
            {
                Timber.e(throwable);
                listener.onRequestFailed(RequestState.FAILED, context.getResources().getString(R.string.global_toast_error_retrieve_from_server));
            }
        });
    }


    public interface GarduResponseListener<T>
    {
        void onResponseFailed(int status, String message);

        void onResponseSuccessful(T response, int status, String message);
    }

    public interface GarduRequestListener
    {
        void onRequestFailed(int status, String message);

        void onRequestSuccessful(int status, String message);
    }

    public static class RequestState
    {
        public static final int SUCCESS = 0x1;
        public static final int FAILED = 0x0;
    }
}
