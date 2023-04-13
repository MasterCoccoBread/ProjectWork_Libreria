package it.corso.service;

import jakarta.servlet.http.HttpSession;

public interface AmministratoreService {
	
	 boolean controlloLogin(String username,String password,HttpSession session);

}
