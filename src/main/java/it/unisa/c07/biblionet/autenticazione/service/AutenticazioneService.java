package it.unisa.c07.biblionet.autenticazione.service;


import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;

import java.util.List;

/**
 * @author Ciro Maiorino , Giulio Triggiani
 * Interfaccia per i metodi del sottosistema Autenticazione.
 */
public interface AutenticazioneService {
     /**
      * Firma del metodo che implementa la funzione di login.
      * @param email dell'utente da loggare.
      * @param password dell'utente da loggare.
      * @return dell'utente da loggato.
      */
     UtenteRegistrato login(String email, String password);

     /**
      * Firma del metodo che implementa l'identificazione di un lettore.
      * @param utente registrato che si trova in sessione.
      * @return true se l'utente è un lettore altrimenti false.
      */
     boolean isLettore(UtenteRegistrato utente);

     /**
      * Firma del metodo che implementa l'identificazione di un esperto.
      * @param utente registrato che si trova in sessione.
      * @return true se l'utente è un esperto altrimenti false.
      */
     boolean isEsperto(UtenteRegistrato utente);

     /**
      * Firma del metodo che implementa l'identificazione di una biblioteca.
      * @param utente registrato che si trova in sessione.
      * @return true se l'utente è una biblioteca altrimenti false.
      */
     boolean isBiblioteca(UtenteRegistrato utente);

     /**
      * Implementa la funzionalità di salvataggio delle modifiche
      * all'account biblioteca.
      * @param utente La biblioteca da aggiornare
      * @return la biblioteca aggiornata
      */
     UtenteRegistrato aggiornaUtente(UtenteRegistrato utente);



     /**
      * Implementa la funzionalità di trovare un esperto.
      * @param email La mail dell esperto
      * @return L'esperto se c'è, altrimenti null
      */
     UtenteRegistrato findUtenteByEmail(String email);



     /**
      * Implementa la funzionalità di prendere una lista di club
      * del libro a cui un lettore partecipa.
      * @param lettore il lettore preso in esame
      * @return la lista dei club del libro a cui partecipa
      */
     //List<ClubDelLibro> findAllByLettori(Lettore lettore);

     /**
      * Implementa la funzionalità di prendere una lista di club
      * del libro di cui un esperto è proprietario.
      * @param esperto l' esperto preso in esame
      * @return la lista dei club del libro a cui partecipa
      */
     //List<ClubDelLibro> findAllByEsperto(Esperto esperto);
}
