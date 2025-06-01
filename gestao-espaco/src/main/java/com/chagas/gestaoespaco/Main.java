package com.chagas.gestaoespaco;

import com.chagas.gestaoespaco.dao.*;

public class Main {
public static void main(String[] args) {
SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
GestorDAO gestorDAO = new GestorDAO();
ConsultaSolicitacoes consulta = new ConsultaSolicitacoes();
// Criar uma nova solicitação
solicitacaoDAO.criarSolicitacao(1, 2, "2025-06-10 10:00:00");

// Listar todas as solicitações
solicitacaoDAO.listarSolicitacoes();

// Avaliar a solicitação como aprovada
gestorDAO.avaliarSolicitacao(1, 3, "APROVADA", "Tudo certo com a reserva.");

// Listar novamente para ver atualização
solicitacaoDAO.listarSolicitacoes();

// Consultar solicitações aprovadas
consulta.listarSolicitacoesAprovadas();
}
}