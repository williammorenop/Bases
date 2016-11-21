package GUI;

import controller.GrupoJpaController;
import controller.UsuarioJpaController;
import entities.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author willi
 */
public class NewClass {

    public static UsuarioJpaController controlador;

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("finalPU");
        UsuarioJpaController controlador = new UsuarioJpaController(emf);
        /*
        String nickname = null,Password = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Ya tenemos el "lector"

        mostrarTabla(controlador);

        System.err.print("INGRSE NICK: ");
        nickname = br.readLine();
        System.err.print("INGRSE Clave: ");
        Password=br.readLine();
        System.out.println(Login(nickname, Password, controlador));*/
        
        EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("finalPU");
        GrupoJpaController controlador1 = new GrupoJpaController(emf1);
        
        String j="pero";
        List<String> listo ;
        listo=controlador1.elregresodelabestia(j);
        
        for (String k : listo)
        {
            System.out.println(k+"\n");
        }
        
        
        
    }
    
    public static String Login(String nickname,String Password,UsuarioJpaController controlador)
    {
        String retorno="Error en la contraseña o en el nombre de usuario.\n\t\t Intente de nuevo\n";
        List<Usuario> findUsuarioEntities;
    findUsuarioEntities = controlador.findUsuarioEntities();

     for (Usuario usuario : findUsuarioEntities) {
           // System.out.println(usuario.getNombre().trim()+"--->");
            if(usuario.getNombre().trim().compareTo(nickname.trim())==0)
            {
                //System.out.println("aaaa1");
                if(usuario.getUsuarioPaypal().compareTo(Password)==0)               //CAMBIAR PoR CLAVE
                {
                    //System.out.println("aaaa2");
                    return "EXITO!!";
                }
            }
         }
     return retorno;
    }

    private static void mostrarTabla(UsuarioJpaController controlador) {
       
        List<Usuario> findUsuarioEntities;
    findUsuarioEntities = controlador.findUsuarioEntities();
        for (Usuario usuario : findUsuarioEntities) {
            System.out.print(usuario.getNickName());
           // System.out.print("\t"+usuario.getUsuarioPaypal()+"\n");
        }
           
    }
    
}
