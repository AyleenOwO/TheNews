package cl.ucn.disc.dsm.atorres.thenews;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;
import cl.ucn.disc.dsm.atorres.thenews.orm.ArticleRepository;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository repository;
    private LiveData<List<Article>> allArticles;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        repository = new ArticleRepository(application);
        allArticles = repository.getAllArticles();
    }


    public void insert(Article article) {
        repository.insert(article);
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }
}
