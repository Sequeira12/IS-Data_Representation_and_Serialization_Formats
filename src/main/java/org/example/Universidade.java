package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(namespace = "")
public class Universidade {
    private String nome;
    private int anoInstituicao;
    private ArrayList<Professor> professores;

    public Universidade() {
    }

    public Universidade(String nome, int anoInstituicao, ArrayList<Professor> professores) {
        super();
        this.nome = nome;
        this.anoInstituicao = anoInstituicao;
        this.professores = professores;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @XmlElement
    public int getAnoInstituicao() {
        return anoInstituicao;
    }

    public void setAnoInstituicao(int anoInstituicao) {
        this.anoInstituicao = anoInstituicao;
    }
    @XmlElement
    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(ArrayList<Professor> professores) {
        this.professores = professores;
    }
}
