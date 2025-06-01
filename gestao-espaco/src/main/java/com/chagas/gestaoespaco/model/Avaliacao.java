package com.chagas.gestaoespaco.model;

import java.time.LocalDateTime;

public class Avaliacao {
    private int id;
    private int idSolicitacao;
    private int idGestor;
    private String status;
    private String justificativa;
    private LocalDateTime dataAvaliacao;

    public Avaliacao() {}

    public Avaliacao(int id, int idSolicitacao, int idGestor, String status, String justificativa, LocalDateTime dataAvaliacao) {
        this.id = id;
        this.idSolicitacao = idSolicitacao;
        this.idGestor = idGestor;
        this.status = status;
        this.justificativa = justificativa;
        this.dataAvaliacao = dataAvaliacao;
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
    public int getIdGestor() {
        return idGestor;
    }
    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getJustificativa() {
        return justificativa;
    }
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}