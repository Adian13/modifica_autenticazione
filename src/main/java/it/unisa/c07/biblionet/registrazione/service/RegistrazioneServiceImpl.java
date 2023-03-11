package it.unisa.c07.biblionet.registrazione.service;

import it.unisa.c07.biblionet.autenticazione.service.AutenticazioneService;
import it.unisa.c07.biblionet.model.dao.utente.UtenteRegistratoDAO;
import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alessio Casolaro
 * @author Antonio Della Porta.
 */
@Service
@RequiredArgsConstructor
public class RegistrazioneServiceImpl implements RegistrazioneService {

    /**
     * Si occupa di gestire le operazioni CRUD dell'Esperto.
     */
    private final UtenteRegistratoDAO utenteRegistratoDAO;

    private final AutenticazioneService autenticazioneService;

    /**
     * Implementa la funzionalità di registrazione un Esperto.
     * @param utente L'Esperto da registrare
     * @return L'utente registrato
     */
    @Override
    public final UtenteRegistrato registraUtente(final UtenteRegistrato utente) {
        return utenteRegistratoDAO.save(utente);
    }


    @Override
    public boolean isEmailRegistrata(final String email) {

       /*
        * Utilizzo il LettoreDAO, ma potrei usare qualsiasi altro DAO
        * degli utenti, poiché data la generalizzazione, la findAll
        * restituisce tutti gli utenti del sistema
        */
        for (UtenteRegistrato u: utenteRegistratoDAO.findAll()) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Implementa la funzionalità di trovare dei generi.
     * @param generi Un'array di nomi di generi da trovare
     * @return Una lista contenente i generi trovati
     */
    /*
    @Override
    public final List<Genere> findGeneriByName(final String[] generi) {
        List<Genere> toReturn = new ArrayList<>();
        for (String g: generi) {
            Genere gen = genereDAO.findByName(g);
            if (gen != null) {
                toReturn.add(gen);
            }

        }
        return toReturn;
    }*/

    /**
     * Implementa la funzionalità di trovare una biblioteca.
     * @param email La mail della biblioteca
     * @return La biblioteca se c'è, altrimenti null
     */
    /*
    @Override
    public final Biblioteca getBibliotecaByEmail(final String email) {

        return autenticazioneService.findBibliotecaByEmail(email);
    }
    */



}
