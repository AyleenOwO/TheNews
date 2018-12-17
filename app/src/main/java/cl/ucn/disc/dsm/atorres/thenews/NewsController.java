/*
 * Copyright (c) 2018.  Diego Urrutia Astorga <durrutia@ucn.cl>
 * This work is licensed under a Creative Commons Attribution-NonCommercial 4.0 International License.
 * http://creativecommons.org/licenses/by-nc/4.0/
 *
 */

package cl.ucn.disc.dsm.atorres.thenews;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import cl.ucn.disc.dsm.atorres.thenews.models.Article;
import cl.ucn.disc.dsm.atorres.thenews.models.NewsAPI;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 *
 */
@Slf4j
public final class NewsController {

    /**
     *
     */
    public interface NewsService {

        /**
         * @return the {@link Call} of {@link NewsAPI}.
         */
        @Headers({"X-Api-Key: b7b7a9968ea341e3980ca613adb808e5"})
        @GET("top-headlines?sources=techcrunch,ars-technica,engadget,buzzfeed,wired&pageSize=100")
        Call<NewsAPI> getTopHeadlines();

    }

    /**
     * Des-Serializador GSON
     */
    private static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            //.serializeNulls()
            //.setPrettyPrinting()
            .create();

    /**
     * Interceptor
     */
    private static final HttpLoggingInterceptor interceptor;

    static {
        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
    }

    /**
     * The Client
     */
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();

    /**
     * Retrofit
     */
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    /**
     * The Service
     */
    private static final NewsService newsService = retrofit.create(NewsService.class);

    /**
     * @return the {@link List} of {@link Article}.
     */
    public static List<Article> getArticles() throws IOException {

        // Call
        final Call<NewsAPI> newsCall = newsService.getTopHeadlines();

        // News
        final NewsAPI news = newsCall.execute().body();
        //log.debug("News status: {}, totalResult: {}", news.getStatus(), news.getTotalResults());

        return news.getArticles();

    }
}
