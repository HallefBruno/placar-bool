package placarbool;

public class Placar {

    private String status;
    private String time01;
    private String separador;
    private String time02;

    public Placar() {
    }
    
    public Placar(String status, String time01, String separador, String time02) {
        this.status = status;
        this.time01 = time01;
        this.separador = separador;
        this.time02 = time02;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime01() {
        return time01;
    }

    public void setTime01(String time01) {
        this.time01 = time01;
    }

    public String getSeparador() {
        return separador;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public String getTime02() {
        return time02;
    }

    public void setTime02(String time02) {
        this.time02 = time02;
    }

}
