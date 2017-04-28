/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.cmunoz.backend.repository;

import es.cic.cmunoz.backend.dominio.Curvas;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvasRepository extends CrudRepository<Curvas, Long> {

    Curvas findById(String id);

    Curvas findByIdCurva(String id);

    Curvas findByCups(String cups);

    Curvas findByMagnitud(String magnitud);

    Curvas findByFecha(String fecha);

    Curvas findByValores(String valor);
    
    @Query(value="{'cups':  ?0  }")
    Curvas encontrarCups(String cups);
    
    @Query(value="{'fecha': ?0  }")
    List<Curvas> encontrarFechas(String fecha);
    
    @Query(value="{'Id Curva': ?0 }")
    Curvas encontrarIdCurva(long idCurva);
    

}
