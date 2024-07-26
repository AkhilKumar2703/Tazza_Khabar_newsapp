package com.techmania.tazzakhabar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.NewsViewHolder>{

    List<Article> articleList;
    NewsRecycleAdapter(List<Article> articleList){
        this.articleList= articleList;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
       Article arcticle = articleList.get(position);
       holder.titletextview.setText(arcticle.getTitle());
       holder.sourceTextview.setText(arcticle.getSource().getName());
       Picasso.get().load(arcticle.getUrlToImage())
               .error(R.drawable.errortk)
               .placeholder(R.drawable.errortk)
               .into(holder.imageView);

       holder.itemView.setOnClickListener((v ->{
           Intent intent =new Intent(v.getContext(), FullNewsActivity.class);
           intent.putExtra("url",arcticle.getUrl());
           v.getContext().startActivity(intent);


       }));

    }

    void updateData(List<Article>data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {

        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView titletextview , sourceTextview;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titletextview = itemView.findViewById(R.id.article_title);
            sourceTextview = itemView.findViewById(R.id.article_source);
            imageView = itemView.findViewById(R.id.article_image_view);
        }
    }
}
