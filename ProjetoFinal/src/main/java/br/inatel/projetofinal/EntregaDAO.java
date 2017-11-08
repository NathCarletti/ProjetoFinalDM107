package br.inatel.projetofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {
private Connection conn = null;
	
	public EntregaDAO(){
		if (conn == null) {
			try {
				this.conn = new ConnectionDB().getConnection();
				System.out.println("cheguei");
			} catch (Exception e) {
				System.out.println("falhei");
				e.printStackTrace();
			}
		}
	}
	
	public List<Entrega> list() throws SQLException{
		String sql="SELECT * FROM entregadb";
		PreparedStatement  stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery(sql);
		Entrega entrega = null;
		List<Entrega>entregas = new ArrayList<Entrega>();
		
		while(rs.next()) {
			entrega = new Entrega();
			entrega.setId(rs.getInt("id"));
			entrega.setIdCliente(rs.getInt("idCliente"));
			entrega.setNumPedido(rs.getInt("numPedido"));
			entrega.setNomeRecebidor(rs.getString("nomeRecebidor"));
			entrega.setCpfRecebidor(rs.getString("cpfRecebidor"));
			entrega.setDataHoraEntrega(rs.getString("dataHoraEntrega"));
			entregas.add(entrega);
		}
		rs.close();
		stm.close();
		return entregas;
	}
	
	
	public Entrega getElementByNumber(int numPedido) throws SQLException{
		String sql="SELECT * FROM entregadb WHERE numPedido = (?)";
		PreparedStatement  stm = conn.prepareStatement(sql);
		stm.setInt(1, numPedido);
		ResultSet rs = stm.executeQuery(sql);
		Entrega entrega = null;
		
		while(rs.next()) {
			entrega = new Entrega();
			 entrega.setId(rs.getInt("id"));
			entrega.setIdCliente(rs.getInt("idCliente"));
			entrega.setNumPedido(rs.getInt("numPedido"));
			entrega.setNomeRecebidor(rs.getString("nomeRecebidor"));
			entrega.setCpfRecebidor(rs.getString("cpfRecebidor"));
			entrega.setDataHoraEntrega(rs.getString("dataHoraEntrega"));
			
		}
		rs.close();
		stm.close();
		
		return entrega;
	}
	
	
	
	public Entrega insertEntrega (Entrega entrega) throws SQLException {
		String sql = "INSERT INTO entregadb (idCliente, numPedido, nomeRecebidor, cpfRecebidor, dataHoraEntrega) VALUES (?, ?, ?, ?, ?);";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		//pstm.setInt(1, entrega.getId());
		pstm.setInt(1, entrega.getIdCliente());
		pstm.setInt(2, entrega.getNumPedido());
		pstm.setString(3, entrega.getNomeRecebidor());
		pstm.setString(4, entrega.getCpfRecebidor());
		pstm.setString(5, entrega.getDataHoraEntrega());
		
		pstm.execute();
		
		return getLastInserted();
	}
	
	private Entrega getLastInserted()  throws SQLException{
		String sql = "SELECT *FROM entregadb WHERE id = (SELECT MAX(id) FROM entregadb)";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Entrega entrega = null;

		while(rs.next()) {
			entrega = new Entrega();
			entrega.setIdCliente(rs.getInt("idCliente"));
			entrega.setNumPedido(rs.getInt("numPedido"));
			entrega.setNomeRecebidor(rs.getString("nomeRecebidor"));
			entrega.setCpfRecebidor(rs.getString("cpfRecebidor"));
			entrega.setDataHoraEntrega(rs.getString("dataHoraEntrega"));
		}
		
		return entrega;
	}
}

