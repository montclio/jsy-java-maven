package model.vo;

public class AutopecasVO {
    private int idAutopeca;
    private String nomeFantasia;
    private int cnpj;
    private String endereco;
    private int telefone;
    private String email;
    private String senha;
    private float frete;

    
    
    
    public AutopecasVO(int idAutopeca, String nomeFantasia, int cnpj, String endereco, int telefone, String email,
			String senha, float frete) {
		this.idAutopeca = idAutopeca;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.frete = frete;
	}

	// Getters e Setters
    public int getIdAutopeca() {
        return idAutopeca;
    }

    public void setIdAutopeca(int idAutopeca) {
        this.idAutopeca = idAutopeca;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public float getFrete() {
        return frete;
    }

    public void setFrete(float frete) {
        this.frete = frete;
    }
}
