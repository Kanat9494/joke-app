package com.android.kanatkg.jokeapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.kanatkg.jokeapp.R;
import com.android.kanatkg.jokeapp.data.JokeAdapter;
import com.android.kanatkg.jokeapp.model.Joke;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    
    private RecyclerView rv_main;
    private List<Joke> jokes;
    private static String JSON_URL = "https://official-joke-api.appspot.com/random_ten";
    JokeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv_main = findViewById(R.id.rv);
        jokes = new ArrayList<>();
        getJokes();


        rv_main.hasFixedSize();
    }

    private void getJokes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null,
        new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++) {
                    try {
                        JSONObject jokeObject = response.getJSONObject(i);
                        Joke joke = new Joke();
                        joke.setType(jokeObject.getString("type").toString());
                        joke.setSetup(jokeObject.getString("setup").toString());
                        joke.setPunchline(jokeObject.getString("punchline").toString());
                        jokes.add(joke);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                rv_main.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new JokeAdapter(getApplicationContext(), jokes);
                rv_main.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);
    }


    /**private void getJokes() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(this, Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            /**@Override
            public void onResponse(JSONArray response) {
            for(int i=0; i<response.length(); i++) {
            try {
            JSONObject jokeObject = response.getJSONObject(i);

            Joke joke = new Joke();
            joke.setType(jokeObject.getString("type").toString());
            joke.setSetup(jokeObject.getString("setup").toString());
            joke.setPunchline(jokeObject.getString("punchline").toString());

            jokes.add(joke);
            } catch (JSONException e) {
            e.printStackTrace();
            }
            }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Log.d("error", "onErrorResponse: " + error.getMessage());
            }
            });**/
}