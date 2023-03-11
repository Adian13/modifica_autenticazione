package it.unisa.c07.biblionet.autenticazione.controller;

import it.unisa.c07.biblionet.autenticazione.service.AutenticazioneService;
import it.unisa.c07.biblionet.model.entity.utente.UtenteRegistrato;
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
 * @author Alessio Casolaro, Antonio Della Porta
 */
@Controller
@SessionAttributes("loggedUser")
@RequiredArgsConstructor
public class AreaUtenteController {

    /**
     * Il service per effettuare le operazioni di persistenza.
     */
    private final AutenticazioneService autenticazioneService;

    /**
     * Implementa la funzionalità di smistare l'utente sulla view di
     * modifica dati corretta.
     * @param model Utilizzato per gestire la sessione.
     *
     * @return modifica_dati_biblioteca se l'account
     * da modificare é una biblioteca.
     *
     * modifica_dati_esperto se l'account
     * da modificare é un esperto.
     *
     * modifica_dati_lettore se l'account
     * da modificare é un lettore.
     */
    /*
    @RequestMapping(value = "/modifica-dati", method = RequestMethod.GET)
    public String modificaDati(final Model model) {
        UtenteRegistrato utente = (UtenteRegistrato)
                model.getAttribute("loggedUser");

        if (utente != null) {
            if (autenticazioneService.isBiblioteca(utente)) {
                Biblioteca biblioteca = (Biblioteca) utente;
                model.addAttribute("biblioteca", biblioteca);
                return "area-utente/modifica-dati-biblioteca";

            } else if (autenticazioneService.isEsperto(utente)) {
                Esperto esperto = (Esperto) utente;
                model.addAttribute("esperto", esperto);
                return "area-utente/modifica-dati-esperto";

            } else if (autenticazioneService.isLettore(utente)) {
                Lettore lettore = (Lettore) utente;
                model.addAttribute("lettore", lettore);
                return "area-utente/modifica-dati-lettore";

            }
        }
        return "autenticazione/login";
    }
*/

    /**
     * Implementa la funzionalità di modifica dati di una bibilioteca.
     *
     * @param model Utilizzato per gestire la sessione.
     * @param utente da modificare.
     * @param vecchia La vecchia password dell'account.
     * @param nuova La nuova password dell'account.
     * @param conferma La password di conferma password dell'account.
     *
     * @return login Se la modifica va a buon fine.
     * modifica_dati_biblioteca Se la modifica non va a buon fine
     */
    @RequestMapping(value = "/conferma-modifica-utente",
            method = RequestMethod.POST)
    public String confermaModificaUtente(final Model model,
                     final UtenteRegistrato utente,
                     final @RequestParam("vecchia_password")String vecchia,
                     final @RequestParam("nuova_password")String nuova,
                     final @RequestParam("conferma_password")String conferma) {


        UtenteRegistrato toUpdate = autenticazioneService
                .findUtenteByEmail(utente.getEmail());



        if (!vecchia.isEmpty() && !nuova.isEmpty() && !conferma.isEmpty()) {
            try {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA-256");
                byte[] vecchiaHash = md.digest(vecchia.getBytes());

                if (nuova.length() <= 7) {
                    return "area-utente/modifica-dati-biblioteca";
                }

                if (Arrays.compare(vecchiaHash,
                        toUpdate.getPassword()) == 0
                        &&
                        nuova.equals(conferma)
                ) {
                    utente.setPassword(nuova);
                } else {
                    return "area-utente/modifica-dati-biblioteca";
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        } else {
            utente.setHashedPassword(toUpdate.getPassword());
        }

        autenticazioneService.aggiornaUtente(utente);

        return "autenticazione/login";

    }

    /**
     * Implementa la funzionalità di visualizzazione area utente
     * in base al tipo.
     *
     * @param model Utilizzato per gestire la sessione.
     * @return La view di visualizzazione area utente
     */
    @RequestMapping(value = "/area-utente", method = RequestMethod.GET)
    public String areaUtente(final Model model) {
        UtenteRegistrato utente = (UtenteRegistrato)
                model.getAttribute("loggedUser");

        if (utente != null) {
            if (autenticazioneService.isBiblioteca(utente)) {
                //Biblioteca biblioteca = (Biblioteca) utente;
                //model.addAttribute("biblioteca", biblioteca);
                return "area-utente/visualizza-biblioteca";

            } else if (autenticazioneService.isEsperto(utente)) {
                //Esperto esperto = (Esperto) utente;
                //model.addAttribute("esperto", esperto);
                return "area-utente/visualizza-esperto";

            } else if (autenticazioneService.isLettore(utente)) {
                //Lettore lettore = (Lettore) utente;
                //model.addAttribute("lettore", lettore);
                return "area-utente/visualizza-lettore";

            }
        }
        return "autenticazione/login";
    }

    /**
     * Implementa la funzionalitá di visualizzazione dei clubs
     * a cui il lettore é iscritto.
     * @param model Utilizzato per gestire la sessione.
     * @return La view di visualizzazione dei clubs a cui é iscritto
     */
    /* todo altrove
    @RequestMapping(value = "area-utente/visualizza-clubs-personali-lettore",
            method = RequestMethod.GET)
    public String visualizzaClubsLettore(final Model model) {
        Lettore utente = (Lettore) model.getAttribute("loggedUser");
        if (utente != null && autenticazioneService.isLettore(utente)) {
            model.addAttribute("clubs",
                    autenticazioneService.findAllByLettori(utente));
            return "area-utente/visualizza-clubs-personali";
        }
        return "autenticazione/login";
    }
    */
    /**
     * Implementa la funzionalitá di visualizzazione dei clubs
     * che l'esperto gestisce.
     * @param model Utilizzato per gestire la sessione.
     * @return La view di visualizzazione dei clubs che gestisce
     */
    /* todo altrove
    @RequestMapping(value = "area-utente/visualizza-clubs-personali-esperto",
            method = RequestMethod.GET)
    public String visualizzaClubsEsperto(final Model model) {
        Esperto utente = (Esperto) model.getAttribute("loggedUser");
        if (utente != null && autenticazioneService.isEsperto(utente)) {
            model.addAttribute("clubs",
                    autenticazioneService.findAllByEsperto(utente));
            return "area-utente/visualizza-clubs-personali";
        }
        return "autenticazione/login";
    }
    */
}
