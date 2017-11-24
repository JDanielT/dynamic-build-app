package br.com.zonesoftware.dynamic.build.app.controller;

import br.com.zonesoftware.dynamic.build.app.model.Classe;
import br.com.zonesoftware.dynamic.build.app.repository.ClasseRepository;
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

    public ClasseBean() {
        super(Classe.class);
    }
    
    @Override
    public ClasseRepository getRepository() {
        return classeRepository;
    }
    
}
