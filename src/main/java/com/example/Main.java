package com.example;

import javax.xml.bind.JAXBException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws JAXBException, IOException {

        com.example.Arquivo.Universidade.Builder universidadeBuilder = com.example.Arquivo.Universidade.newBuilder();
        universidadeBuilder.setNome("Universidade de Coimbra");
        universidadeBuilder.setAnoInstituicao(1754);

        ArrayList<Arquivo.Professor> professores = new ArrayList<>();

        //numero profs
        int num = 5;

        for (int i = 1; i <= num; i++) {

            com.example.Arquivo.Professor.Builder professorBuilder = com.example.Arquivo.Professor.newBuilder();
            professorBuilder.setNome("prof" + i);
            professorBuilder.setIdade(60);
            professorBuilder.setAnoDoutorado(1909);

            ArrayList<com.example.Arquivo.Aluno> alunos = new ArrayList<>();


            for (int j = 1; j <= 20; j++) {
                com.example.Arquivo.Aluno.Builder alunoBuilder = com.example.Arquivo.Aluno.newBuilder();
                alunoBuilder.setNome("Aluno" + j);
                alunoBuilder.setIdade(18);
                alunoBuilder.setAvaliacao(20);
                alunos.add(alunoBuilder.build());
            }


            professorBuilder.addAllAlunos(alunos);


            professores.add(professorBuilder.build());
        }


        universidadeBuilder.addAllProfessores(professores);


        com.example.Arquivo.Universidade universidade = universidadeBuilder.build();


        //REBENTA-SE AQUIIII!!!!!
        byte[] bytes = universidade.toByteArray();

        //save bytes into a file
        FileOutputStream fos = new FileOutputStream("universidade.bin");
        fos.write(bytes);
        fos.flush();
        fos.close();


        System.out.println(Arrays.toString(bytes));

    }
}