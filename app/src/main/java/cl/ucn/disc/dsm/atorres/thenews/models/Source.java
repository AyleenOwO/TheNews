package cl.ucn.disc.dsm.atorres.thenews.models;

import androidx.room.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "source_table")
public class Source {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;
}
