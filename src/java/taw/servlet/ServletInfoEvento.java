/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.dao.EventoFacade;
import taw.dao.InscripcionFacade;
import taw.dao.UsuarioFacade;
import taw.entity.Evento;
import taw.entity.Inscripcion;
import taw.entity.Usuario;

/**
 *
 * @author migue
 */
@WebServlet(name = "ServletInfoEvento", urlPatterns = {"/ServletInfoEvento"})
public class ServletInfoEvento extends HttpServlet {

    @EJB
    public EventoFacade eventoFacade;
    @EJB
    public InscripcionFacade inscripcionFacade;
    @EJB
    public UsuarioFacade usuarioFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String strIdEvento = request.getParameter("id");
       if(strIdEvento != null){ //Primera llamada desde MostrarEventos
            Evento evento = this.eventoFacade.find(Integer.parseInt(strIdEvento));
            HttpSession sesion = request.getSession();
            Usuario usuario = this.usuarioFacade.find(sesion.getAttribute("idUsuario"));
            List<Inscripcion> listaInscripcionesUsuario = this.inscripcionFacade.usuarioInscritoEn(evento, usuario);
            if(listaInscripcionesUsuario == null || listaInscripcionesUsuario.isEmpty()){
                listaInscripcionesUsuario = new ArrayList();
                boolean inscrito = false;
                request.setAttribute("inscrito", inscrito);
            }else{
                boolean inscrito = true;
                request.setAttribute("inscrito", inscrito);
            }
            List<Inscripcion> listaInscripciones = this.inscripcionFacade.inscripcionesEvento(evento);
            if(listaInscripciones == null || listaInscripciones.isEmpty()){
                listaInscripciones = new ArrayList();
            }
            request.setAttribute("evento", evento);
            request.setAttribute("listaInscripcionesUsuario", listaInscripcionesUsuario);
            request.setAttribute("listaInscripciones", listaInscripciones);
       }else{
          // Evento evento = (Evento)request.getAttribute("evento");
           
       }
       
       //response.sendRedirect("infoEvento.jsp");
       RequestDispatcher rd = request.getRequestDispatcher("infoEvento.jsp");
       rd.forward(request, response);     
       
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
