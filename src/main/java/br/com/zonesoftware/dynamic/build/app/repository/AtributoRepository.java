package br.com.zonesoftware.dynamic.build.app.repository;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.Classe;
import java.util.List;

/**
 *
 * @author daniel
 */
public class AtributoRepository extends AbstractRepository<Atributo>{

    public AtributoRepository() {
        super(Atributo.class);
    }
    
    public List<Atributo> buscarAtributosPorClasse(Classe c){
        return listar(Atributo.BUSCAR_ATRIBUTOS_POR_CLASSE, c.getId());
    }
 
}
