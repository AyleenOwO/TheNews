package cl.ucn.disc.dsm.atorres.thenews.orm;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;

@Dao
public interface ArticleDAO {

    @Insert
    void insert(Article article);

    @Query("SELECT * FROM article_table ORDER BY publishedAt ASC")
    LiveData<List<Article>> getAllArticles();
}
