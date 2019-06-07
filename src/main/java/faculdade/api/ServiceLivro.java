package faculdade.api;

import faculdade.bo.BOLivro;
import faculdade.bo.BOUsuario;
import faculdade.to.TOLivro;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("livro")
public class ServiceLivro {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOLivro> lista() throws Exception {
        return BOLivro.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOLivro model) throws Exception {
        TOLivro livro = BOLivro.inserir(model);

        if (livro != null) {
            JSONObject json = new JSONObject();
            json.put("id", livro.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOLivro model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOLivro.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(int id) throws Exception {
        BOLivro.deletar(id);
    }
}
