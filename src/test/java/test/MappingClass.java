package test;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MappingClass {

    @Test
    public void JsonToObject(){

        String jsonBody ="{\n" +
                "\t\t\"batch\": 0,\n" +
                "\t\t\"birthDate\": \"12/12/2019\",\n" +
                "\t\t\"department\": \"Math\",\n" +
                "\t\t\"emailAddress\": \"some@gmail.com\",\n" +
                "\t\t\"firstName\": \"John\",\n" +
                "\t\t\"gender\": \"Male\",\n" +
                "\t\t\"joinDate\": \"12/12/2019\",\n" +
                "\t\t\"lastName\": \"Smith\",\n" +
                "\t\t\"password\": \"1234\",\n" +
                "\t\t\"phone\": \"123123123\",\n" +
                "\t\t\"premanentAddress\": \"123. Main st\",\n" +
                "\t\t\"salary\": 1000,\n" +
                "\t\t\"section\": \"Ap-123\",\n" +
                "\t\t\"subject\": \"Intro to Math\",\n" +
                "\t\t\"teacherId\": 12\n" +
                "\t   }";



        ObjectMapper mapper = new ObjectMapper();

        try {
            Teacher teacher = mapper.readValue(jsonBody, Teacher.class);
            System.out.println(teacher.getFirstName());
            System.out.println(teacher.getBatch());
            System.out.println(teacher.getLastName());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ObjectToJsonString(){

        Teacher teacher = new Teacher();
        teacher.setBatch(11);
        teacher.setBirthDate("12/12/2019");
        teacher.setDepartment("Maths");
        teacher.setEmailAddress("some@gmail.com");
        teacher.setGender("Male");
        teacher.setLastName("LastName");
        teacher.setFirstName("Adam");



        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonBody = mapper.writeValueAsString(teacher);
            System.out.println(jsonBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void postMethod(){

        Response response = RestAssured.get("http://api.cybertektraining.com/teacher/all");
        //System.out.println(response.asString());
        Assert.assertTrue(response.statusCode()==200);

        ObjectMapper mapper = new ObjectMapper();

        try {
            MyResponse myResponse = mapper.readValue(response.asString(), MyResponse.class);
            List<Teacher> myTeachers = myResponse.getTeachers();

            for(Teacher teacher: myTeachers){
                if(teacher.getBatch()==11){
                    System.out.println("FirstName: "+teacher.getFirstName()+" batch : "+teacher.getBatch());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        // get batch 11 first name




    }




}
