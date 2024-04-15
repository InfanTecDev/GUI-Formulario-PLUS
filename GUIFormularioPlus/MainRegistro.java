import MiLibreria.Login;
import MiLibreria.PostLogin;
/**
 *
 * @author Diego Infante
 */
public class MainRegistro {

    public static void main(String[] args) {
        Login login = new Login(new PostLogin() {
            @Override
            public void ejecutar() {
                new Registro().ejecutar();
            }
        });
        login.start();
    }
    
}
