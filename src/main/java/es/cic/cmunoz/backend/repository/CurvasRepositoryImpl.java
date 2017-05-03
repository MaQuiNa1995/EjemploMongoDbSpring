package es.cic.cmunoz.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Interfaz que uso para la adición de métodos propios que no tiene
 * CrudRepository por defecto
 */
public class CurvasRepositoryImpl implements CurvasRepositoryCustom {

    /**
     * Es un objeto que tiene métodos por defecto para el tratamiento de la base
     * de datos
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Método usado para hacer la actualización (Update) de las curvas que
     * tengan un Id de curva de 99.999 o menos en los que cambia la fecha por
     * una String definida por codigo
     */
    @Override
    public void updateCurvas() {

        Query consulta = new Query(Criteria.where("Id Curva").lt(100000));

        Update actualizado = new Update();
        actualizado.set("Fecha", "Cambio Fecha Menores De 100000");

        mongoTemplate.updateMulti(consulta, actualizado, "JuanchoCurvas");

    }

    /**
     * Método usado para hacer la actualización (Update) de la curva que tenga
     * id 1 en la que le cambia el código del pais del Cups a Gran Bretaña
     */
    @Override
    public void updateUnaCurva() {
        Query consulta = new Query(Criteria.where("Id Curva").is(1));

        Update actualizado = new Update();
        actualizado.set("Cups", "GB0027700000000000000010F");

        mongoTemplate.updateFirst(consulta, actualizado, "JuanchoCurvas");
    }

}
