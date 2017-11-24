package br.com.zonesoftware.dynamic.build.app.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "instancias")
@NamedQueries({
    @NamedQuery(name = Instancia.BUSCAR_INSTANCIAS_POR_CLASSE,
                query = "SELECT i FROM Instancia i WHERE classe.id = ?")
})
public class Instancia implements BaseEntity {

    public static final String BUSCAR_INSTANCIAS_POR_CLASSE = "buscarInstanciasPorClasse";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @OneToMany
    private List<ValorAtributo> valores;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<ValorAtributo> getValores() {
        return valores;
    }

    public void setValores(List<ValorAtributo> valores) {
        this.valores = valores;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instancia other = (Instancia) obj;
        return Objects.equals(this.id, other.id);
    }

}
