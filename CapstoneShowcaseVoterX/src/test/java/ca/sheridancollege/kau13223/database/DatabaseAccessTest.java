package ca.sheridancollege.kau13223.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import ca.sheridancollege.kau13223.beans.CapstoneProject;

@SpringBootTest
public class DatabaseAccessTest {

    @Autowired
    private DatabaseAccess da;

    @Test
    public void testInsertProjectAndGetList() {
        // setup
        int size = da.getProjectList().size();

        // Create a new project
        CapstoneProject project = new CapstoneProject();
        project.setProjectName("Test Project");
        project.setVideoLink("https://example.com");
        project.setTeamName("Test Team");
        project.setProjectDescription("This is a test project.");

        // when
        da.insertProject(project);

        // then (the actual test)
        assertEquals(da.getProjectList().size(), ++size);
    }
}
