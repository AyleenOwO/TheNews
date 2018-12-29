/*
 * Copyright (c) 2018. Ayleen Torres Castillo  <ayleen.torres@alumnos.ucn.cl>
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0 International License.
 * http://creativecommons.org/licenses/by-nc/4.0/
 */

package cl.ucn.disc.dsm.atorres.thenews.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cl.ucn.disc.dsm.atorres.thenews.ArticleViewModel;
import cl.ucn.disc.dsm.atorres.thenews.NewsController;
import cl.ucn.disc.dsm.atorres.thenews.R;
import cl.ucn.disc.dsm.atorres.thenews.adapters.ArticleAdapter;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;
import lombok.extern.slf4j.Slf4j;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainActivity extends AppCompatActivity {

    private ArticleAdapter articleAdapter;

    private ArticleViewModel articleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        {
            final RecyclerView recyclerView = findViewById(R.id.am_rv_articles);
            final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);

            articleAdapter = new ArticleAdapter(this);
            recyclerView.setAdapter(articleAdapter);

        }

        // ViewModel
        {
            articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
            articleViewModel.getAllArticles().observe(this, new Observer<List<Article>>() {
                @Override
                public void onChanged(List<Article> articles) {
                    if(articles != null) {
                        articleAdapter.addArticles(articles);
                    }
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadArticles();
    }

    private void loadArticles() {
        AsyncTask.execute(() -> {

            try {

                final List<Article> articles = NewsController.getArticles();
                log.debug("Size: {}", articles.size());

                for (Article article : articles) {
                    articleViewModel.insert(article);
                }

                articleAdapter.addArticles(articles);

                runOnUiThread(articleAdapter::notifyDataSetChanged);

            } catch (IOException e) {
                log.error("Error", e);
            }

        });
    }

}
