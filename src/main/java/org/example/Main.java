package org.example;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.example.utils.DataProvider;
import org.example.utils.DataService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        SessionFactory factory = DataProvider.getSessionFactory();
        DataService dataService = new DataService(factory);

        /**
         * Historia de usuario 1
         */
        Pelicula newpelicula = new Pelicula();
        newpelicula.setTitulo("Pelicula1");
        dataService.savePelicula(newpelicula);

        /**
         * Historia de usuario 2
         */
        String user="user1@example.com";
        List<Opinion> opiniones = dataService.findOpinionByEmail(user);
        System.out.println("Opiniones del usuario: " + user);
        for (Opinion opinion : opiniones) {
            System.out.println("------------------------------------------------------");
            System.out.println("Pelicula:" + opinion.getPelicula().getTitulo());
            System.out.println("Descripcion: " + opinion.getDescripcion());
            System.out.println("Puntuacion: " + opinion.getPuntuacion());
        }

        /**
         * Historia de usuario 3
         */
        Opinion newOpinion = new Opinion();
        newOpinion.setPelicula(dataService.findPeliculaById(8L).get());
        newOpinion.setDescripcion("Opinion1");
        newOpinion.setUsuario("Juan");
        newOpinion.setPuntuacion(10);
        dataService.saveOpinion(newOpinion);

        /**
         * Historia de usuario 4
         */
        int puntMenorOIgualQue=3;
        List<Opinion> puntMenorList = dataService.findOpinionByPuntuacionMenorQue(puntMenorOIgualQue);

        System.out.println("Opiniones con puntuacion menor o igual que " + puntMenorOIgualQue + ":");
        for (Opinion opinion1 : puntMenorList) {
            System.out.println("------------------------------------------------------");
            System.out.println("Pelicula:" + opinion1.getPelicula().getTitulo());
            System.out.println("Descripcion: " + opinion1.getDescripcion());
            System.out.println("Puntuacion: " + opinion1.getPuntuacion());
        }

    }
}
