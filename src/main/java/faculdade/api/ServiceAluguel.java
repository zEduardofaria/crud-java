package faculdade.api;

import faculdade.bo.BOAluguel;
import faculdade.bo.BOUsuario;
import faculdade.to.TOAluguel;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("aluguel")
public class ServiceAluguel {

    @Context
    protected HttpServletResponse response;
    @Context
    protected HttpServletRequest request;

    @GET
    @Produces("application/json;charset=utf-8")
    public List<TOAluguel> lista() throws Exception {
        return BOAluguel.lista();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public String inserir(TOAluguel model) throws Exception {
        TOAluguel aluguel = BOAluguel.inserir(model);

        if (aluguel != null) {
            JSONObject json = new JSONObject();
            json.put("id", aluguel.getId());
            return json.toString();
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PUT
    @Consumes("application/json;charset=utf-8")
    public void atualizar(@HeaderParam("token") String token, TOAluguel model) throws Exception {
        if (BOUsuario.isValid(token)) {
            BOAluguel.atualizar(model);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @DELETE    
    @Consumes("application/json;charset=utf-8")
    @Produces("application/json;charset=utf-8")
    public void deletar(String id) throws Exception {
        BOAluguel.deletar(id);
    }
}
