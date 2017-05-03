package es.cic.cmunoz.backend.dominio;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "GrupoMusica")
public class GrupoMusica {
    
    @Id
    private String id;
    @NotNull
    @Size(min = 4, max = 4, message = "Titulo tiene que tener 4 caracteres")
    @Field("Titulo")
    private String titulo;
    @NotNull
    @Size(min = 11, max = 11, message = "Tipo tiene que tener  11 caracteres")
    @Field("Tipo")
    private String tipo;
    @Field("Integrantes")
    private List<String> integrantes;

    public GrupoMusica(String titulo, String tipo, List<String> integrantes) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.integrantes = integrantes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public String toString() {
        return "GrupoMusica{" + "id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", integrantes=" + integrantes + '}';
    }
    
    
}
