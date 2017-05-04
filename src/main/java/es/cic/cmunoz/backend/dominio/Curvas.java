package es.cic.cmunoz.backend.dominio;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "JuanchoCurvas")
public class Curvas {

    @Id
    private String id;
    @Size(max = 7, message = "Id Curva no puede tener mas de 7 caracteres")
    @Field("Id Curva")
    private int idCurva;
    
    @NotNull(message = "No puede ser nulo")
    @Size(min = 5, max = 10, message = "Cups tiene que tener una longitud de 26 caracteres")
    @Field("Cups")
    private String cups;
    @Field("Magnitud")
    private int magnitud;
    @Field("Fecha")
    private String fecha;
    @Field("Valores")
    private int[] valores;
    @Field("Flag")
    private int[] flag;

    public Curvas() {

    }

    public Curvas(String id, int idCurva, String cups,
            int magnitud, String fecha, int[] valores, int[] flag) {

        this.id = id;
        this.idCurva = idCurva;
        this.cups = cups;
        this.magnitud = magnitud;
        this.fecha = fecha;
        this.valores = valores;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdCurva() {
        return idCurva;
    }

    public void setIdCurva(int idCurva) {
        this.idCurva = idCurva;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public int getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(int magnitud) {
        this.magnitud = magnitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    public int[] getFlag() {
        return flag;
    }

    public void setFlag(int[] flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Curvas{" + "id=" + id
                + ", idCurva=" + idCurva
                + ", cups=" + cups
                + ", magnitud=" + magnitud
                + ", fecha=" + fecha
                + ", valores=" + valores
                + ", flag=" + flag + '}';
    }

}
