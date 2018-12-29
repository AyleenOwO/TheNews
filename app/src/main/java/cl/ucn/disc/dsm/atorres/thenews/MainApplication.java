package cl.ucn.disc.dsm.atorres.thenews;

import android.app.Application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainApplication extends Application {

    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApplication getInstance() {
        return instance;
    }
}