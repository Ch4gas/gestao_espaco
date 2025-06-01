package com.chagas.gestaoespaco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class HistoricoDAO {
	public void registrarHistorico(int idSolicitacao, String acao) {
	    if (acao == null || acao.trim().isEmpty()) {
	        System.err.println("Ação não pode ser nula ou vazia.");
	        return;
	    }

	    String sql = "INSERT INTO historico_solicitacoes (id_solicitacao, acao) VALUES (?, ?)";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idSolicitacao);
	        stmt.setString(2, acao);

	        stmt.executeUpdate();

	        System.out.println("Histórico registrado para solicitação ID " + idSolicitacao + ": " + acao);

	    } catch (SQLException e) {
	        System.err.println("Erro ao registrar histórico para solicitação ID " + idSolicitacao);
	        e.printStackTrace();
	    }
	}

}