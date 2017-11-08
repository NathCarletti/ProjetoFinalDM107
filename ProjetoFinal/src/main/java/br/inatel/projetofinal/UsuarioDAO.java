package br.inatel.projetofinal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private Connection conn = null;
	
	public UsuarioDAO(){
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
	
	public List<Usuario> list() throws SQLException{
		String sql="SELECT * FROM usuariodb";
		PreparedStatement  stm = conn.prepareStatement(sql);
		ResultSet rs = stm.executeQuery(sql);
		Usuario usuario = null;
		List<Usuario>usuarios = new ArrayList<Usuario>();
		
		while(rs.next()) {
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
}
