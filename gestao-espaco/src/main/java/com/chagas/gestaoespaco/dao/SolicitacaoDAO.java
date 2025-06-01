package com.chagas.gestaoespaco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class SolicitacaoDAO {
	private final HistoricoDAO historicoDAO = new HistoricoDAO();
	private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

	// ✅ CREATE - Criar uma nova solicitação
	public void criarSolicitacao(int idEspaco, int idSolicitante, String dataReserva) {
		if (verificarConflitoReserva(idEspaco, dataReserva)) {
			System.out.println("Conflito: já existe reserva para esse espaço nesse horário.");
			return;
		}
	    String sql = "INSERT INTO solicitacoes (id_espaco, id_solicitante, data_solicitacao, data_reserva, status) " +
	                 "VALUES (?, ?, NOW(), ?, 'PENDENTE')";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        Timestamp dataTimestamp = Timestamp.valueOf(dataReserva);
	        stmt.setInt(1, idEspaco);
	        stmt.setInt(2, idSolicitante);
	        stmt.setTimestamp(3, dataTimestamp);

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            // Recuperar ID gerado
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    int idSolicitacao = generatedKeys.getInt(1);

	                    // Registra no histórico e auditoria
	                    historicoDAO.registrarHistorico(idSolicitacao, "CRIADA");
	                    auditoriaDAO.registrarAcao(idSolicitante, "CRIAR_SOLICITACAO ID " + idSolicitacao);
	                }
	            }
	            System.out.println("Solicitação criada com sucesso.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        System.out.println("Formato inválido para data. Use o padrão: yyyy-MM-dd HH:mm:ss.");
	    }
	}

	// ✅ READ - Listar todas as solicitações
	public void listarSolicitacoes() {
	    String sql = "SELECT s.id, e.nome AS espaco, u.nome AS solicitante, s.data_reserva, s.status " +
	                 "FROM solicitacoes s " +
	                 "JOIN espacos e ON s.id_espaco = e.id " +
	                 "JOIN usuarios u ON s.id_solicitante = u.id";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

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

	// ✅ READ - Buscar solicitação específica por ID
	public void buscarSolicitacaoPorId(int id) {
	    String sql = "SELECT s.id, e.nome AS espaco, u.nome AS solicitante, s.data_reserva, s.status " +
	                 "FROM solicitacoes s " +
	                 "JOIN espacos e ON s.id_espaco = e.id " +
	                 "JOIN usuarios u ON s.id_solicitante = u.id " +
	                 "WHERE s.id = ?";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                System.out.println("ID: " + rs.getInt("id"));
	                System.out.println("Espaço: " + rs.getString("espaco"));
	                System.out.println("Solicitante: " + rs.getString("solicitante"));
	                System.out.println("Data Reserva: " + rs.getTimestamp("data_reserva"));
	                System.out.println("Status: " + rs.getString("status"));
	            } else {
	                System.out.println("Solicitação não encontrada.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// ✅ UPDATE - Atualizar status da solicitação
	public void atualizarStatusSolicitacao(int idSolicitacao, String novoStatus, String justificativa, int idGestor) {
	    String sql = "UPDATE solicitacoes SET status = ?, justificativa_gestor = ?, id_gestor = ?, data_avaliacao = NOW() WHERE id = ?";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, novoStatus.toUpperCase());
	        stmt.setString(2, justificativa);
	        stmt.setInt(3, idGestor);
	        stmt.setInt(4, idSolicitacao);

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println("Status atualizado com sucesso.");

	            // Registra no histórico e auditoria
	            historicoDAO.registrarHistorico(idSolicitacao, "STATUS ALTERADO PARA: " + novoStatus);
	            auditoriaDAO.registrarAcao(idGestor, "ATUALIZOU STATUS DA SOLICITACAO ID " + idSolicitacao);
	        } else {
	            System.out.println("Solicitação não encontrada.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	// ✅ DELETE - Excluir solicitação
	public void excluirSolicitacao(int idSolicitacao, int idGestor) {
	    String sql = "DELETE FROM solicitacoes WHERE id = ?";

	    try (Connection conn = Conexao.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, idSolicitacao);

	        int rows = stmt.executeUpdate();
	        if (rows > 0) {
	            System.out.println("Solicitação excluída com sucesso.");

	            // Registra no histórico e auditoria
	            historicoDAO.registrarHistorico(idSolicitacao, "SOLICITACAO EXCLUÍDA");
	            auditoriaDAO.registrarAcao(idGestor, "EXCLUIU SOLICITACAO ID " + idSolicitacao);
	        } else {
	            System.out.println("Solicitação não encontrada.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public boolean verificarConflitoReserva(int idEspaco, String dataReserva) {
		String sql = "SELECT COUNT(*) FROM solicitacoes WHERE id_espaco = ? AND data_reserva = ?";
		try (Connection conn = Conexao.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)) {
		stmt.setInt(1, idEspaco);
		stmt.setTimestamp(2, Timestamp.valueOf(dataReserva));
		try (ResultSet rs = stmt.executeQuery()) {
		if (rs.next()) {
		return rs.getInt(1) > 0;
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}
		return false;
		}
}