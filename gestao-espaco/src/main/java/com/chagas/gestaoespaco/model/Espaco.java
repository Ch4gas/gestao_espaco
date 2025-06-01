package com.chagas.gestaoespaco.model;

public class Espaco {
    private int id;
    private String nome;
    private int metragem;
    private String equipamentos;

    public Espaco() {}

    public Espaco(int id, String nome, int metragem, String equipamentos) {
        this.id = id;
        this.nome = nome;
        this.metragem = metragem;
        this.equipamentos = equipamentos;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getMetragem() { return metragem; }
    public void setMetragem(int metragem) { this.metragem = metragem; }

    public String getEquipamentos() { return equipamentos; }
    public void setEquipamentos(String equipamentos) { this.equipamentos = equipamentos; }

    @Override
    public String toString() {
        return "Espaco{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", metragem=" + metragem +
                ", equipamentos='" + equipamentos + '\'' +
                '}';
    }
}