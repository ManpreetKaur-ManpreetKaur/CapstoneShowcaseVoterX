package ca.sheridancollege.kau13223.beans;



import lombok.Data;

import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CapstoneProject {
    private Long id;
    private String projectName;
    private String videoLink;
    private String teamName;
    private String projectDescription;
    private int votes;
}    