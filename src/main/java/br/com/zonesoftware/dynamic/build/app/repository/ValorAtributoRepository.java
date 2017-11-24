package br.com.zonesoftware.dynamic.build.app.repository;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.ValorAtributo;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ValorAtributoRepository extends AbstractRepository<ValorAtributo> {

    public ValorAtributoRepository() {
        super(ValorAtributo.class);
    }
    
    public List<ValorAtributo> buscarValoresPorAtributo(Atributo a){
        return super.listar(ValorAtributo.BUSCAR_VALORES_POR_ATRIBUTO, a.getId());
    }
    
}
