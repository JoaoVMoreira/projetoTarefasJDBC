package br.com.Jm.ListaTarefas.DAO;

import br.com.Jm.ListaTarefas.Modulos.Categoria;
import br.com.Jm.ListaTarefas.Modulos.Tarefa;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection){
        this.connection = connection;
    }

    public List<Categoria> listar(){
        try {
            List<Categoria> categorias = new ArrayList<>();

            String sql = "SELECT ID, NOME FROM CATEGORIA";

            try (PreparedStatement st = connection.prepareStatement(sql)) {
                st.execute();

                try (ResultSet rs = st.getResultSet()) {
                    while (rs.next()) {
                        Categoria categoria =
                                new Categoria(rs.getString(2));
                        categorias.add(categoria);
                        categoria.setId(rs.getInt(1));
                    }
                }
            }
            return categorias;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Categoria> listarCategoria(){
        String sql = "SELECT T.NOME, T.STATUS, T.DESCRICAO, T.CATEGORIA_ID, C.NOME, T.ID FROM TAREFA T INNER JOIN CATEGORIA C ON T.CATEGORIA_ID = C.ID";
        List<Categoria> categorias = new ArrayList<>();

        try {
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.execute();
                try (ResultSet rs = pr.getResultSet()) {
                    while (rs.next()) {
                        Categoria categoria = new Categoria(rs.getString(5));
                        Tarefa tarefa = new Tarefa(rs.getString(1), rs.getString(3), rs.getInt(4), rs.getBoolean(2));
                        tarefa.setId(rs.getInt(6));
                        categoria.adicionar(tarefa);
                        categorias.add(categoria);
                    }
                }
            }
            return categorias;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void Concluir(Tarefa tarefa) {
        String sql = "UPDATE TAREFA SET STATUS = TRUE WHERE ID = ?";
        Integer id = tarefa.getId();

        if(tarefa.getStatus() == true){
            sql = "UPDATE TAREFA SET STATUS = FALSE WHERE ID = ?";
        }

        try {
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setInt(1, id);
                pr.execute();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Categoria> filtrar(Integer id){
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT T.NOME, T.STATUS, T.DESCRICAO, T.CATEGORIA_ID, C.NOME, T.ID FROM TAREFA T INNER JOIN CATEGORIA C ON T.CATEGORIA_ID = C.ID WHERE C.ID = ?";
        try {
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setInt(1, id);
                pr.execute();

                try(ResultSet rs = pr.getResultSet()){
                    while(rs.next()){
                        Categoria categoria = new Categoria(rs.getString(5));
                        Tarefa tarefa = new Tarefa(rs.getString(1), rs.getString(3), rs.getInt(4), rs.getBoolean(2));
                        tarefa.setId(rs.getInt(6));
                        categoria.adicionar(tarefa);
                        categorias.add(categoria);
                    }
                }
                return categorias;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
