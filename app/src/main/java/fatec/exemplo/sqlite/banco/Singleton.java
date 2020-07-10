package fatec.exemplo.sqlite.banco;


public class Singleton {

    private static Singleton uniqueInstance;
    private static String idUsuario = "";

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

    public void setUsuario(String idUsuario) {
        Singleton.idUsuario = idUsuario;
    }

    public void setUsuario(int idUsuario) {
        Singleton.idUsuario = String.valueOf(idUsuario);
    }

    public String getUsuario() {
        return Singleton.idUsuario;
    }


}