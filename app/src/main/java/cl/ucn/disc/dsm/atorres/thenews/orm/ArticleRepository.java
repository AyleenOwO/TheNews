package cl.ucn.disc.dsm.atorres.thenews.orm;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;

public class ArticleRepository {


    private ArticleDAO articleDao;
    private LiveData<List<Article>> allArticles;

    public ArticleRepository(Application application) {
        ArticleDataBase database = ArticleDataBase.getInstance(application);
        articleDao = database.articleDAO();
        allArticles = articleDao.getAllArticles();

    }

    public void insert(Article article) {
        new InsertArticleAsyncTask(articleDao).execute(article);
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }

    private static class InsertArticleAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDAO articleDao;

        private InsertArticleAsyncTask(ArticleDAO articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            articleDao.insert(articles[0]);
            return null;
        }
    }
}
