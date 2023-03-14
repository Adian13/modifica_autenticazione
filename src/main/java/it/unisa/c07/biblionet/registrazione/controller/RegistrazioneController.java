package it.unisa.c07.biblionet.registrazione.controller;

import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import it.unisa.c07.biblionet.registrazione.service.RegistrazioneService;
import it.unisa.c07.biblionet.utils.validazione.RegexTester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

//todo le operazioni di conferma password etc sono state spostate al front-end
/**
 * @author Alessio Casolaro
 * @author Antonio Della Porta
 */
@Controller
@SessionAttributes("loggedUser")
@RequiredArgsConstructor
@RequestMapping("/registrazione")
public final class RegistrazioneController {

    /**
     * Il service per effettuare le operazioni di persistenza.
     */
    private final RegistrazioneService registrazioneService;

    /**
     * Implementa la funzionalità di visualizzare
     * la scelta di registrazione.
     *
     * @return La pagina di visualizzazione
     */
    //@RequestMapping(value = "/", method = RequestMethod.GET) todo front-end
    //public String visualizzaRegistrazione() {return "registrazione/registrazione";}

    /**
     * Implementa la funzionalità di registrazione di
     * scegliere il tipo di utente da registrare.
     *
     * @param scelta Il tipo di utente da registrare
     * @return La view che visualizza il form di registrazione scelto.
     */
    /*
    @RequestMapping(value = "/scegli", method = RequestMethod.POST)
    public String scegliRegistrazione(final @RequestParam("scelta")
                                              String scelta) {
        return "registrazione/registrazione_" + scelta.toLowerCase();
    }
    todo frontend
    */


    /**
     * Implementa la funzionalità di registrazione di un esperto.
     *
     * @param utente l'utente da registrare
     * @return la view per effettuare il login
     */
    @RequestMapping(value = "/utente", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public boolean registrazioneUtente(final UtenteRegistrato utente) {

        System.out.println(utente.getEmail());
        if (registrazioneService.isEmailRegistrata(utente.getEmail())) {
            return false;
        }

        if(registrazioneService.registraUtente(utente) != null);
        return true;
    }

    /**
     * Implementa la funzionalità di registrazione di una biblioteca.
     *
     * @param utente la biblioteca da registrare
     * @return la view di login
     */
    @RequestMapping(value = "/biblioteca", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public boolean registrazioneBiblioteca(final UtenteRegistrato utente) {


        if (registrazioneService.isEmailRegistrata(utente.getEmail())) {
            return false;
        }

        utente.setTipo("Biblioteca");

        registrazioneService.registraUtente(utente);
        return true;
    }


    /**
     * Implementa la funzionalità di registrazione di
     * un lettore.
     * Gestisce la chiamata POST
     * per creare un nuovo lettore.
     *
     * @param utente  Il utente da registrare
     * @param password il campo conferma password del form per controllare
     *                 il corretto inserimento della stessa.
     * @return La view per effettuare il login
     */
    @RequestMapping(value = "/lettore", method = RequestMethod.POST)
    @CrossOrigin
    @ResponseBody
    public String registrazioneLettore(final UtenteRegistrato utente,
                                       final @RequestParam("conferma_password")
                                               String password) {


        if (registrazioneService.isEmailRegistrata(utente.getEmail())) {
            return "registrazione/registrazione_esperto";
        }

        utente.setTipo("Lettore");

        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte[] arr = md.digest(password.getBytes());

            if (Arrays.compare(arr, utente.getPassword()) != 0) {

                System.out.println("Questa password non va bene");
                return "registrazione/registrazione_esperto";

            } else if (password.length() <= 7) {

                return "registrazione/registrazione_esperto";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        registrazioneService.registraUtente(utente);
        return "redirect:/preferenze-di-lettura/generi";
    }

}
