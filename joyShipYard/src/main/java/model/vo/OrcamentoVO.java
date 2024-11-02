package model.vo;

import java.util.Date;

public class OrcamentoVO {
	private int idOrcamento;
	private int qtdPeca;
	private float valorTotal;
	private Date dataOrcamento;
	public OrcamentoVO(int idOrcamento, int qtdPeca, float valorTotal, Date dataOrcamento) {
		this.idOrcamento = idOrcamento;
		this.qtdPeca = qtdPeca;
		this.valorTotal = valorTotal;
		this.dataOrcamento = dataOrcamento;
	}
	
	public int getIdOrcamento() {
		return idOrcamento;
	}
	public void setIdOrcamento(int idOrcamento) {
		this.idOrcamento = idOrcamento;
	}
	public int getQtdPeca() {
		return qtdPeca;
	}
	public void setQtdPeca(int qtdPeca) {
		this.qtdPeca = qtdPeca;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getDataOrcamento() {
		return dataOrcamento;
	}
	public void setDataOrcamento(Date dataOrcamento) {
		this.dataOrcamento = dataOrcamento;
	}
	

}
