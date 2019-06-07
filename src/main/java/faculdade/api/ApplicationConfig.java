package faculdade.api;


import javax.ws.rs.core.Application;
import java.util.Set;

@javax.ws.rs.ApplicationPath("faculdade")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {

        resources.add(ServiceAluguel.class);
        resources.add(ServiceAluno.class);
        resources.add(ServiceDisciplina.class);
        resources.add(ServiceLivro.class);
        resources.add(ServiceMateria.class);
        resources.add(ServiceProfessor.class);
        resources.add(ServiceTurma.class);
        resources.add(ServiceUsuario.class);
        resources.add(ServiceVersion.class);

    }

}