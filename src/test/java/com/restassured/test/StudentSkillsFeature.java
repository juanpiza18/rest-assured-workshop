package com.restassured.test;

import com.restassured.pojo.*;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StudentSkillsFeature extends AuthWithSessionTokenParseApi {
    public CreatedStudent createdStudent;
    public Skill selectedSkill;

    @BeforeMethod
    public void createStudentAndSelectingSkill() {
        String studentRandomString = RandomStringUtils.random(6, true, false);
        List<String> interest = new ArrayList<String>();
        interest.add("Soccer");
        interest.add("Tennis");
        interest.add("Video Games");
        Student student = new Student();
        student.setName(studentRandomString);
        student.setLastname(studentRandomString);
        student.setInterests(interest);

        createdStudent = given()
                .body(student)
                .contentType(ContentType.JSON)
                .when()
                .post("/classes/Students")
                .then()
                .statusCode(201)
                .extract().body().as(CreatedStudent.class);

        Random random = new Random();
        Skills skillsList = when().get("classes/Skills").as(Skills.class);
        int randomIndex = random.nextInt(skillsList.getResults().size());
        selectedSkill =  skillsList.getResults().get(randomIndex);
    }

    @Test(priority = 0, description="Verify adding a skill to a student.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: the skills management service StudentSkills with valid authentication")
    public void verify_skills_can_be_added_to_student() {
        String studentId = createdStudent.getObjectId();
        AssociateSkill associateSkill = new AssociateSkill();
        associateSkill.setStudentId(studentId);
        associateSkill.setSkillId(selectedSkill.getObjectId());

        given()
                .body(associateSkill)
                .contentType(ContentType.JSON)
        .when()
                .post("/classes/StudentSkills")
        .then()
                .statusCode(201)
                .body("objectId", notNullValue())
                .body("createdAt", notNullValue());

        given()
                .pathParams("studentId", studentId)
        .when()
                .get("/classes/Students/{studentId}")
        .then()
                .statusCode(200)
                .body("skillNames", notNullValue());
    }

    @Test(priority = 0, description="Verify adding a repeated skill to a student")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: include an error message saying there is duplicate information")
    public void verify_adding_repeated_skills_to_student() {
        String studentId = createdStudent.getObjectId();
        AssociateSkill associateSkill = new AssociateSkill();
        associateSkill.setStudentId(studentId);
        associateSkill.setSkillId(selectedSkill.getObjectId());

        given()
                .body(associateSkill)
                .contentType(ContentType.JSON)
        .when()
                .post("/classes/StudentSkills")
        .then()
                .statusCode(201)
                .body("objectId", notNullValue())
                .body("createdAt", notNullValue());

        given()
                .body(associateSkill)
                .contentType(ContentType.JSON)
        .when()
                .post("/classes/StudentSkills")
        .then()
                .statusCode(400)
                .body("error", is("A duplicate value for a field with unique values was provided"));

    }
}
