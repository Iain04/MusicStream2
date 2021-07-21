package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {


    public static ArrayList<Song> mainList = new ArrayList<Song>();
    RecyclerView mainSongView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        mainSongView = findViewById(R.id.mainSongView);

//        https://musicapp-e6c4.restdb.io/rest/playlist?apikey=a32658250bd832876eae1c9d9dc852b1a1064
        String url = "https://musicapp-e6c4.restdb.io/rest/song?apikey=fc5b26c3bd61d149b00d51ddd7f220aa79011";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                TypeToken<ArrayList<Song>> token = new TypeToken<ArrayList<Song>>(){};
                mainList = gson.fromJson(response, token.getType());
                SongMainAdapter adapter = new SongMainAdapter(mainList);
                mainSongView.setAdapter(adapter);
                mainSongView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                int i = mainList.size();
//                Log.d("temasek", String.valueOf(i));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("temasek", error.toString());
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}