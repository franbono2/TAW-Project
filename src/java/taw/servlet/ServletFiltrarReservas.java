/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import taw.dao.InscripcionFacade;
import taw.dao.UsuarioFacade;
import taw.entity.Inscripcion;
import taw.entity.Usuario;

/**
 *
 * @author migue
 */
@WebServlet(name = "ServletFiltrarReservas", urlPatterns = {"/ServletFiltrarReservas"})
public class ServletFiltrarReservas extends HttpServlet {

    
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
            throws ServletException, IOException, ParseException {
        
        Usuario usuario = this.usuarioFacade.find(request.getSession().getAttribute("idUsuario"));
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String strFechaFiltro = request.getParameter("fechaFiltro");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strFechaFiltro);
        List<Inscripcion> listaFiltrada = this.inscripcionFacade.inscripcionesFiltrarFecha(date, usuario);
        request.setAttribute("listaFiltrada", listaFiltrada);
        
        RequestDispatcher rd = request.getRequestDispatcher("listadoInscripciones.jsp");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletFiltrarReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ServletFiltrarReservas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
