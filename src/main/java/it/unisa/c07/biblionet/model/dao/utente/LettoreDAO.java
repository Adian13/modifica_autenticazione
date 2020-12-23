package it.unisa.c07.biblionet.model.dao.utente;

import it.unisa.c07.biblionet.model.entity.utente.Lettore;
import org.springframework.stereotype.Repository;

/**
 * Questa classe rappresenta il DAO di un Lettore.
 */
@Repository
public interface LettoreDAO extends UtenteRegistratoDAO {
    /**
     * Implementa la funzionalità di ricerca di un utente Lettore nel DB.
     * @param email dell'utente da cercare.
     * @param password dell'utente da cercare.
     * @return dell'utente trovato.
     */
    Lettore findByEmailAndPassword(String email, byte[] password);


}
