package placarbool;

public class Placar {

    private String status;
    private String primeiroTime;
    private String golsPrimeiroTime;
    private String segundoTime;
    private String golsSeguntoTime;
    private String link;

    public Placar(String status, String primeiroTime, String golsPrimeiroTime, String segundoTime, String golsSeguntoTime, String link) {
        this.status = status;
        this.primeiroTime = primeiroTime;
        this.golsPrimeiroTime = golsPrimeiroTime;
        this.segundoTime = segundoTime;
        this.golsSeguntoTime = golsSeguntoTime;
        this.link = link;
    }

    public Placar() {
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrimeiroTime() {
        return primeiroTime;
    }

    public void setPrimeiroTime(String primeiroTime) {
        this.primeiroTime = primeiroTime;
    }

    public String getGolsPrimeiroTime() {
        return golsPrimeiroTime;
    }

    public void setGolsPrimeiroTime(String golsPrimeiroTime) {
        this.golsPrimeiroTime = golsPrimeiroTime;
    }

    public String getSegundoTime() {
        return segundoTime;
    }

    public void setSegundoTime(String segundoTime) {
        this.segundoTime = segundoTime;
    }

    public String getGolsSeguntoTime() {
        return golsSeguntoTime;
    }

    public void setGolsSeguntoTime(String golsSeguntoTime) {
        this.golsSeguntoTime = golsSeguntoTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    
    
}
