package com.example;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static boolean imprime = false;

    public static Arquivo.Universidade Criacao(int num){

        com.example.Arquivo.Universidade.Builder universidadeBuilder = com.example.Arquivo.Universidade.newBuilder();
        universidadeBuilder.setNome("Universidade de Coimbra");
        universidadeBuilder.setAnoInstituicao(1754);
        ArrayList<Arquivo.Professor> professores = new ArrayList<>();




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


        return universidadeBuilder.build();

    }

    public static void UnMarshalling(String file)  {

        try (FileInputStream fis = new FileInputStream(file)) {
            long tempoInicial, tempoFinal, tempoDecorrido;
            tempoInicial = System.currentTimeMillis();
            Arquivo.Universidade universidade = Arquivo.Universidade.parseFrom(fis);
            tempoFinal = System.currentTimeMillis();
            tempoDecorrido = tempoFinal - tempoInicial;
            System.out.println("Tempo (UnMarshalling Protocol) em milissegundos:" + tempoDecorrido);

            if(imprime) {
                System.out.println("Universidade: " + universidade.getNome() + " " + universidade.getAnoInstituicao());
                for (Arquivo.Professor professor : universidade.getProfessoresList()) {
                    System.out.println("Professor: " + professor.getNome() + "  " + professor.getIdade() + "  " + professor.getAnoDoutorado());
                    for (Arquivo.Aluno aluno : professor.getAlunosList()) {
                        System.out.println("Aluno Nome: " + aluno.getNome() + "  " + aluno.getIdade() + "  " + +aluno.getIdade());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void MarshallingProtocol(Arquivo.Universidade universidade,String File) throws IOException {
        long tempoInicial, tempoFinal, tempoDecorrido;
        tempoInicial = System.currentTimeMillis();
        byte[] bytes = universidade.toByteArray();
        tempoFinal = System.currentTimeMillis();
        tempoDecorrido = tempoFinal - tempoInicial;
        System.out.println("Tempo (Marshalling Protocol) em milissegundos:" + tempoDecorrido);
        //save bytes into a file
        FileOutputStream fos = new FileOutputStream(File);
        fos.write(bytes);
        fos.flush();
        fos.close();

    }


    public static void main(String[] args) throws  IOException {

        int num = 10000;
        Arquivo.Universidade universidade = Criacao(num);
        System.out.printf("TESTE COM %d professores!\n",num);
        MarshallingProtocol(universidade,"universidade.bin");

        UnMarshalling("universidade.bin");





    }
}