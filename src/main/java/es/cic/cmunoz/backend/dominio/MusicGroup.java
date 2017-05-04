package es.cic.cmunoz.backend.dominio;

import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "MusicGroup")
public class MusicGroup {

    @Id
    private String id;
    @Field("Title")
    private String title;
    @Field("Type")
    private String type;
    @Field("Members")
    private String[] members;

    public MusicGroup() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Size(min = 7, max = 7, message = "title need to have only 4 characters")
    public String getTitle() {
        return title;
    }

    public void setTitle(String titulo) {
        this.title = titulo;
    }

    @Size(min = 11, max = 11, message = "title need to have only 11 characters")
    public String getType() {
        return type;
    }

    public void setType(String tipo) {
        this.type = tipo;
    }

    public String[] getIntegrantes() {
        return members;
    }

    public void setIntegrantes(String[] integrantes) {
        this.members = integrantes;
    }

    @Override
    public String toString() {
        return "GrupoMusica{" + "id=" + id + ", titulo=" + title + ", tipo=" + type + ", integrantes=" + members + '}';
    }

}
