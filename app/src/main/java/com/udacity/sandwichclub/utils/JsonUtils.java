package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class JsonUtils {
    //trying to use xml parser
//    private static final
    public static Sandwich parseSandwichJson(String json){

        return null;
    }


//    public static Sandwich parseSandwichJson(String json) {
//        Sandwich sandwich = new Sandwich();
//        if (json != null) {
//            try {
//                JSONObject jsonObject = new JSONObject(json);
//                JSONArray sandwiches = jsonObject.getJSONArray("sandwich_details");
//
//                for (int i = 0; i < sandwiches.length(); i++) {
//                    JSONObject s = sandwiches.getJSONObject(i);
//
//                    String mainName = s.getString("mainName");
//                    List<String> alsoKnownAs = Collections.singletonList(s.getString("alsoKnownAs"));
//                    String placeOfOrigin = s.getString("placeOfOrigin");
//                    String description = s.getString("description");
//                    String image = s.getString("image");
//                    List<String> ingredients = Collections.singletonList(s.getString("ingredients"));
//
//                    sandwich.setMainName(mainName);
//                    sandwich.setPlaceOfOrigin(placeOfOrigin);
//                    sandwich.setDescription(description);
//                    sandwich.setImage(image);
//                    sandwich.setIngredients(ingredients);
//                }
//
//            } catch (JSONException e) {
//                Log.e("JSONUtilsParse ", e.getMessage());
//            }
//        } else {
//            String e = "Empty Json String";
//            Log.e("JSONUtils ::", e);
//        }
//
//        return sandwich;
//    }
}
