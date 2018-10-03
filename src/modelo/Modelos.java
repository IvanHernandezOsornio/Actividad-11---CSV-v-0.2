
package modelo;


public class Modelos {
    
    private String path = "C:\\Users\\Familia Hernández\\Desktop\\base.csv"; // Ruta del archivo

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    //Variable (extra) para mandar mensaje a pantalla 
    private String message = ""; 

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    private int position = 0; //Almacenamos la posisicion de cada registro 

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    
}
