package ca.sheridancollege.kau13223.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import ca.sheridancollege.kau13223.beans.CapstoneProject;
import ca.sheridancollege.kau13223.beans.User;

@Repository
public class DatabaseAccess {

	@Autowired
	@Lazy
	private NamedParameterJdbcTemplate jdbc;
	
	public void insertProject(CapstoneProject project) {
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    String query = "INSERT INTO capstoneProject(projectName, videoLink, teamName, projectDescription, votes) " + "VALUES(:projectName, :videoLink, :teamName, :projectDescription, :votes)";

	    namedParameters.addValue("projectName", project.getProjectName());
	    namedParameters.addValue("videoLink", project.getVideoLink());
	    namedParameters.addValue("teamName", project.getTeamName());
	    namedParameters.addValue("projectDescription", project.getProjectDescription());
	    namedParameters.addValue("votes", project.getVotes());

	    jdbc.update(query, namedParameters);
	}

	public List<CapstoneProject> getProjectList()
	{
	   MapSqlParameterSource namedParameters= new MapSqlParameterSource();
	   String query= "SELECT * FROM capstoneProject  ";
	   		
	   return jdbc.query(query,  namedParameters, new BeanPropertyRowMapper<CapstoneProject>(CapstoneProject.class));
	   
	}
	public void deleteProjectById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM capstoneProject WHERE id = :id";
		
		namedParameters.addValue("id", id);
		if (jdbc.update(query, namedParameters) > 0)
		System.out.println("Deleted project " + id + " from database.");
		}
	
	public void voteUp(Long id) {
        
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
        String query = "UPDATE capstoneProject SET votes = votes + 1 WHERE id = :id";
		namedParameters.addValue("id", id);
		jdbc.update(query, namedParameters);	
    }

    public void voteDown(Long id) {
    	MapSqlParameterSource namedParameters= new MapSqlParameterSource();
        String query = "UPDATE capstoneProject SET votes = votes - 1 WHERE id =  :id";
		namedParameters.addValue("id", id);
		jdbc.update(query, namedParameters);	
    }
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User findUserAccount(String email) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user where email = :email";
		namedParameters.addValue("email", email);
		try {
			return jdbc.queryForObject(query, namedParameters, new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException erdae) {
			return null;
		}
	}

	public List<String> getRolesById(Long userId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT sec_role.roleName " + "FROM user_role, sec_role "
				+ "WHERE user_role.roleId = sec_role.roleId " + "AND userId = :userId";
		namedParameters.addValue("userId", userId);
		return jdbc.queryForList(query, namedParameters, String.class);
	}

	public void addUser(String email, String password) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO sec_user " + "(email, encryptedPassword, enabled) "
				+ "VALUES (:email, :encryptedPassword, 1)";
		namedParameters.addValue("email", email);
		namedParameters.addValue("encryptedPassword", passwordEncoder.encode(password));
		jdbc.update(query, namedParameters);
	}
	
	public void addRole(Long userId, Long roleId) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId) "+ "VALUES (:userId, :roleId)";
		namedParameters.addValue("userId", userId);
		namedParameters.addValue("roleId", roleId);
		jdbc.update(query, namedParameters);
		}
	
}
