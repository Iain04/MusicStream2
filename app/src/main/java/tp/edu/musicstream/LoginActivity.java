package tp.edu.musicstream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    public static ArrayList<Login> loginList = new ArrayList<Login>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.btnLogin);

        // arrays
        ArrayList<String> usernameArray = new ArrayList<String>();
        ArrayList<String> passwordArray = new ArrayList<String>();

        // Database
        String url = "https://musicapp-e6c4.restdb.io/rest/login?apikey=fc5b26c3bd61d149b00d51ddd7f220aa79011";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TypeToken<ArrayList<Login>> token = new TypeToken<ArrayList<Login>>(){};
                loginList = gson.fromJson(response, token.getType());
                Log.d("temasek", String.valueOf(loginList));
                for (int i = 0; i < loginList.size(); i++) {
                    Login info = (Login) loginList.get(i);
                    String listUsername = info.getUsername();
                    String listPassword = info.getPassword();
                    Log.d("temasek", listUsername);
                    Log.d("temasek", listPassword);
                    usernameArray.add(listUsername);
                    passwordArray.add(listPassword);
                }
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

    private void compareInfo(String usernameList, String passwordList) {
        if (usernameList.equals(username) && passwordList.equals(password)) {
            Intent intent = new Intent(LoginActivity.this, PlaylistActivity.class);
            startActivity(intent);
        }
    }
}