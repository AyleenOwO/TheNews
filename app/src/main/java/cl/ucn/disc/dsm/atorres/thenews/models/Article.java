package cl.ucn.disc.dsm.atorres.thenews.models;

import java.sql.Date;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "article_table")
public class Article {
    @PrimaryKey(autoGenerate = true)
    @Getter
    @Setter
    private int idArt;

    @Getter
    @Setter
    @Embedded
    private Source source;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String urlToImage;

    @Getter
    @Setter
    private String publishedAt;

    @Getter
    @Setter
    private String content;
}
