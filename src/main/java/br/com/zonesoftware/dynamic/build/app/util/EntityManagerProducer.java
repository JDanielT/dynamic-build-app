package br.com.zonesoftware.dynamic.build.app.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory factory;

    public EntityManagerProducer() {
        reload();
    }

    @Produces
    @RequestScoped
    public EntityManager createEntityManager() {
        return this.factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager manager) {
        manager.close();
    }
    
    public void reload(){
        this.factory = Persistence.createEntityManagerFactory("compile-app");
    }

}
