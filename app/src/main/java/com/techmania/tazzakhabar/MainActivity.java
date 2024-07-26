package com.techmania.tazzakhabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recyclerView ;
    List<Article> articleList = new ArrayList<>();
    NewsRecycleAdapter adapter;
    LinearProgressIndicator progressIndicator;
    SearchView searchView ;

    Button btn1 ,btn2,btn3,btn4,btn5,btn6,btn7;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.news_recycler_view);
        progressIndicator =findViewById(R.id.progress_bar);
        searchView =findViewById(R.id.search_view);
        btn1 =findViewById(R.id.btn1);
        btn2 =findViewById(R.id.btn2);
        btn3 =findViewById(R.id.btn3);
        btn4 =findViewById(R.id.btn4);
        btn5 =findViewById(R.id.btn5);
        btn6 =findViewById(R.id.btn6);
        btn7 =findViewById(R.id.btn7);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("GENERAL",query);
                return  true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        setUpRecycle();
        getNews("GENERAL",null);


    }

    void changeInprogressbar(boolean show){
        if(show){
            progressIndicator.setVisibility(View.VISIBLE);
        }else{
            progressIndicator.setVisibility(View.INVISIBLE);
        }
    }

    void setUpRecycle(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsRecycleAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }
  void getNews(String category,String query) {
        changeInprogressbar(true);
      NewsApiClient newsApiClient = new NewsApiClient("5468ec13005943b382d1a0ace3b83189");
      newsApiClient.getTopHeadlines(
              new TopHeadlinesRequest.Builder()
                      .language("en")
                      .category(category)
                      .q(query)
                      .build(),
              new NewsApiClient.ArticlesResponseCallback() {
                  @Override
                  public void onSuccess(ArticleResponse response) {
                      runOnUiThread(()->{
                          changeInprogressbar(false);
                          articleList = response.getArticles();
                          adapter.updateData(articleList);
                          adapter.notifyDataSetChanged();
                      });

                  }

                  @Override
                  public void onFailure(Throwable throwable) {
                      Log.i("Got Failure",throwable.getMessage());
                  }
              }


      );

    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String category = btn.getText().toString();
        getNews(category,null);
    }
}