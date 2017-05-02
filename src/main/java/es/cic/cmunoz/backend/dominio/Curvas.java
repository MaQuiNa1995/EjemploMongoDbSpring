package es.cic.cmunoz.backend.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "JuanchoCurvas")
public class Curvas {

    @Id
    private String id;
    @Field("Id Curva")
    private int idCurva;
    @Field("Cups")
    private String cups;
    @Field("Magnitud")
    private int magnitud;
    @Field("Fecha")
    private String fecha;
    @Field("Valores")
    private String valores;
    @Field("Flag")
    private String flag;

    public Curvas() {
        
    }

    public Curvas(String id, int idCurva, String cups, int magnitud, String fecha, String valores, String flag) {
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

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Curvas{" + "id=" + id + ", idCurva=" + idCurva + ", cups=" + cups + ", magnitud=" + magnitud + ", fecha=" + fecha + ", valores=" + valores + ", flag=" + flag + '}';
    }


}
