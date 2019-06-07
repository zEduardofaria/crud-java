package faculdade.api;

import faculdade.bo.BODisciplina;
import faculdade.bo.BOUsuario;
import faculdade.to.TODisciplina;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("disciplina")
public class ServiceDisciplina {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TODisciplina> lista() throws Exception {
        return BODisciplina.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TODisciplina model) throws Exception {
        TODisciplina disciplina = BODisciplina.inserir(model);

        if (disciplina != null) {
            JSONObject json = new JSONObject();
            json.put("id", disciplina.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TODisciplina model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BODisciplina.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(int id) throws Exception {
        BODisciplina.deletar(id);
    }
}
