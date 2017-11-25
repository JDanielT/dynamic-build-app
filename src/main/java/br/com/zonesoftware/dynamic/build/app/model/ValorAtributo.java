package br.com.zonesoftware.dynamic.build.app.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "valores_atributos")
@NamedQueries({
    @NamedQuery(name = ValorAtributo.BUSCAR_VALORES_POR_ATRIBUTO_INSTANCIA,
                query = "SELECT v FROM ValorAtributo v WHERE atributo.id = ?1 AND instancia.id = ?2")
})
public class ValorAtributo implements BaseEntity {
    
    public static final String BUSCAR_VALORES_POR_ATRIBUTO_INSTANCIA = "buscarValoresPorAtributoInstancia";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "instancia_id", referencedColumnName = "id")
    private Instancia instancia;
    
    @ManyToOne
    @JoinColumn(name = "atributo_id", referencedColumnName = "id")
    private Atributo atributo;
    
    @Column(columnDefinition = "TEXT")
    private String valor;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instancia getInstancia() {
        return instancia;
    }

    public void setInstancia(Instancia instancia) {
        this.instancia = instancia;
    }
    
    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final ValorAtributo other = (ValorAtributo) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
