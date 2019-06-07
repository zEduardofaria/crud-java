package faculdade.api;

import faculdade.bo.BOProfessor;
import faculdade.bo.BOUsuario;
import faculdade.to.TOProfessor;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("professor")
public class ServiceProfessor {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOProfessor> lista() throws Exception {
        return BOProfessor.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOProfessor model) throws Exception {
        TOProfessor professor = BOProfessor.inserir(model);

        if (professor != null) {
            JSONObject json = new JSONObject();
            json.put("id", professor.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOProfessor model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOProfessor.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(int id) throws Exception {
        BOProfessor.deletar(id);
    }
}
