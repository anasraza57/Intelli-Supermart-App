package com.example.intelli_supermart_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PictureModel {
    int id;
    public String name;

    public PictureModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static List<PictureModel> ParseJson(String json) {

        List<PictureModel> pictureModelList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray picturesArray = jsonObject.getJSONArray("category_images");
            for (int i = 0; i < picturesArray.length(); i++) {
                JSONObject pictureObject = picturesArray.getJSONObject(i);
                int id = pictureObject.getInt("picture_id");
                String name = pictureObject.getString("picture_url");
                PictureModel pictureModel = new PictureModel(id, name);
                pictureModelList.add(pictureModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pictureModelList;
    }
}
