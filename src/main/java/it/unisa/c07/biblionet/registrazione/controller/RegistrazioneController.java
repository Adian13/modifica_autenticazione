package it.unisa.c07.biblionet.registrazione.controller;

import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
import it.unisa.c07.biblionet.registrazione.service.RegistrazioneService;
import it.unisa.c07.biblionet.utils.validazione.RegexTester;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String visualizzaRegistrazione() {
        return "registrazione/registrazione";
    }

    /**
     * Implementa la funzionalità di registrazione di
     * scegliere il tipo di utente da registrare.
     *
     * @param scelta Il tipo di utente da registrare
     * @return La view che visualizza il form di registrazione scelto.
     */
    @RequestMapping(value = "/scegli", method = RequestMethod.POST)
    public String scegliRegistrazione(final @RequestParam("scelta")
                                              String scelta) {
        return "registrazione/registrazione_" + scelta.toLowerCase();
    }

    /**
     * Implementa la funzionalità di registrazione di un esperto.
     *
     * @param utente l'utente da registrare
     * @param password il campo conferma password del form per controllare
     *                 il corretto inserimento della stessa
     * @return la view per effettuare il login
     */
    @RequestMapping(value = "/esperto", method = RequestMethod.POST)
    public String registrazioneEsperto(final UtenteRegistrato utente,
                                       final @RequestParam("conferma_password")
                                               String password) {

        if (registrazioneService.isEmailRegistrata(utente.getEmail())) {
            return "registrazione/registrazione_esperto";
        }

        utente.setTipo("Esperto");

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

    /**
     * Implementa la funzionalità di registrazione di una biblioteca.
     *
     * @param utente la biblioteca da registrare
     * @param password   la password di conferma
     * @return la view di login
     */
    @RequestMapping(value = "/biblioteca", method = RequestMethod.POST)
    public String registrazioneBiblioteca(final UtenteRegistrato utente,
                                 final @RequestParam("conferma_password")
                                                  String password) {


        if (registrazioneService.isEmailRegistrata(utente.getEmail())) {
            return "registrazione/registrazione_esperto";
        }

        utente.setTipo("Biblioteca");

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
