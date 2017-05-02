package es.cic.cmunoz.backend.repository;

/**
 * Interfaz que uso para la adición de métodos propios que no tiene
 * CrudRepository por defecto
 */
public interface CurvasRepositoryCustom {

    /**
     * Método usado para hacer la actualización (Update) de las curvas que
     * tengan un Id de curva de 99.999 o menos en los que cambia la fecha por
     * una String definida por codigo
     */
    void updateCurvas();

    /**
     * Método usado para hacer la actualización (Update) de la curva que tenga
     * id 1 en la que le cambia el código del pais del Cups a Gran Bretaña
     */
    void updateUnaCurva();
}
