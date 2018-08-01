package com.udacity.sandwichclub.utils;

import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    public static Sandwich parseSandwichJson(String json) {
        List<String> alsoKnownAsList= new ArrayList<>();
        List<String> ingredientsList= new ArrayList<>();
        Sandwich sandwichDetails = new Sandwich();
        if (json != null) {
            try {
                //fetching sandwich details
                JSONObject sandwiches = new JSONObject(json);

                //fetch name object
                JSONObject name = sandwiches.getJSONObject("name");
                String mainName = name.getString("mainName");

                JSONArray alsoKnownas = name.getJSONArray("alsoKnownAs");
                for (int x = 0; x < alsoKnownas.length(); x++) {
                    String otherName= alsoKnownas.getString(x);
                    alsoKnownAsList.add(otherName);

                }

                JSONArray ingredients= sandwiches.getJSONArray("ingredients");
                for (int x = 0; x < ingredients.length(); x++) {
                    String ingr= ingredients.getString(x);
                    ingredientsList.add(ingr);

                }


                String origin = sandwiches.getString("placeOfOrigin");
                String description = sandwiches.getString("description");
                String image = sandwiches.getString("image");

                sandwichDetails = new Sandwich(mainName, alsoKnownAsList, origin, description, image, ingredientsList);

            } catch (JSONException e) {
                Log.e("JSONUtils", e.getMessage());
            }
        } else {
            Log.e("Json Utils", "Couldn't get json from server");
        }

        return sandwichDetails;
    }


}
