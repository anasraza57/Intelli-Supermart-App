package com.example.intelli_supermart_app;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
    public String name;
    public String rollnum;


    // making  a function to parseJson and then return list
        public static List<StudentModel> ParseJson(String json)
    {

        List<StudentModel> studentModelList = new ArrayList<>();

        try {
               JSONObject jsonObject = new JSONObject(json);

                StudentModel studentModel = new StudentModel();
                studentModel.name = jsonObject.getString("Name"); // we are giving keys
                studentModel.rollnum =jsonObject.getString("Rollno"); // we are giving keys
                studentModelList.add(studentModel);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return studentModelList;
    }
}
