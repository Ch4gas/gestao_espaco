package com.chagas.gestaoespaco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaSolicitacoes {
    public void listarSolicitacoes() {
    	String sql = "SELECT s.id, e.nome AS espaco, u.nome AS solicitante, s.data_reserva, s.status " +
    	"FROM solicitacoes s " +
    	"JOIN espacos e ON s.id_espaco = e.id " +
    	"JOIN usuarios u ON s.id_solicitante = u.id";
    	try (Connection conn = Conexao.getConnection();
    		     PreparedStatement stmt = conn.prepareStatement(sql);
    		     java.sql.ResultSet rs = stmt.executeQuery()) {

    		    while (rs.next()) {
    		        System.out.println("ID: " + rs.getInt("id"));
    		        System.out.println("Espaço: " + rs.getString("espaco"));
    		        System.out.println("Solicitante: " + rs.getString("solicitante"));
    		        System.out.println("Data Reserva: " + rs.getTimestamp("data_reserva"));
    		        System.out.println("Status: " + rs.getString("status"));
    		        System.out.println("---------------------------");
    		    }
    		} catch (SQLException e) {
    		    e.printStackTrace();
    		}
    }
	
	
    public void listarSolicitacoesAprovadas() {
        String sql = "SELECT s.id, e.nome AS espaco, s.data_reserva, u.nome AS solicitante " +
                     "FROM solicitacoes s " +
                     "JOIN espacos e ON s.id_espaco = e.id " +
                     "JOIN usuarios u ON s.id_solicitante = u.id " +
                     "WHERE s.status = 'APROVADA'";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Espaço: " + rs.getString("espaco"));
                System.out.println("Data Reserva: " + rs.getString("data_reserva"));
                System.out.println("Solicitante: " + rs.getString("solicitante"));
                System.out.println("-------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

