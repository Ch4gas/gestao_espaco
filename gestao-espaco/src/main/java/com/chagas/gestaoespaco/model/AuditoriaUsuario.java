package com.chagas.gestaoespaco.model;

import java.time.LocalDateTime;

public class AuditoriaUsuario {
    private int id;
    private int idUsuario;
    private String acao;
    private LocalDateTime data;

    public AuditoriaUsuario() {}

    public AuditoriaUsuario(int id, int idUsuario, String acao, LocalDateTime data) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.acao = acao;
        this.data = data;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsuarioId() {
        return idUsuario	;
    }
    public void setUsuarioId(int usuarioId) {
        this.idUsuario = usuarioId;
    }
    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
}