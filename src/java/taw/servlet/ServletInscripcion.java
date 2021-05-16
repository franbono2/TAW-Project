/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.entity.Evento;

/**
 *
 * @author migue
 */
@WebServlet(name = "ServletInscripcion", urlPatterns = {"/ServletInscripcion"})
public class ServletInscripcion extends HttpServlet {

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
        List<String> listaAsientosSeleccionados = (List)request.getAttribute("listaAsientosSeleccionados");
        if(listaAsientosSeleccionados == null){
            listaAsientosSeleccionados = new ArrayList<>();
        }
        String strAsiento= request.getParameter("asientoLibreSeleccionado");
        
        String strAsientoYaSeleccionado = request.getParameter("asientoSeleccionado");
        
        
        
        //String[] asiento = strAsiento.split("-");
        //int f = Integer.parseInt(asiento[0]);
        //int c = Integer.parseInt(asiento[1]);
        listaAsientosSeleccionados.add(strAsiento);
        
        String cadena = request.getParameter("asientosSeleccionados");
        if(cadena != ""){
             String[] aux = cadena.split(",");
            for(String s : aux){
                //String[] aux2 = s.split("-");
                //int fila = Integer.parseInt(aux2[0]);
                //int columna = Integer.parseInt(aux2[1]);
                listaAsientosSeleccionados.add(s);
            }
        }
        listaAsientosSeleccionados.remove(strAsientoYaSeleccionado);
        request.setAttribute("listaAsientosSeleccionados", listaAsientosSeleccionados);
        String strIdEvento = request.getParameter("idEvento");
        ////////////////
        //response.sendRedirect("infoEvento.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("ServletInfoEvento?id=" + strIdEvento);
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
