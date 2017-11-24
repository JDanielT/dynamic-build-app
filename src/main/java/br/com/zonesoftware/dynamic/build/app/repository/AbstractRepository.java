package br.com.zonesoftware.dynamic.build.app.repository;

import br.com.zonesoftware.dynamic.build.app.util.Transacional;
import br.com.zonesoftware.dynamic.build.app.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author daniel
 * @param <T>
 */
public abstract class AbstractRepository<T extends BaseEntity> implements Serializable {

    @Inject
    private EntityManager manager;
    private Class entityClass;

    public AbstractRepository(Class entityClass) {
        this.entityClass = entityClass;
    }
    
    @Transacional
    public void salvar(T t) throws Exception {
        if (t.getId() == null) {
            manager.persist(t);
        } else {
            manager.merge(t);
        }
    }

    @Transacional
    public void excluir(T entity) throws Exception {
        BaseEntity entityToBeRemoved = (BaseEntity) manager.find(entityClass, entity.getId());
        manager.remove(entityToBeRemoved);
    }

    public T buscarPorId(Object id) {
        return (T) manager.find(entityClass, id);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<T> listarTodos() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.orderBy(cb.asc(root.get("id")));
        cq.select(root);
        List<T> resultado = manager.createQuery(cq).getResultList();
        return resultado;
    }

    protected List<T> listar(String namedQuery, Object... params) {
        Query q = manager.createNamedQuery(namedQuery);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        List<T> resultado = q.getResultList();
        return resultado;
    }

    @SuppressWarnings("unchecked")
    protected T buscarUmResultado(String namedQuery, Object... params) {
        T result = null;
        Query q = manager.createNamedQuery(namedQuery);
        for (int i = 0; i < params.length; i++) {
            q.setParameter(i + 1, params[i]);
        }
        result = (T) q.getSingleResult();
        return result;
    }

}