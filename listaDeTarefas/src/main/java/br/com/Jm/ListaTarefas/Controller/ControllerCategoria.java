package br.com.Jm.ListaTarefas.Controller;

import br.com.Jm.ListaTarefas.ConnectionFactory.ConnectionFactory;
import br.com.Jm.ListaTarefas.DAO.CategoriaDAO;
import br.com.Jm.ListaTarefas.Modulos.Categoria;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;

import java.sql.Connection;
import java.util.List;

public class ControllerCategoria {
    private CategoriaDAO categoriaDAO;
    public ControllerCategoria(){
        Connection con = new ConnectionFactory().CriaConeccao();
        this.categoriaDAO = new CategoriaDAO(con);
    }

    public List<Categoria> listTarefasComCategoria(){
        return this.categoriaDAO.listarCategoria();
    }

    public void Concluir(Tarefa tarefa){
        this.categoriaDAO.Concluir(tarefa);
    }

    public List<Categoria> Listar(){
        return this.categoriaDAO.listar();
    }

    public List<Categoria> filtrar(Integer id){
        return this.categoriaDAO.filtrar(id);
    }
}
