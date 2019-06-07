package faculdade.api;

import faculdade.bo.BOTurma;
import faculdade.bo.BOUsuario;
import faculdade.to.TOTurma;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("turma")
public class ServiceTurma {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOTurma> lista() throws Exception {
        return BOTurma.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOTurma model) throws Exception {
        TOTurma turma = BOTurma.inserir(model);

        if (turma != null) {
            JSONObject json = new JSONObject();
            json.put("id", turma.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOTurma model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOTurma.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(int id) throws Exception {
        BOTurma.deletar(id);
    }
}
