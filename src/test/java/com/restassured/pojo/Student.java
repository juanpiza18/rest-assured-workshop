package com.restassured.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Student extends BasePojo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> interests;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> skillNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<String> getInterests() {
        return interests;
    }

    public List<String> getSkillNames() {
        return skillNames;
    }

    public void setSkillNames(List<String> skillNames) {
        this.skillNames = skillNames;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
