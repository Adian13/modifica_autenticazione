package it.unisa.c07.biblionet.model.dao.utente;

import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * Questa classe rappresenta il DAO di un Utente Registrato, usato
 * per estendere gli attori core del sistema.
 */
@Repository
public interface UtenteRegistratoDAO
        extends JpaRepository<UtenteRegistrato, String> {

    UtenteRegistrato findByEmailAndPassword(String email, byte[] password);

    /**
     * Query custom che recupera dal DB un lettore dato il
     * suo id.
     * @param email L'ID del lettore
     * @return Lettore trovato
     */
    @Query("SELECT l FROM UtenteRegistrato l WHERE l.email=?1")
    UtenteRegistrato findByID(String email);


}


