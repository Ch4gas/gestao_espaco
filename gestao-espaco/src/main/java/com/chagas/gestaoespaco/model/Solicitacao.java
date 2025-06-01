package com.chagas.gestaoespaco.model;

import java.time.LocalDateTime;

public class Solicitacao {
    private int id;
    private int idEspaco;
    private int idSolicitante;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataReserva;
    private String status;
    private int idGestor;
    private String justificativaGestor;
    private LocalDateTime dataAvaliacao;

    public Solicitacao() {}

    public Solicitacao(int id, int idEspaco, int idSolicitante, LocalDateTime dataSolicitacao,
                       LocalDateTime dataReserva, String status, int idGestor,
                       String justificativaGestor, LocalDateTime dataAvaliacao) {
        this.id = id;
        this.idEspaco = idEspaco;
        this.idSolicitante = idSolicitante;
        this.dataSolicitacao = dataSolicitacao;
        this.dataReserva = dataReserva;
        this.status = status;
        this.idGestor = idGestor;
        this.justificativaGestor = justificativaGestor;
        this.dataAvaliacao = dataAvaliacao;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdEspaco() {
        return idEspaco;
    }
    public void setIdEspaco(int idEspaco) {
        this.idEspaco = idEspaco;
    }
    public int getIdSolicitante() {
        return idSolicitante;
    }
    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }
    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
    public LocalDateTime getDataReserva() {
        return dataReserva;
    }
    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getIdGestor() {
        return idGestor;
    }
    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }
    public String getJustificativaGestor() {
        return justificativaGestor;
    }
    public void setJustificativaGestor(String justificativaGestor) {
        this.justificativaGestor = justificativaGestor;
    }
    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}