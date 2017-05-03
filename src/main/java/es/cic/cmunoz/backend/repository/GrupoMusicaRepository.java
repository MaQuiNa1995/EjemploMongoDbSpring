package es.cic.cmunoz.backend.repository;

import es.cic.cmunoz.backend.dominio.GrupoMusica;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoMusicaRepository extends CrudRepository<GrupoMusica, Long> {

    GrupoMusica findById(String id);

    GrupoMusica findByTitulo(String titulo);

    GrupoMusica findByTipo(String tipo);
    
    @Query("{'Valores': {  $regex: '^?0'} ,'Flag': { $regex: '?1$'}}")
    List<GrupoMusica> encontrarGrupoMusicaPorPattern(String valorInicio,String flagFinal);
    
}