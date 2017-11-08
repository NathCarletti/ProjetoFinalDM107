package br.inatel.projetofinal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.StringTokenizer;
import br.inatel.projetofinal.UsuarioDAO;

public class AuthenticationService {
	public boolean authenticate(String credentials) {
		
		System.out.println(credentials);

		if (null == credentials)
			return false;
		// extraindo o valor vindo do header "Basic encodedstring" for Basic
		//Exemplo: "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = credentials.replaceFirst("Basic"+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String usernameAndPassSplit[] = usernameAndPassword.split(":");
		final String username = usernameAndPassSplit[0];
		final String password = usernameAndPassSplit[1];
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		// Estamos usando um unico usuario e senha, aqui poderia ser feito via banco de dados
		boolean authenticationStatus=false;
		try {
			authenticationStatus = usuarioDAO.authUsuario(username, password);
			System.out.println("olá");
			System.out.println(authenticationStatus);
			//return authenticationStatus;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authenticationStatus;
	}

}
