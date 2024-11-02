package model.vo;

import java.util.Date;

public class AvariacaoVO {
	private int idAvariacao;
	private String pecaDanificada;
	private String tipoAvariacao;
	private int qtdPeca;
	private Date dataAvariacao;
	
	
	
	public AvariacaoVO(int idAvariacao, String pecaDanificada, String tipoAvariacao, int qtdPeca, Date dataAvariacao) {
		this.idAvariacao = idAvariacao;
		this.pecaDanificada = pecaDanificada;
		this.tipoAvariacao = tipoAvariacao;
		this.qtdPeca = qtdPeca;
		this.dataAvariacao = dataAvariacao;
	}
	
	public int getIdAvariacao() {
		return idAvariacao;
	}
	public void setIdAvariacao(int idAvariacao) {
		this.idAvariacao = idAvariacao;
	}
	public String getPecaDanificada() {
		return pecaDanificada;
	}
	public void setPecaDanificada(String pecaDanificada) {
		this.pecaDanificada = pecaDanificada;
	}
	public String getTipoAvariacao() {
		return tipoAvariacao;
	}
	public void setTipoAvariacao(String tipoAvariacao) {
		this.tipoAvariacao = tipoAvariacao;
	}
	public int getQtdPeca() {
		return qtdPeca;
	}
	public void setQtdPeca(int qtdPeca) {
		this.qtdPeca = qtdPeca;
	}
	public Date getDataAvariacao() {
		return dataAvariacao;
	}
	public void setDataAvariacao(Date dataAvariacao) {
		this.dataAvariacao = dataAvariacao;
	}
	
	
}
