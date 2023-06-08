package br.com.jm.gerenciadorTarefas.JUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Factory {
    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("gerenciador_tarefas");

    public static EntityManager getFactory(){
        return FACTORY.createEntityManager();
    }
}
