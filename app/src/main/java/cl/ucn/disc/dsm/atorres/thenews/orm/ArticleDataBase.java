package cl.ucn.disc.dsm.atorres.thenews.orm;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import cl.ucn.disc.dsm.atorres.thenews.NewsController;
import cl.ucn.disc.dsm.atorres.thenews.models.Article;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Database(entities = {Article.class}, version = 1)
public abstract class ArticleDataBase extends RoomDatabase {

    private static volatile ArticleDataBase instance;

    public abstract ArticleDAO articleDAO();

    public static synchronized ArticleDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ArticleDataBase.class, "article_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ArticleDAO articleDao;

        private PopulateDbAsyncTask(ArticleDataBase db) {

            this.articleDao = db.articleDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

    }
}