package br.inatel.projetofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private Connection conn = null;

	public UsuarioDAO() {
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

	public List<Usuario> list() throws SQLException {
		String sql = "SELECT * FROM usuariodb";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery(sql);
		Usuario usuario = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		while (rs.next()) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuarios.add(usuario);
		}
		rs.close();
		stm.close();
		return usuarios;
	}

	
	public Usuario insertUsuario (Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuariodb (usuario,senha) VALUES (?, ?);";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, usuario.getUsuario());
		pstm.setString(2, usuario.getSenha());
		
		pstm.execute();
		
		return getLastInserted();
	}
	
	private Usuario getLastInserted()  throws SQLException{
		String sql = "SELECT *FROM usuariodb WHERE id = (SELECT MAX(id) FROM usuariodb)";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Usuario usuario = null;

		while(rs.next()) {
			usuario = new Usuario();
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
		}
		
		return usuario;
	}
	
	
	boolean authUsuario(String user, String senha) throws SQLException {
		Usuario usuario = new Usuario();
		boolean flag = false;
		String sql = "SELECT * FROM usuariodb WHERE usuario = '" + user + "' AND senha = '" + senha + "'";
		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			if (usuario.getUsuario().equals(user) && usuario.getSenha().equals(senha)) {
				flag = true;
				break;
			}
		}
		rs.close();
		stm.close();
		if (flag)
			return true;
		else
			return false;
	}

}
