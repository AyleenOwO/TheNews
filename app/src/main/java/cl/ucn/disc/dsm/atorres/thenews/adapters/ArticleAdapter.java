
package cl.ucn.disc.dsm.atorres.thenews.adapters;
/*
 * Copyright (c) 2018.  Diego Urrutia Astorga <durrutia@ucn.cl>
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0 International License.
 * http://creativecommons.org/licenses/by-nc/4.0/
 *
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.atorres.thenews.R;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 */
@Slf4j
public final class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    /**
     *
     */
    private final List<Article> articles = new ArrayList<>();

    /**
     * Inflater
     */
    private LayoutInflater inflater;

    /**
     * @param context to use.
     */
    public ArticleAdapter(@NonNull final Context context) {
        this.inflater = LayoutInflater.from(context);

        Picasso.get().setIndicatorsEnabled(true);

    }

    /**
     * @param list to add.
     */
    public void addArticles(final List<Article> list) {
        this.articles.addAll(list);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    @Override
    public ViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        final View view = this.inflater.inflate(R.layout.row_article, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Article article = this.articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        //holder.image.setImageURI(article.getUrlToImage());
        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.image_not_found)
                .into(holder.image);
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    /**
     *
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView description;

        ViewHolder(View view) {
            super(view);
            this.image = view.findViewById(R.id.ra_iv_image);
            this.title = view.findViewById(R.id.ra_tv_title);
            this.description = view.findViewById(R.id.ra_tv_description);
        }

    }
}
