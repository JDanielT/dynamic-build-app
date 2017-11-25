package br.com.zonesoftware.dynamic.build.app.controller;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.Classe;
import br.com.zonesoftware.dynamic.build.app.model.Instancia;
import br.com.zonesoftware.dynamic.build.app.model.ValorAtributo;
import br.com.zonesoftware.dynamic.build.app.repository.AbstractRepository;
import br.com.zonesoftware.dynamic.build.app.repository.ClasseRepository;
import br.com.zonesoftware.dynamic.build.app.repository.InstanciaRepository;
import br.com.zonesoftware.dynamic.build.app.repository.ValorAtributoRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */

@Named
@ViewScoped
public class InstanciaBean extends AbstractBean<Instancia>{

    @Inject
    private InstanciaRepository instanciaRepository;
    private Map<String, Object> valores = new HashMap<>();
    
    @Inject
    private ClasseRepository classeRepository;
    
    @Inject
    private ValorAtributoRepository valorAtributoRepository;
    
    // Para filtrar as instancias
    @Inject
    private Classe classe;
    
    public InstanciaBean() {
        super(Instancia.class);
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Map<String, Object> getValores() {
        return valores;
    }

    public void setValores(Map<String, Object> valores) {
        this.valores = valores;
    }
    
    @Override
    public AbstractRepository<Instancia> getRepository() {
        return instanciaRepository;
    }

    @Override
    public List<Instancia> getItens() {
        return super.itens;
    }

    @Override
    public void salvar() {        
        getEntity().setClasse(classe);
        getEntity().setValores(this.instanciarValores(getEntity(), valores));
        super.salvar();
        this.buscarInstancias();
    }
    
    public void buscarInstancias(){
        if(classe != null && classe.getId() != null){            
            //Buscando demais atributos
            classe = classeRepository.buscarPorId(classe.getId());            
            super.setItens(instanciaRepository.buscarInstanciasPorClasse(classe));            
        }   
    }
    
    public ValorAtributo buscarValorPorAtributo(Atributo a, Instancia i){
        return valorAtributoRepository.buscarValorPorAtributo(a, i);
    }
    
    private List<ValorAtributo> instanciarValores(Instancia instancia, Map<String, Object> valores) {

        List<ValorAtributo> valoresAtributos = new ArrayList<>();

        for (Atributo atributo : classe.getAtributos()) {

            String nome = atributo.getNome();
            Object valor = valores.get(nome);

            if (valor != null) {
                ValorAtributo valorAtributo = new ValorAtributo();
                valorAtributo.setAtributo(atributo);
                valorAtributo.setInstancia(instancia);
                valorAtributo.setValor(valor.toString());
                valoresAtributos.add(valorAtributo);                
            }
            
        }
        
        return valoresAtributos;
    }
    
}
