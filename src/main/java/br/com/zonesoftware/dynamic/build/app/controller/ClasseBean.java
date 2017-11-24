package br.com.zonesoftware.dynamic.build.app.controller;

import br.com.zonesoftware.dynamic.build.app.model.Atributo;
import br.com.zonesoftware.dynamic.build.app.model.Classe;
import br.com.zonesoftware.dynamic.build.app.model.TipoAtributo;
import br.com.zonesoftware.dynamic.build.app.repository.ClasseRepository;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author daniel
 */
@Named
@ViewScoped
public class ClasseBean extends AbstractBean<Classe>{

    @Inject
    private ClasseRepository classeRepository;
    
    private Atributo atributo;

    public ClasseBean() {
        super(Classe.class);
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }
    
    @Override
    public ClasseRepository getRepository() {
        return classeRepository;
    }

    @Override
    public void preCadastro() {
        super.preCadastro();
        getEntity().setAtributos(new ArrayList<>());
    }
    
    /**
     * Métodos relativos a atributos
     */

    public void preCadastroAtributo(){
        atributo = new Atributo();
        atributo.setClasse(getEntity());
    }
    
    public void adicionarAtributo(){
        getEntity().getAtributos().add(atributo);
        atributo = null;
    }
    
    public void excluirAtributo(){
        getEntity().getAtributos().remove(atributo);
    }
    
    public TipoAtributo[] getTiposAtributos(){
        return TipoAtributo.values();
    }
    
}
