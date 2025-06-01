package com.chagas.gestaoespaco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestorDAO {

    private final HistoricoDAO historicoDAO = new HistoricoDAO();
    private final AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

    /**
     * Avalia uma solicitação, atualizando o status, registrando a avaliação,
     * adicionando ao histórico e à auditoria.
     *
     * @param idSolicitacao ID da solicitação a ser avaliada.
     * @param idGestor      ID do gestor que avalia.
     * @param status        Status: "APROVADA" ou "REJEITADA".
     * @param justificativa Justificativa da avaliação.
     * @return true se avaliação realizada com sucesso, false caso contrário.
     */
    public boolean avaliarSolicitacao(int idSolicitacao, int idGestor, String status, String justificativa) {
    	String updateSolicitacao = "UPDATE solicitacoes SET status = ?, justificativa_gestor = ?, id_gestor = ? WHERE id = ?";
    	String insertAvaliacao = "INSERT INTO avaliacoes (id_solicitacao, id_gestor, status, justificativa, data_avaliacao) VALUES (?, ?, ?, ?, NOW())";
    	
    	try (Connection conn = Conexao.getConnection()) {
    	    conn.setAutoCommit(false);

    	    try (
    	        PreparedStatement stmtUpdate = conn.prepareStatement(updateSolicitacao);
    	        PreparedStatement stmtInsert = conn.prepareStatement(insertAvaliacao)
    	    ) {
    	        // Atualiza a solicitação
    	        stmtUpdate.setString(1, status.toUpperCase());
    	        stmtUpdate.setString(2, justificativa);
    	        stmtUpdate.setInt(3, idGestor);
    	        stmtUpdate.setInt(4, idSolicitacao);
    	        stmtUpdate.executeUpdate();

    	        // Insere avaliação
    	        stmtInsert.setInt(1, idSolicitacao);
    	        stmtInsert.setInt(2, idGestor);
    	        stmtInsert.setString(3, status.toUpperCase());
    	        stmtInsert.setString(4, justificativa);
    	        stmtInsert.executeUpdate();

    	        // Histórico e Auditoria
    	        historicoDAO.registrarHistorico(idSolicitacao, "AVALIADO: " + status.toUpperCase());
    	        auditoriaDAO.registrarAcao(idGestor, "AVALIAR_SOLICITACAO ID " + idSolicitacao);

    	        conn.commit();
    	        System.out.println("Solicitação avaliada com sucesso.");
    	        return true;

    	    } catch (SQLException e) {
    	        conn.rollback();
    	        System.err.println("Erro ao avaliar solicitação: rollback realizado.");
    	        e.printStackTrace();
    	    }

    	} catch (SQLException e) {
    	    System.err.println("Erro de conexão ao avaliar solicitação.");
    	    e.printStackTrace();
    	}

    	return false;
    }
}
