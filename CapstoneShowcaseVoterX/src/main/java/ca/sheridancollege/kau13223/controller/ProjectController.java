package ca.sheridancollege.kau13223.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import ca.sheridancollege.kau13223.beans.CapstoneProject;
import ca.sheridancollege.kau13223.database.DatabaseAccess;

@Controller
public class ProjectController {
	
	@Autowired 
	@Lazy
	private DatabaseAccess da;
	
	final boolean ADMIN= false;
	
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("project", new CapstoneProject());
		model.addAttribute("projectList", da.getProjectList());
		model.addAttribute("admin", ADMIN);   
		
		return "index";
	}   
	
	@PostMapping("/insertProject")
	public String insertProject(Model model, @ModelAttribute CapstoneProject project)
	{
		da.insertProject(project);
		model.addAttribute("project", new CapstoneProject());
		model.addAttribute("projectList", da.getProjectList());
		model.addAttribute("admin", ADMIN);
		
		return "redirect:/";
	}
	
	 @GetMapping("/voteDown/ {id}")
	    public String voteDown(@PathVariable Long id) {
	        da.voteDown(id);
	        return "redirect:/";
	    }
	 
	 @GetMapping("/voteUp/{id}")
	    public String voteUp(@PathVariable Long id) {
	        da.voteUp(id);
	        return "redirect:/";
	    }

	    
	    @GetMapping("/deleteProjectById/{id}")
		public String deleteAppointmentById(Model model, @PathVariable Long id) {

			da.deleteProjectById(id);
			model.addAttribute("project", new CapstoneProject());
			model.addAttribute("projectList", da.getProjectList());
			
			return "index";
		}

	
	


	@RequestMapping(value = "/secure/index", method = {RequestMethod.GET, RequestMethod.POST})
	public String secureIndex(Model model, Authentication authentication) {

		String email = authentication.getName();
		List<String> roleList = new ArrayList<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) {
			roleList.add(ga.getAuthority());
		}
		model.addAttribute("email", email);
		model.addAttribute("roleList", roleList);
		model.addAttribute("project", new CapstoneProject());
		model.addAttribute("projectList", da.getProjectList());
		model.addAttribute("admin", ADMIN);

		return "/secure/index";
	}

	@GetMapping("/secure/check")
	public String secureCheck() {
		return "/secure/check";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/permission-denied")
	public String permissionDenied() {
		return "/error/permission-denied";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}

	@PostMapping("/register")
	public String postRegister(@RequestParam String username, @RequestParam String password) {
		// more code here in a jiff

		da.addUser(username, password);
		Long userId = da.findUserAccount(username).getUserId();
		// this next line is dangerous! For extra credit, try making a DatabaseAccess
		// method to find a roleId
		// associate with a role of “ROLE_USER” and add the “correct” id every time ☺
		da.addRole(userId, Long.valueOf(1));

		return "redirect:/";
	}


}
