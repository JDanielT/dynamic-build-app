package br.com.zonesoftware.dynamic.build.app.controller;

import br.com.zonesoftware.dynamic.build.app.util.FacesMessages;
import br.com.zonesoftware.dynamic.build.app.model.BaseEntity;
import br.com.zonesoftware.dynamic.build.app.repository.AbstractRepository;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author daniel
 */
public abstract class AbstractBean<T extends BaseEntity> implements Serializable {
    
    private Class<T> entityClass;
    private T entity;
    protected List<T> itens;
    
    @Inject
    protected FacesMessages messages;

    public AbstractBean() {
    }
    
    public AbstractBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
    
    public abstract AbstractRepository<T> getRepository();
    
    public void preCadastro(){
        try {
            entity = entityClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AbstractBean.class.getName()).log(Level.SEVERE, null, ex);
            messages.error("Ocorreu um erro ao iniciar a operação. Erro: " + ex.getMessage());
        }
    }
    
    public void salvar(){
        try {
            getRepository().salvar(entity);
        } catch (Exception ex) {
            Logger.getLogger(AbstractBean.class.getName()).log(Level.SEVERE, null, ex);
            messages.error("Ocorreu um erro ao salvar. Erro: " + ex.getMessage());
        }
        setEntity(null);
    }
    
    public void excluir(){
        try {
            getRepository().excluir(entity);
        } catch (Exception ex) {
            Logger.getLogger(AbstractBean.class.getName()).log(Level.SEVERE, null, ex);
            messages.error("Ocorreu um erro ao excluir. Erro: " + ex.getMessage());
        }
    }
    
    public List<T> getItens(){
        return getRepository().listarTodos();
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }
    
}
