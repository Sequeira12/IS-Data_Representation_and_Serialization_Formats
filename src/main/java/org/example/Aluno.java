package org.example;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "")
public class Aluno {
    private String nome;
    private int idade;
    private int avaliacao;

    public Aluno() {

    }

    public Aluno(String nome, int idade, int avaliacao) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.avaliacao = avaliacao;
    }

    @XmlAttribute(namespace = "")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @XmlElement
    public int getIdade() {
        return idade;
    }

    public void setIdade(int ida) {
        this.idade = ida;
    }
    @XmlElement
    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int ava) {
        this.avaliacao = ava;
    }
}
