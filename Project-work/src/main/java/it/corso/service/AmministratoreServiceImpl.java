package it.corso.service;
import org.springframework.stereotype.Service;
import it.corso.model.Amministratore;
import jakarta.servlet.http.HttpSession;

@Service
public class AmministratoreServiceImpl implements AmministratoreService {

	@Override
	public boolean controlloLogin(String username, String password, HttpSession session) {

		final String USERNAME_REGISTRATO = "admin";
		final String PASSWORD_REGISTRATA = "admin";
				
		if(username.equalsIgnoreCase(USERNAME_REGISTRATO)&& password.equals(PASSWORD_REGISTRATA)) {
					
			Amministratore admin = new Amministratore();
			admin.setUsername(USERNAME_REGISTRATO);
			admin.setPassword(PASSWORD_REGISTRATA);
					
			session.setAttribute("admin",admin);
				return true;
		}		
		return false;
	}
}
