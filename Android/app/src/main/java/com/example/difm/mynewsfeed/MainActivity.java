package com.example.difm.mynewsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycle_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Queries the API and send the list of articles to the CardAdapter
        getArticles();



    }


    protected void getArticles() {
        String url = "http://10.0.2.2:3000/rss";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Article> articles = parseApiResponse(response);
                CardAdapter cardAdapter = new CardAdapter(articles);
                recyclerView.setAdapter(cardAdapter);
                System.out.println("Success");
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error");
            }
        });

        // Add the request to the queue
        Volley.newRequestQueue(this).add(jsonArrayRequest);


    }

    private ArrayList<Article> parseApiResponse(JSONArray response) {

        Gson gson = new Gson();
        ArrayList<Article> articles= new ArrayList<>();
        for(int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                String str = jsonObject.toString();
                Article art = gson.fromJson(str, Article.class);
                articles.add(art);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return articles;
    }
}
