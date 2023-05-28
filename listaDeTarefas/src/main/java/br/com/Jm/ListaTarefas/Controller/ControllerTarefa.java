package br.com.Jm.ListaTarefas.Controller;

import br.com.Jm.ListaTarefas.ConnectionFactory.ConnectionFactory;
import br.com.Jm.ListaTarefas.DAO.TarefaDAO;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;

import java.sql.Connection;
import java.util.List;

public class ControllerTarefa {
    private TarefaDAO tarefaDAO;

    public ControllerTarefa(){
        Connection con = new ConnectionFactory().CriaConeccao();
        this.tarefaDAO = new TarefaDAO(con);
    }

    public List<Tarefa> Listar(){
        return tarefaDAO.Listar();
    }

    public void Salvar(Tarefa tarefa){
        this.tarefaDAO.Inserir(tarefa);
    }

    public void Excluir(Integer id){
        this.tarefaDAO.Exlcuir(id);
    }

}
