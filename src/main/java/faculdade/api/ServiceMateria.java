package faculdade.api;

import faculdade.bo.BOMateria;
import faculdade.bo.BOUsuario;
import faculdade.to.TOMateria;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("materia")
public class ServiceMateria {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOMateria> lista() throws Exception {
        return BOMateria.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOMateria model) throws Exception {
        TOMateria materia = BOMateria.inserir(model);

        if (materia != null) {
            JSONObject json = new JSONObject();
            json.put("id", materia.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOMateria model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOMateria.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(String id) throws Exception {
        BOMateria.deletar(id);
    }
}
