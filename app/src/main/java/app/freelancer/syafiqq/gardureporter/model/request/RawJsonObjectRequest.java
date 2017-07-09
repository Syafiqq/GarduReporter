package app.freelancer.syafiqq.gardureporter.model.request;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * This <GarduReporter> created by :
 * Name         : syafiq
 * Date / Time  : 07 July 2017, 8:15 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */

public class RawJsonObjectRequest extends JsonRequest<JSONObject>
{

    /**
     * Creates a new request.
     *
     * @param method        the HTTP method to use
     * @param url           URL to fetch the JSON from
     * @param jsonRequest   A {@link String} to post with the request. Null is allowed and
     *                      indicates no parameters will be posted along with request.
     * @param listener      Listener to receive the JSON response
     * @param errorListener Error listener, or null to ignore errors.
     */
    public RawJsonObjectRequest(int method, String url, String jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener)
    {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response)
    {
        try
        {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        }
        catch(UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
        catch(JSONException je)
        {
            return Response.error(new ParseError(je));
        }
    }
}
