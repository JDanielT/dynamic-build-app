package br.com.zonesoftware.dynamic.build.app.repository;

import br.com.zonesoftware.dynamic.build.app.model.Classe;
import br.com.zonesoftware.dynamic.build.app.model.Instancia;
import java.util.List;

/**
 *
 * @author daniel
 */
public class InstanciaRepository extends AbstractRepository<Instancia> {

    public InstanciaRepository() {
        super(Instancia.class);
    }
    
    public List<Instancia> buscarInstanciasPorClasse(Classe c){
        return super.listar(Instancia.BUSCAR_INSTANCIAS_POR_CLASSE, c.getId());
    }
    
}
