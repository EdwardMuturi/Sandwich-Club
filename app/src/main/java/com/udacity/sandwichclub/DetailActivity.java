package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.adapter.ViewPagerAdapter;
import com.udacity.sandwichclub.fragment.DescriptionFragment;
import com.udacity.sandwichclub.fragment.IngredientFragment;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView tvOrigin, tvKnownas;
    TabLayout tabLayout;
    ViewPager viewPager;

    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        sandwich = JsonUtils.parseSandwichJson(clickedSandwichJson);

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

        StringBuilder builder = new StringBuilder();
        for (String knownAs : sandwich.getAlsoKnownAs()) {
            builder.append(knownAs + "\t ");
        }
        tvKnownas.setText(builder.toString());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        tvOrigin = findViewById(R.id.origin_tv);
        tvKnownas = findViewById(R.id.also_known_tv);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);

        checkEmpty();

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DescriptionFragment.newInstance (sandwich.getDescription()), "Description");
        adapter.addFragment(IngredientFragment.newInstance(sandwich.getIngredients()), "Ingredients");
        viewPager.setAdapter(adapter);
    }

    public void checkEmpty() {
        if (sandwich.getAlsoKnownAs().isEmpty()){
            TextView knownAsLabel= findViewById(R.id.also_known_label);
            tvKnownas.setVisibility(View.INVISIBLE);
            knownAsLabel.setVisibility(View.INVISIBLE);

            tvKnownas.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);
            knownAsLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);

        }

        if (sandwich.getPlaceOfOrigin().isEmpty()){
            TextView originLabel= findViewById(R.id.origin_label);
            originLabel.setVisibility(View.INVISIBLE);
            tvOrigin.setVisibility(View.INVISIBLE);

            tvOrigin.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);
            originLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 0);
        }

    }

}
