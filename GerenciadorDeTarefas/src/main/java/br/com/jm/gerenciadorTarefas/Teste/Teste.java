package br.com.jm.gerenciadorTarefas.Teste;

import br.com.jm.gerenciadorTarefas.DAO.CategoriaDAO;
import br.com.jm.gerenciadorTarefas.DAO.TarefaDAO;
import br.com.jm.gerenciadorTarefas.JUtils.Factory;
import br.com.jm.gerenciadorTarefas.Mods.Categoria;
import br.com.jm.gerenciadorTarefas.Mods.Tarefa;
import br.com.jm.gerenciadorTarefas.VO.CategoriaTarefaVO;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        EntityManager em = Factory.getFactory();
        Categoria categoria = em.find(Categoria.class, 1);
        Tarefa tarefa = new Tarefa("Finalizar robo tiago", "Finalizar o robo para compilar as producoes", categoria);
        TarefaDAO tarefaDAO = new TarefaDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);


        //tarefaDAO.insert(tarefa);
        //tarefaDAO.delete(3);

//        List<Categoria> teste = categoriaDAO.listar();
//
//        for (Categoria c:teste) {
//            System.out.println(c);
//        }
//
//        System.out.println("**********************************************");
//
//        List<CategoriaTarefaVO> lista = tarefaDAO.listarTarefas();
//
//        for (CategoriaTarefaVO item : lista) {
//            System.out.println(item);
//        }
//
//        System.out.println("**********************************************");
//        tarefaDAO.AlteraStatus(4);
        List<CategoriaTarefaVO> listTarefa = new ArrayList<>();
         listTarefa = categoriaDAO.filtrar(1);

        for (CategoriaTarefaVO item:listTarefa) {
            System.out.println(item);
        }

        System.out.println(tarefa.getCategoria().getId());



    }
}
