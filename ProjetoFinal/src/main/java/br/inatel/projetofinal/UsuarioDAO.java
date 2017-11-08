package br.inatel.projetofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
