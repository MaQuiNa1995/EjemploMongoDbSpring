package es.cic.cmunoz.backend.repository;

import es.cic.cmunoz.backend.dominio.MusicGroup;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicGroupRepository extends CrudRepository<MusicGroup, Long> {
    
}