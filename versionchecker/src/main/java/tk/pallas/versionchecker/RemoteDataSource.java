package tk.pallas.versionchecker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import static com.android.volley.VolleyLog.TAG;

class RemoteDataSource {

    private final MutableLiveData<String> mResult;
    private final RequestQueue mRequestQueue;
    public RemoteDataSource(RequestQueue requestQueue, String url) {
        mRequestQueue = requestQueue;
        mResult = new MutableLiveData<>();
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mResult.setValue(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        mRequestQueue.add(strReq);
    }
    public LiveData<String> getData() {
        return mResult;
    }
}
