/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    //: { $regex: /^firstname/}
    // @Query("{'Valores': { $regex: ?0$, $regex: ^?0} ,'Flag': { $regex: ^?1, $regex: ?1$}}")
    // ,fields="{ 'firstname' : 1, 'lastname' : 1}"

package es.cic.cmunoz.backend.repository;

import es.cic.cmunoz.backend.dominio.Curvas;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvasRepository extends CrudRepository<Curvas, Long>,CurvasRepositoryCustom {

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
    
//    @Query("{'Valores': { $regex: '?0$'},'Valores': {$regex: '^?1'} ,'Flag': { $regex: '^?2'} ,'Flag': {$regex: '?3$'}}")
//    @Query("{'Valores': {  $regex: '?1$',$regex: '^?0'} ,'Flag': { $regex: '?3$',$regex: '^?2'}}")
    @Query("{'Valores': {  $regex: '^?0'} ,'Flag': { $regex: '?1$'}}")
    List<Curvas> encontrarCurvasPorPattern(String valorInicio,String flagFinal);
    
    /*@Query(value="{'Cups': { $regex: '^?0'} }")
    List<Curvas> empiecenPorES(String pais);
    */
}




//    @Query("{'Valores': { $regex:  '^?0'}"
//         + ",'Valores': { $regex: '?1$'}"
//         + ",'Flag': { $regex:    '^?2'}"
//         + ",'Flag': { $regex:     '?3$'}}")