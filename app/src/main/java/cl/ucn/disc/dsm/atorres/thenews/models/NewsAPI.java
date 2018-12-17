package cl.ucn.disc.dsm.atorres.thenews.models;

import java.util.List;

import lombok.Getter;

public class NewsAPI {

    @Getter
    private String status;


    @Getter
    private int totalResults;

    @Getter
    private List<Article> articles;
}
