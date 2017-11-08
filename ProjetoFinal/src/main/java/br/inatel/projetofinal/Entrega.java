package br.inatel.projetofinal;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entrega {
	private int id;
	private int numPedido;
	private int idCliente;
	private String nomeRecebidor;
	private String cpfRecebidor;
	private String dataHoraEntrega;
	
	public Entrega() {}
	
	public Entrega(int id, int numPedido, int idCliente, String nomeRecebidor, String cpfRecebidor,
			String dataHoraEntrega) {
		super();
		this.id = id;
		this.numPedido = numPedido;
		this.idCliente = idCliente;
		this.nomeRecebidor = nomeRecebidor;
		this.cpfRecebidor = cpfRecebidor;
		this.dataHoraEntrega = dataHoraEntrega;
	}
	
	public Entrega(int numPedido, int idCliente, String nomeRecebidor, String cpfRecebidor,
			String dataHoraEntrega) {
		this.numPedido = numPedido;
		this.idCliente = idCliente;
		this.nomeRecebidor = nomeRecebidor;
		this.cpfRecebidor = cpfRecebidor;
		this.dataHoraEntrega = dataHoraEntrega;
	}
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNomeRecebidor() {
		return nomeRecebidor;
	}
	public void setNomeRecebidor(String nomeRecebidor) {
		this.nomeRecebidor = nomeRecebidor;
	}
	public String getCpfRecebidor() {
		return cpfRecebidor;
	}
	public void setCpfRecebidor(String cpfRecebidor) {
		this.cpfRecebidor = cpfRecebidor;
	}
	public String getDataHoraEntrega() {
		return dataHoraEntrega;
	}
	public void setDataHoraEntrega(String dataHoraEntrega) {
		this.dataHoraEntrega = dataHoraEntrega;
	}
	
	
}
