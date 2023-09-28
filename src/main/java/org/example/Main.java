package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Main {
    public static Universidade Criacao(int num){
        Universidade uni = new Universidade();
        uni.setNome("Universidade de Coimbra");
        uni.setAnoInstituicao(1754);
        ArrayList<Professor> profs = new ArrayList<>();
        for(int i = 0; i < num;i++){
            Professor prof = new Professor();
            prof.setNome("prof"+i);
            prof.setIdade(60);
            ArrayList<Aluno> alunos = new ArrayList<>();
            for(int j = 0; j < 20; j++){
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
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Universidade a = Criacao(1000);
        Aluno b = new Aluno("sad",12,12);

        JAXBContext contextObj = JAXBContext.newInstance(Universidade.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        FileOutputStream s = new FileOutputStream("universidade.xml");
        marshallerObj.marshal(a,s);




    }
}