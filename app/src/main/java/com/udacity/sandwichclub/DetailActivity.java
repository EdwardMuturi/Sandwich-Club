package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView tvOrigin, tvKnownas, tvDescription, tvIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String clickedSandwichJson = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(clickedSandwichJson);
        Log.e("DetailsAct", sandwich.getDescription());
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        tvOrigin.setText(sandwich.getPlaceOfOrigin());
        StringBuilder builder= new StringBuilder();
        for (String knownAs: sandwich.getAlsoKnownAs()){
            builder.append(knownAs + "\n");
        }
        tvKnownas.setText(builder.toString());

        StringBuilder ingredientsBuilder= new StringBuilder();
        for (String sIngredients: sandwich.getIngredients()){
            ingredientsBuilder.append(sIngredients + "\n");
        }
        tvIngredients.setText(ingredientsBuilder.toString());
        tvDescription.setText(sandwich.getDescription());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        tvOrigin= findViewById(R.id.origin_tv);
        tvKnownas = findViewById(R.id.also_known_tv);
        tvDescription = findViewById(R.id.description_tv);
        tvIngredients = findViewById(R.id.ingredients_tv);
    }
}
