package br.com.jm.gerenciadorTarefas.Controller;

import br.com.jm.gerenciadorTarefas.DAO.CategoriaDAO;
import br.com.jm.gerenciadorTarefas.JUtils.Factory;
import br.com.jm.gerenciadorTarefas.Mods.Categoria;
import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.persistence.EntityManager;
import java.util.List;

public class ControllerCategoria {
    private CategoriaDAO categoriaDAO;

    public ControllerCategoria(){
        EntityManager em = Factory.getFactory();
        this.categoriaDAO = new CategoriaDAO(em);
    }

    public List<Categoria> listar(){
        return categoriaDAO.listar();
    }
    public List<CategoriaTarefaVO> filtrar(Integer id){
        return this.categoriaDAO.filtrar(id);
    }

}
