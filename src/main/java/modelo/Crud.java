/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Cristian
 */
public class Crud {
    
    public static void insertaApp(Apps app) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(app);
        manager.getTransaction().commit();
    }
    
    public static int actualizaApp(Apps miApp) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "UPDATE Apps a SET a.nombre = :nombre, a.categoria = :categoria, a.plataforma = :plataforma WHERE a.id = :id";
        Query q = manager.createQuery(sql,Apps.class);
        q.setParameter("plataforma", miApp.getPlataforma());
        q.setParameter("categoria", miApp.getCategoria());
        q.setParameter("nombre", miApp.getNombre());
        q.setParameter("id", miApp.getId());
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate();
        manager.getTransaction().commit();
        //manager.close();
        return filasAfectadas;      
    }
    
    public static Apps getApp(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT a FROM Apps a WHERE a.id=" + id; //consulta en JPQL 
        Query q = manager.createQuery(sql,Apps.class); //método para consultas en JPQL
        Apps appsBD =  ( Apps )q.getSingleResult();
        return appsBD;
        
    } 
     
    public static List<Apps> getApps() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM apps";
        Query q = manager.createNativeQuery(sql,Apps.class); //método para consultas en SQL
        List<Apps> appsBD =  q.getResultList();

        return appsBD;        
    }
       
    public static List<Apps> getAppsPaginado(int offset, int lineas_pagina) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM apps";
        Query q = manager.createNativeQuery(sql,Apps.class); //método para consultas en SQL
        q.setFirstResult(offset);
        q.setMaxResults(lineas_pagina);
        List<Apps> appsBD =  q.getResultList();

        return appsBD;        
    }
       
    public static int destroyApp(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("unidad");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from Apps a WHERE a.id = " + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;     
    }
    
}