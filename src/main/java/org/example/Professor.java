package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(namespace = "")
public class Professor {
    private String nome;
    private int idade;
    private int anoDoutorado;
    private ArrayList<Aluno> alunos;

    public Professor() {
    }

    public Professor(String nome, int idade, int anoDoutorado, ArrayList<Aluno> alunos) {
        super();
        this.nome = nome;
        this.idade = idade;
        this.anoDoutorado = anoDoutorado;
        this.alunos = alunos;
    }

    @XmlElement
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

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @XmlElement
    public int getAnoDoutorado() {
        return anoDoutorado;
    }

    public void setAnoDoutorado(int anoDoutorado) {
        this.anoDoutorado = anoDoutorado;
    }

    @XmlElement
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
}
