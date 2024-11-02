package model.vo;

public class OficinaVO {
	private int idOficina;
	private String nomeFantasia;
	private int telefone;
	private String endereco;
	private int cnpj;
	private String email;
	private String senha;
	private float precoMaoObra;
	
	
	
	
	public OficinaVO(int idOficina, String nomeFantasia, int telefone, String endereco, int cnpj, String email,
			String senha, float precoMaoObra) {
		this.idOficina = idOficina;
		this.nomeFantasia = nomeFantasia;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cnpj = cnpj;
		this.email = email;
		this.senha = senha;
		this.precoMaoObra = precoMaoObra;
	}
	
	
	public int getIdOficina() {
		return idOficina;
	}
	public void setIdOficina(int idOficina) {
		this.idOficina = idOficina;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getCnpj() {
		return cnpj;
	}
	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
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
	public float getPrecoMaoObra() {
		return precoMaoObra;
	}
	public void setPrecoMaoObra(float precoMaoObra) {
		this.precoMaoObra = precoMaoObra;
	}
	


}
