package org.example.utils;

import org.example.models.Opinion;
import org.example.models.Pelicula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class DataService {

    SessionFactory sessionFactory;

    public DataService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Opinion saveOpinion(Opinion entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    public List<Opinion> findOpinionByEmail(String usuario) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Opinion where usuario = :usuario", Opinion.class)
                    .setParameter("usuario", usuario)
                    .list();
        }
    }

    public List<Opinion> findOpinionByPuntuacionMenorQue(int puntuacion){
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Opinion where puntuacion <=: puntuacion", Opinion.class)
                    .setParameter("puntuacion", puntuacion)
                    .list();
        }
    }

    public Pelicula savePelicula(Pelicula entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    public Optional<Pelicula> findPeliculaById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Pelicula.class, id));
        }
    }
}
