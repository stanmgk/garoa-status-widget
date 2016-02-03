package dierobot.com.garoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String url = "https://garoahc.appspot.com/status";
    Boolean open;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //JSON is here
        //Pretty cute code, by the way
        requestQueue = Volley.newRequestQueue(this);
        textView = (TextView) findViewById(R.id.textView);

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            open = response.getBoolean("open");
                            if (open)
                                textView.setText("Aberto");
                            else
                                textView.setText("Fechado");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            textView.setText("Error");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                });
        requestQueue.add(jor);

    }
}
