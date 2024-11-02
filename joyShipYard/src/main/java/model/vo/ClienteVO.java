package model.vo;

import java.sql.Date;

public class ClienteVO {
    private int id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String sexo;
    private int cep;
    private String endereco;
    private long cpf;
    private long cnh;
    private long celular;
    private String email;
    private String senha;

    // Construtor 
    public ClienteVO(int id, String nome, String sobrenome, Date dataNascimento, String sexo, int cep, String endereco, long cpf, long cnh, long celular, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cep = cep;
        this.endereco = endereco;
        this.cpf = cpf;
        this.cnh = cnh;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public int getCep() {
    	return cep;
    }
    
    public void setCep(int cep) {
    	this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCnh() {
        return cnh;
    }

    public void setCnh(long cnh) {
        this.cnh = cnh;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
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
}
