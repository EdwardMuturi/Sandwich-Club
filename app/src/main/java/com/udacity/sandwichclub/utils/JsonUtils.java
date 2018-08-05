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

    public static Sandwich parseSandwichJson(String json) {
        List<String> alsoKnownAsList= new ArrayList<>();
        List<String> ingredientsList= new ArrayList<>();
        Sandwich sandwichDetails = new Sandwich();

        if (json != null) {
            try {
                //fetching sandwich details
                JSONObject sandwiches = new JSONObject(json);

                //fetch name object
                JSONObject name = sandwiches.optJSONObject("name");
                String mainName = name.optString("mainName");

                JSONArray alsoKnownas = name.optJSONArray("alsoKnownAs");
                for (int x = 0; x < alsoKnownas.length(); x++) {
                    String otherName= alsoKnownas.optString(x);
                    alsoKnownAsList.add(otherName);

                }

                JSONArray ingredients= sandwiches.optJSONArray("ingredients");
                for (int x = 0; x < ingredients.length(); x++) {
                    String ingr= ingredients.optString(x);
                    ingredientsList.add(ingr);

                }


                String origin = sandwiches.optString("placeOfOrigin");
                String description = sandwiches.optString("description");
                String image = sandwiches.optString("image");

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
