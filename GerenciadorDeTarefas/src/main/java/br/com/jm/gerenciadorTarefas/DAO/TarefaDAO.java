package br.com.jm.gerenciadorTarefas.DAO;

import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    private EntityManager em;

    public TarefaDAO(EntityManager em) {

        this.em = em;

    }

    public void insert(Tarefa tarefa){
        try{
            em.getTransaction().begin();
            em.persist(tarefa);
            tarefa.getCategoria().getListaTarefas().add(tarefa);
            em.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void delete(Integer id){
        Tarefa tarefa = em.find(Tarefa.class, id);
        em.getTransaction().begin();
        try{
            em.remove(tarefa);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        em.getTransaction().commit();
    }

    public List<CategoriaTarefaVO> listarTarefas(){
        String jpql = "SELECT NEW br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO " +
                "(t.id, t.nome, t.status, t.descricao, c.nome) " +
                "FROM Tarefa t JOIN t.categoria c";
        List<CategoriaTarefaVO> lista = em.createQuery(jpql, CategoriaTarefaVO.class).getResultList();
        return lista;
    }

    public void AlteraStatus(Integer id){
        Tarefa tarefa = em.find(Tarefa.class, id);
        em.getTransaction().begin();
        tarefa.setStatus(!tarefa.getStatus());
        em.getTransaction().commit();
    }


}
