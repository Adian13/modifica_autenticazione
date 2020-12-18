package it.unisa.c07.biblionet.model.entity;

import it.unisa.c07.biblionet.model.entity.utente.Esperto;
import it.unisa.c07.biblionet.model.entity.utente.Lettore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genere {

    @Id
    @Size(max = 30)
    private String nome;

    @NotNull
    @Size(max = 255)
    private String descrizione;

    @ManyToMany
    private List<Lettore>lettori;

    @ManyToMany
    private List<Esperto>esperti;

    @ManyToMany
    private List<Libro> libri;

    @ManyToMany
    private List<ClubDelLibro> clubs;
}
