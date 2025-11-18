package ort.da.mvc.Peajes.login;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ort.da.mvc.Fachada;
import ort.da.mvc.Peajes.Usuarios.Administrador.Administrador;
import ort.da.mvc.Peajes.Usuarios.Propietario.Propietario;
import ort.da.mvc.Peajes.Utils.Respuesta;
import ort.da.mvc.Peajes.Utils.Exceptions.UsuarioException;

@RestController
@Scope("session")
@RequestMapping("/LoginController")
public class LoginController {

    // #region propietario

    @PostMapping("/Propietariologin")
    public List<Respuesta> Propietariologin(HttpSession sesionHttp, @RequestParam int ci,
            @RequestParam String contrasenia) throws UsuarioException {

        if (sesionHttp.getAttribute("propietario") != null) {
            return Respuesta.lista(new Respuesta("usuarioAutenticado", "TableroDeControlPropietario.html"));
        }

        try {
            // Si hay un usuario previo logueado, remover su atributo
            if (sesionHttp.getAttribute("propietario") != null) {
                PropietarioLogout(sesionHttp);
            }
            Propietario usuarioLogueado = Fachada.getInstancia().loginPropietario(ci, contrasenia);
            sesionHttp.setAttribute("propietario", usuarioLogueado);

        } catch (UsuarioException e) {
            System.out.println("Error en login propietario: " + e.getMessage());
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }

        System.out.println("///usuario logueado: " + sesionHttp.getAttribute("propietario"));

        return Respuesta.lista(new Respuesta("loginExitoso", "TableroDeControlPropietario.html"));

    }

    @PostMapping("/PropietarioLogout")
    public List<Respuesta> PropietarioLogout(HttpSession sesionHttp) {
        Propietario usuario = (Propietario) sesionHttp.getAttribute("propietario");
        if (usuario != null) {
            sesionHttp.removeAttribute("propietario");
            sesionHttp.invalidate();
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "index.html"));
    }

    // #endregion

    // #region administrador

    @PostMapping("/loginAdministrador")
    public List<Respuesta> loginAdministrador(HttpSession sesionHttp, @RequestParam int ci,
            @RequestParam String contrasenia) throws UsuarioException {

        if (sesionHttp.getAttribute("administrador") != null) {
            return Respuesta.lista(new Respuesta("adminYaLogueado", "menuAdministrador.html"));
        }

        try {
            if (sesionHttp.getAttribute("administrador") != null) {
                AdministradorLogout(sesionHttp);
            }
            Administrador usuarioLogueado = Fachada.getInstancia().loginAdministrador(ci, contrasenia);
            sesionHttp.setAttribute("administrador", usuarioLogueado);
        } catch (UsuarioException e) {
            System.out.println("Error en login administrador: " + e.getMessage());
            return Respuesta.lista(new Respuesta("error", e.getMessage()));

        }
        System.out.println("/// usuario logueado: " + sesionHttp.getAttribute("administrador"));

        return Respuesta.lista(new Respuesta("loginExitoso", "menuAdministrador.html"));

    }

    @PostMapping("/AdministradorLogout")
    public List<Respuesta> AdministradorLogout(HttpSession sesionHttp) {
        Administrador usuario = (Administrador) sesionHttp.getAttribute("administrador");
        if (usuario != null) {
            sesionHttp.removeAttribute("administrador");
            sesionHttp.invalidate();
        }
        return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "loginAdministrador.html"));
    }

    // #endregion

}
