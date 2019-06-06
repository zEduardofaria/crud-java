package faculdade.api;

import faculdade.bo.BOUsuario;
import faculdade.fw.Cache;
import faculdade.to.TOUsuario;
import com.sun.corba.se.impl.oa.toa.TOA;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("usuario")
public class ServiceUsuario {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @POST
    @Path("autenticar")
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public TOUsuario autenticar(TOUsuario model) throws Exception {

        TOUsuario usuario = BOUsuario.autenticar(model);

        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        return usuario;
    }

    @POST
    @Path("esqueciMinhaSenha")
    @Consumes("application/json;charset=utf-8")
    public void esqueciMinhaSenha(TOUsuario model) throws Exception {
        TOUsuario usuario = BOUsuario.esqueciMinhaSenha(model);
        if (usuario == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOUsuario model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOUsuario.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }


    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOUsuario model) throws Exception {
        TOUsuario usuario = BOUsuario.inserir(model);
        if (usuario != null) {
            JSONObject j = new JSONObject();
            j.put("id", usuario.getId());
            return j.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }


    @GET
    @Path("meuUsuario")
    @Produces("application/json;charset=utf-8")
    public TOUsuario meuUsuario(@HeaderParam("token") String token) throws Exception {

        if (BOUsuario.isValid(token)) {
            return BOUsuario.meuUsuario(token);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }

    }

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOUsuario> lista() throws Exception {
        return BOUsuario.lista();
    }


}
