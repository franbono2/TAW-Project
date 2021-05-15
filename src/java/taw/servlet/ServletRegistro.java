/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import taw.dao.RolFacade;
import taw.entity.Usuario;
import taw.dao.UsuarioFacade;

/**
 *
 * @author Francisco Bono
 */
@WebServlet(name = "ServletRegistro", urlPatterns = {"/ServletRegistro"})
public class ServletRegistro extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private RolFacade rolFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String contrasenia = request.getParameter("contrasenia");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String domicilio = request.getParameter("domicilio");
        String ciudad = request.getParameter("ciudad");
        String sexo = request.getParameter("genero");
        String strError = "";
        String strTo = "";
        HttpSession session = request.getSession();
        if(comprobarCampos(email, contrasenia, nombre, apellidos, domicilio, ciudad, sexo)){
            if(!existeUsuario(email)){
                crearUsuario(email, contrasenia, nombre, apellidos, domicilio, ciudad, sexo);
                strError = "Se ha creado el usuario";
                request.setAttribute("error", strError);
                HttpSession sesion= request.getSession();         
                sesion.setAttribute("idUsuario",this.usuarioFacade.findByEmail(email).getId() );
                strTo = "ServletMostrarEventos";
            }else{
                strError = "El email introducido ya existe";
                request.setAttribute("error", strError);
                strTo = "registro.jsp";
            }
        }else{
            strError = "Error al introducir los campos";
            request.setAttribute("error", strError);
            strTo = "registro.jsp";
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(strTo);
        rd.forward(request, response);       
    }
    
    private boolean comprobarCampos(String email, String contrasenia, String nombre, String apellidos, String domicilio, String ciudad, String sexo){
        boolean esCorrecto = true;
        if (email == null || email.isEmpty() || contrasenia == null || contrasenia.isEmpty() || nombre == null || nombre.isEmpty() || apellidos == null || apellidos.isEmpty() || domicilio == null || domicilio.isEmpty() || ciudad == null || ciudad.isEmpty() || sexo == null || sexo.isEmpty() ){
            esCorrecto = false;
        }
        return esCorrecto;
    }
    
    private boolean existeUsuario(String email){
        boolean existe = false;
        Usuario usuario;
        usuario = this.usuarioFacade.findByEmail(email);
        if (usuario != null){
            existe = true;
        }
        return existe;
    }
    
    private void crearUsuario(String email, String contrasenia, String nombre, String apellidos, String domicilio, String ciudad, String sexo){
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setContraseña(contrasenia);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setDomicilio(domicilio);
        usuario.setCiudad(ciudad);
        usuario.setSexo(sexo);
        usuario.setRol(rolFacade.findByName("USUARIO"));
        this.usuarioFacade.create(usuario);
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
