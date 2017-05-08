package es.cic.cmunoz.backend.dominio;

import java.util.Arrays;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//@Pattern(regexp=".+@.+\\.[a-z]+")

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
    private int[] valores;
    @Field("Flag")
    private int[] flag;

    public Curvas() {

    }

    public Curvas(int idCurva, String cups,
            int magnitud, String fecha, int[] valores, int[] flag) {

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

    @Min(value=1, message="El id de la curva tiene que ser mayor de 1")
//    @Max(value=, message="El  tiene que ser menor de ")
    public int getIdCurva() {
        return idCurva;
    }

    public void setIdCurva(int idCurva) {
        this.idCurva = idCurva;
    }

    @Size(min = 25, max = 25, message = "Cups no valido tiene que tener 25 caracteres")
    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    @Min(value=7, message="El id de la curva tiene que ser mayor de 1")
    public int getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(int magnitud) {
        this.magnitud = magnitud;
    }

    @Size(min = 8, max = 8, message = "Fecha no valida tiene que tener 8 caracteres")
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Size(min = 25, max = 25, message = "Arreglo de valores no valido, tiene que tener 25 elementos")
    public int[] getValores() {
        return valores;
    }

    public void setValores(int[] valores) {
        this.valores = valores;
    }

    @Size(min = 25, max = 25, message = "Arreglo de flags no valido, tiene que tener 25 elementos")
    public int[] getFlag() {
        return flag;
    }

    public void setFlag(int[] flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Curvas{" + "id=" + id + ", idCurva=" + idCurva + ", cups=" + cups + ", magnitud=" + magnitud + ", fecha=" + fecha + ", valores=" + Arrays.toString(valores) + ", flag=" + Arrays.toString(flag) + '}';
    }



}
