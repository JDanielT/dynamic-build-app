package br.com.zonesoftware.dynamic.build.app.repository;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.Instancia;
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
    
    public ValorAtributo buscarValorPorAtributo(Atributo a, Instancia i){
        return super.buscarUmResultado(ValorAtributo.BUSCAR_VALORES_POR_ATRIBUTO_INSTANCIA, a.getId(), i.getId());
    }
    
}
