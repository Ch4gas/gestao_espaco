package com.chagas.gestaoespaco.testes;

import com.chagas.gestaoespaco.dao.*;

public class TesteSolicitacao {
public static void main(String[] args) {
SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
GestorDAO gestorDAO = new GestorDAO();
System.out.println("Testando criação de solicitação...");
solicitacaoDAO.criarSolicitacao(1, 2, "2025-06-15 09:00:00");

System.out.println("Testando listagem de solicitações...");
solicitacaoDAO.listarSolicitacoes();

System.out.println("Testando avaliação de solicitação...");
boolean resultado = gestorDAO.avaliarSolicitacao(1, 3, "REJEITADA", "Horário indisponível.");
System.out.println("Avaliação realizada: " + resultado);
}
}