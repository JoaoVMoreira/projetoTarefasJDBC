package br.com.jm.gerenciadorTarefas.Controller;

import br.com.jm.gerenciadorTarefas.DAO.TarefaDAO;
import br.com.jm.gerenciadorTarefas.JUtils.Factory;
import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.persistence.EntityManager;
import java.util.List;

public class ControllerTarefa {
    private TarefaDAO tarefaDAO;

    public ControllerTarefa(){
        EntityManager em = Factory.getFactory();
        this.tarefaDAO = new TarefaDAO(em);
    }

    public List<CategoriaTarefaVO> listTarefas(){
        return tarefaDAO.listarTarefas();
    }

    public void incluir(Tarefa tarefa){
        this.tarefaDAO.insert(tarefa);
    }

    public void excluir(Integer id){
        this.tarefaDAO.delete(id);
    }



    public void concluir(Integer id){
        this.tarefaDAO.AlteraStatus(id);
    }
}
