package es.cic.cmunoz.backend.service;

import es.cic.cmunoz.backend.dominio.GrupoMusica;
import es.cic.cmunoz.backend.repository.GrupoMusicaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase usada para el llamado a métodos del repository registrando los segundos
 */
@Service
public class GrupoMusicaServiceImpl implements GrupoMusicaService {
    
    @Autowired
    private GrupoMusicaRepository repository;

    public GrupoMusicaServiceImpl() {
        
    }
    
    @Override
    public void guardarGrupo(){
        
        List<String> listaIntegrantes = new ArrayList<>();
        listaIntegrantes.add("tipoA");
        listaIntegrantes.add("tipoB");
        listaIntegrantes.add("tipoC");
        
        GrupoMusica grupo = new GrupoMusica("Pantera", "Heavy Metal", listaIntegrantes);
        
        repository.save(grupo);
        
    }
    
}
