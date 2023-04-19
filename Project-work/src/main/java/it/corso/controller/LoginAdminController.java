package it.corso.controller;


import it.corso.service.AmministratoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/loginadmin")
public class LoginAdminController {

    @Autowired
   private AmministratoreService adminService;

    @GetMapping
    public String getPage(@RequestParam (name="le", required= false) String logError, Model model, HttpSession session)
    {

    	if(session.getAttribute("admin") != null)
    		return "redirect:/catalogo";
        model.addAttribute("logError",logError != null);

        	return "LoginAdmin";
    }

    
    @PostMapping 
    public String gestioneLogin(
            @RequestParam("username")String username,
            @RequestParam("password")String password,
            HttpSession session) {

        if(!adminService.controlloLogin(username, password,session))
            return "redirect:/loginadmin?le";


        return "redirect:/catalogo";
    }


}
