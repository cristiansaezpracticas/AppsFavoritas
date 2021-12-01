/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Apps;
import modelo.Crud;

/**
 *
 * @author Cristian
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {
    
    final int NUM_LINEAS_PAGINA = 5;
    int pagina=1;
    int offset=0;
    int num_paginas=0;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        /* TODO output your page here. You may use following sample code. */
        String op = "listar";
        if (request.getParameter("op")!=null ){
            op=request.getParameter("op");
        }
            
        if ( op.equals("insertar") ) {
            request.setAttribute("operacion", "insertardatos");
            request.setAttribute("mensaje", "");
            request.getRequestDispatcher("actualizar.jsp").forward(request,response);
        }
        
        if ( op.equals("listar") ) {
            listar(request, response);
        }

        /******************************************/
        /*    BORRAR                              */
        /******************************************/
        if ( op.equals("borrar") ) {
            int id=Integer.parseInt(request.getParameter("id")) ;
            if ( Crud.destroyApp(id)>0) {
                request.setAttribute("mensaje", "App con id" + id + " borrada");
            } else {
                request.setAttribute("mensaje", "No se ha borrado ningúna app");
            }
            this.listar(request, response);
        }

        /******************************************/
        /*    ACTUALIZAR                           */
        /******************************************/    
        if ( op.equals("actualizar") ) {
            int id=Integer.parseInt(request.getParameter("id")) ;
            Apps miApp = Crud.getApp(id);
            request.setAttribute("operacion", "actualizardatos");
            request.setAttribute("app", miApp);
            request.getRequestDispatcher("actualizar.jsp").forward(request,response);
        }
        
        /******************************************/
        /*    ACTUALIZAR DATOS                      */
        /******************************************/ 
        if ( op.equals("actualizardatos") ) {
            int id=Integer.parseInt(request.getParameter("id")) ;
            String nombre=request.getParameter("nombre");
            String categoria=request.getParameter("categoria");
            String plataforma=request.getParameter("plataforma");

            Apps miApp=new Apps(id,nombre,categoria,plataforma);
    
            if ( Crud.actualizaApp(miApp)>0) {
                request.setAttribute("mensaje", "App con id" + id + "Actualizado");
            } else {
                request.setAttribute("mensaje", "No se ha podido actualizar la app");
            }
            
            request.setAttribute("app", miApp);
            request.getRequestDispatcher("actualizar.jsp").forward(request,response);             

        }
        
        /******************************************/
        /*    INSERTAR DATOS                      */
        /******************************************/ 
        if ( op.equals("insertardatos") ) {               
            String nombre=request.getParameter("nombre");
            String categoria=request.getParameter("categoria");
            String plataforma=request.getParameter("plataforma");
            

            Apps miApp = new Apps();
            miApp.setNombre(nombre);
            miApp.setCategoria(categoria);
            miApp.setPlataforma(plataforma);

            Crud.insertaApp(miApp);
            this.listar(request, response);

        }
        
    }
    
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                     
        List<Apps> listaApps=Crud.getApps();
        /* cálculos para la paginación */

        if ( request.getParameter("pagina")!=null){
            pagina = Integer.parseInt(request.getParameter("pagina"));
            offset = ( pagina-1 ) * NUM_LINEAS_PAGINA;
        }
        num_paginas = ( int ) Math.ceil(listaApps.size() / ( double ) NUM_LINEAS_PAGINA);
        listaApps = Crud.getAppsPaginado(offset, NUM_LINEAS_PAGINA);

        request.setAttribute("listado", listaApps);
        request.setAttribute("pagina", pagina);
        request.setAttribute("num_paginas", String.valueOf(num_paginas));

        request.setAttribute("mensaje", "");
        request.getRequestDispatcher("listar.jsp").forward(request,response);
        
    }
        
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
