package com.example.difm.mynewsfeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by difm on 03/01/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {


    private ArrayList<Article> mArticles;


    public class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView imageView;

        public CardHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.CardImageView);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }

        public void bindArticle(Article article){
            textView.setText(article.title);
            //Picasso.with(imageView.getContext()).setLoggingEnabled(true);
            //Picasso.with(imageView.getContext()).load(article.getUrl()).into(imageView);
        }
    }
    public CardAdapter(ArrayList<Article> articles) {
        mArticles = articles;
    }

    @Override
    public CardAdapter.CardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_item, parent, false);
        return new CardHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(CardAdapter.CardHolder holder, int position) {
        Article article = mArticles.get(position);
        holder.bindArticle(article);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }
}
