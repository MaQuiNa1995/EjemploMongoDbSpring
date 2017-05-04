package es.cic.cmunoz.backend.service;

import es.cic.cmunoz.backend.dominio.MusicGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.cmunoz.backend.repository.MusicGroupRepository;
import org.springframework.validation.annotation.Validated;

/**
 * Clase usada para el llamado a métodos del repository registrando los segundos
 */
@Service
public class MusicGroupServiceImpl implements MusicGroupService {
    
    @Autowired
    private MusicGroupRepository repository;

    public MusicGroupServiceImpl() {
        
    }
    
    @Override
    public void storeGroup(MusicGroup group){
        
        repository.save(group);
        
    }
    
}
