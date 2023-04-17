package it.corso.service;


import org.springframework.stereotype.Service;

import it.corso.model.Amministratore;
import jakarta.servlet.http.HttpSession;

@Service
public class AmministratoreServiceImpl implements AmministratoreService {

	@Override
	public boolean controlloLogin(String username, String password, HttpSession session) {
		 
			
		
				//Va cancellato attenzione 
				final String USERNAME_REGISTRATO = "admin";
				final String PASSWORD_REGISTRATA = "admin";
				
				if(username.equalsIgnoreCase(USERNAME_REGISTRATO)&& password.equals(PASSWORD_REGISTRATA)) {
					
					Amministratore admin = new Amministratore();
					admin.setUsername(USERNAME_REGISTRATO);
					admin.setPassword(PASSWORD_REGISTRATA);
					
					session.setAttribute("amministratore",admin);
					
					return true;
				} else 			
					
				return false;
	}

}
