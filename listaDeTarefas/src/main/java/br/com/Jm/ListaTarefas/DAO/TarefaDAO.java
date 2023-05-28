package br.com.Jm.ListaTarefas.DAO;

import br.com.Jm.ListaTarefas.Modulos.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection connection;
    public TarefaDAO(Connection connection){
        this.connection = connection;
    }
    public List<Tarefa> Listar(){
        List<Tarefa> tarefas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TAREFA";
            try (PreparedStatement str = connection.prepareStatement(sql);) {
                str.execute();

                try (ResultSet rs = str.getResultSet()) {
                    while (rs.next()) {
                        Tarefa tarefa =
                                new Tarefa(rs.getString(2), rs.getString(4), rs.getInt(5), rs.getBoolean(3));
                        tarefas.add(tarefa);
                    }
                }
            }
            return tarefas;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void Inserir( Tarefa tarefa){
        String sql = "INSERT INTO TAREFA (NOME, STATUS, DESCRICAO, CATEGORIA_ID) VALUE(?, ?, ?, ?)";
        try {

            try (PreparedStatement prst = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                prst.setString(1, tarefa.getNome());
                prst.setBoolean(2, tarefa.getStatus());
                prst.setString(3, tarefa.getDescricao());
                prst.setInt(4, tarefa.getCategoria_id());
                prst.execute();

                try (ResultSet rs = prst.getGeneratedKeys()) {
                    while (rs.next()) {
                        tarefa.setId(rs.getInt(1));
                    }
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void Exlcuir(Integer id){
        String sql = "DELETE FROM TAREFA WHERE ID = ?";
        try {
            try (PreparedStatement pr = connection.prepareStatement(sql)) {
                pr.setInt(1, id);
                try {
                    pr.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
