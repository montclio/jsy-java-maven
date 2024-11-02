package model.vo;

public class PecaVO {
    private int idPeca;
    private String nomePeca;
    private String precoPeca;
    private String tipoPeca;
    private String modelo;
    private String fabricantePeca;

    // Construtor com todos os atributos
    public PecaVO(int idPeca, String nomePeca, String precoPeca, String tipoPeca, String modelo, String fabricantePeca) {
        this.idPeca = idPeca;
        this.nomePeca = nomePeca;
        this.precoPeca = precoPeca;
        this.tipoPeca = tipoPeca;
        this.modelo = modelo;
        this.fabricantePeca = fabricantePeca;
    }

    // Getters e Setters
    public int getIdPeca() {
        return idPeca;
    }

    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    public String getNomePeca() {
        return nomePeca;
    }

    public void setNomePeca(String nomePeca) {
        this.nomePeca = nomePeca;
    }

    public String getPrecoPeca() {
        return precoPeca;
    }

    public void setPrecoPeca(String precoPeca) {
        this.precoPeca = precoPeca;
    }

    public String getTipoPeca() {
        return tipoPeca;
    }

    public void setTipoPeca(String tipoPeca) {
        this.tipoPeca = tipoPeca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricantePeca() {
        return fabricantePeca;
    }

    public void setFabricantePeca(String fabricantePeca) {
        this.fabricantePeca = fabricantePeca;
    }
}
