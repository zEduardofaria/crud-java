package faculdade.api;

import faculdade.bo.BOAluno;
import faculdade.bo.BOUsuario;
import faculdade.to.TOAluno;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("aluno")
public class ServiceAluno {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOAluno> lista() throws Exception {
        return BOAluno.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOAluno model) throws Exception {
        TOAluno aluno = BOAluno.inserir(model);

        if (aluno != null) {
            JSONObject json = new JSONObject();
            json.put("id", aluno.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOAluno model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOAluno.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE
    @Consumes("application/json;charset=utf-8")
    public void deletar(int id) throws Exception {
        BOAluno.deletar(id);
    }
}
