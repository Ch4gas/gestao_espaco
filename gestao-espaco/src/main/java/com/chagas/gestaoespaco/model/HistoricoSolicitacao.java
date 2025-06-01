package com.chagas.gestaoespaco.model;

import java.time.LocalDateTime;

public class HistoricoSolicitacao {
    private int id;
    private int idSolicitacao;
    private LocalDateTime data;
    private String acao;

    public HistoricoSolicitacao() {}

    public HistoricoSolicitacao(int id, int idSolicitacao, LocalDateTime data, String acao) {
        this.id = id;
        this.idSolicitacao = idSolicitacao;
        this.data = data;
        this.acao = acao;
    }
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public int getIdSolicitacao() {
    return idSolicitacao;
}
public void setIdSolicitacao(int idSolicitacao) {
    this.idSolicitacao = idSolicitacao;
}
public LocalDateTime getData() {
    return data;
}
public void setData(LocalDateTime data) {
    this.data = data;
}
public String getAcao() {
    return acao;
}
public void setAcao(String acao) {
    this.acao = acao;
}
}