package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.*;


public class Main {
    public static boolean imprime = false;
    public static Universidade Criacao(int num){
        Universidade uni = new Universidade();
        uni.setNome("Universidade de Coimbra");
        uni.setAnoInstituicao(1754);
        ArrayList<Professor> profs = new ArrayList<>();
        for(int i = 1; i <= num;i++){
            Professor prof = new Professor();
            prof.setNome("prof"+i);
            prof.setIdade(60);
            ArrayList<Aluno> alunos = new ArrayList<>();
            for(int j = 1; j <= 20; j++){
                Aluno al = new Aluno();
                al.setIdade(18);
                al.setNome("Aluno"+j);
                al.setAvaliacao(20);
                alunos.add(al);
            }
            prof.setAlunos(alunos);
            prof.setAnoDoutorado(1909);
            profs.add(prof);
        }
        uni.setProfessores(profs);
        return uni;
    }
    public static void MarshallingXML(String file, Universidade uni) throws JAXBException, FileNotFoundException {

        long tempoInicial, tempoFinal, tempoDecorrido;
        tempoInicial = System.currentTimeMillis();
        JAXBContext contextObj = JAXBContext.newInstance(Universidade.class);
        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        FileOutputStream s = new FileOutputStream(file);
        marshallerObj.marshal(uni,s);
        tempoFinal = System.currentTimeMillis();
        tempoDecorrido = tempoFinal - tempoInicial;
        System.out.println("Tempo (Marshalling XML) em milissegundos:" + tempoDecorrido);

    }


    public static void UnMarshallingXML(String filename) throws JAXBException, FileNotFoundException {

        File file = new File(filename);

        long tempoInicial, tempoFinal, tempoDecorrido;
        tempoInicial = System.currentTimeMillis();

        JAXBContext jaxbContext = JAXBContext.newInstance(Universidade.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Universidade e=(Universidade) jaxbUnmarshaller.unmarshal(file);
        tempoFinal = System.currentTimeMillis();
        tempoDecorrido = tempoFinal - tempoInicial;
        System.out.println("Tempo (UnMarshalling XML) em milissegundos:" + tempoDecorrido);


        if(imprime) {
            System.out.println(e.getNome() + "  " + e.getAnoInstituicao());
            ArrayList<Professor> professors = e.getProfessores();
            for (int i = 0; i < professors.size(); i++) {
                System.out.println(professors.get(i).getNome() + "  " + professors.get(i).getIdade() + "  " + professors.get(i).getAnoDoutorado());
                ArrayList<Aluno> alun = professors.get(i).getAlunos();
                for (int k = 0; k < alun.size(); k++) {
                    System.out.println(alun.get(k).getNome() + "  " + alun.get(k).getIdade() + "  " + alun.get(k).getAvaliacao());
                }
            }
        }

    }

    public static void MarshallingJSON(String file, Universidade uni) throws IOException {
        long tempoInicial, tempoFinal, tempoDecorrido;
        tempoInicial = System.currentTimeMillis();
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        String j = g.toJson(uni);
        tempoFinal = System.currentTimeMillis();
        tempoDecorrido = tempoFinal - tempoInicial;
        System.out.println("Tempo (Marshalling JSON) em milissegundos:" + tempoDecorrido);
        //medir aqui o tempo
      //  System.out.println(j);
        FileWriter w = new FileWriter(new File(file));
        w.write(j);
        w.flush();
    }

    public static void UnMarshallingJson(String filename) throws IOException {



        FileReader reader = new FileReader(filename);
        long tempoInicial, tempoFinal, tempoDecorrido;
        tempoInicial = System.currentTimeMillis();
        Gson gson = new Gson();
        Universidade e = gson.fromJson(reader, Universidade.class);
        tempoFinal = System.currentTimeMillis();
        tempoDecorrido = tempoFinal - tempoInicial;
        System.out.println("Tempo (UnMarshalling JSON) em milissegundos:" + tempoDecorrido);

        if (imprime) {
            System.out.println(e.getNome() + "  " + e.getAnoInstituicao());
            ArrayList<Professor> professors = e.getProfessores();
            for (int i = 0; i < professors.size(); i++) {
                System.out.println(professors.get(i).getNome() + "  " + professors.get(i).getIdade() + "  " + professors.get(i).getAnoDoutorado());
                ArrayList<Aluno> alun = professors.get(i).getAlunos();
                for (int k = 0; k < alun.size(); k++) {
                    System.out.println(alun.get(k).getNome() + "  " + alun.get(k).getIdade() + "  " + alun.get(k).getAvaliacao());
                }
            }
        }


    }


    public static void main(String[] args) throws JAXBException, IOException {
        int num = 10000;
        Universidade a = Criacao(num);
        String FileNameXML = "universidade.xml";
        String FileNameJSON = "universidade.json";

        System.out.printf("TESTE COM %d professores!\n",num);
        MarshallingXML(FileNameXML,a);
        MarshallingJSON(FileNameJSON,a);
        UnMarshallingXML(FileNameXML);
        UnMarshallingJson(FileNameJSON);





    }
}