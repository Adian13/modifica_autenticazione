package it.unisa.c07.biblionet.model.entity.utente;

import it.unisa.c07.biblionet.model.entity.Libro;
import it.unisa.c07.biblionet.model.entity.TicketPrestito;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Biblioteca extends UtenteRegistrato{
    @Column(nullable = false, length = 30)
    @NonNull
    String nomeBiblioteca;

    @OneToMany
    private List<Esperto>esperti;

    @OneToMany
    private List<TicketPrestito>tickets;

    public Biblioteca(String email, String password, String provincia, String citta, String via, String recapitoTelefonico, String nomeBiblioteca) {
        super(email, password, provincia, citta, via, recapitoTelefonico);
        this.nomeBiblioteca = nomeBiblioteca;
    }

}
