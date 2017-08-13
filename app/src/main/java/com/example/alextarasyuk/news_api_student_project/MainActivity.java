package com.example.alextarasyuk.news_api_student_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.example.alextarasyuk.news_api_student_project.adapter.HomeNewsAdapter;
import com.example.alextarasyuk.news_api_student_project.model.GetArticlesResponse;
import com.example.alextarasyuk.news_api_student_project.model.NewsStore;
import com.example.alextarasyuk.news_api_student_project.networking.NewsAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by AlexTarasyuk
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView newsRecyclerView;
    private HomeNewsAdapter homeNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        newsRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


//getting data from API and setting adapter
        Call<GetArticlesResponse> call = NewsAPI.getApi().getArticles("reuters", "top");
        call.enqueue(new Callback<GetArticlesResponse>() {
            @Override
            public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
                GetArticlesResponse getArticlesResponse = response.body();
                NewsStore.setArticles(getArticlesResponse.getArticles());
                homeNewsAdapter = new HomeNewsAdapter(getArticlesResponse.getArticles());
                newsRecyclerView.setAdapter(homeNewsAdapter);
            }

            @Override
            public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
