
package it.unisa.c07.biblionet.registrazione.service;

import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import java.util.List;

/**
 * @author Alessio Casolaro
 * @author Antonio Della Porta.
 */
public interface RegistrazioneService {
    /**
     * Implementa la funzionalità di registrazione un Esperto.
     * @param utente L'Esperto da registrare
     * @return L'utente registrato
     */


    UtenteRegistrato registraUtente(UtenteRegistrato utente);



    /**
     * Implementa la funzionalità di controllare se una mail è
     * presente già associata ad un altro utente nel database.
     * @param email la mail da controllare
     * @return true se la mail è già associata, false altrimenti
     */
    boolean isEmailRegistrata(String email);

}
