package br.com.jm.gerenciadorTarefas.DAO;

import br.com.jm.gerenciadorTarefas.Mods.Categoria;
import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private EntityManager em;

    public CategoriaDAO(EntityManager em){
        this.em = em;
    }

    public List<Categoria> listar(){
        String jpql = "SELECT c FROM Categoria c";
        return em.createQuery(jpql, Categoria.class).getResultList();
    }
    public List<CategoriaTarefaVO> filtrar(Integer id){
        List<CategoriaTarefaVO> lista = new ArrayList<>();
        String jpql = "SELECT NEW br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO " +
                "(t.id, t.nome, t.status, t.descricao, c.nome) " +
                "FROM Tarefa t JOIN t.categoria c WHERE t.categoria.id = :idCategoria";
        lista = em.createQuery(jpql, CategoriaTarefaVO.class).setParameter("idCategoria", id).getResultList();

        return lista;

    }

}
