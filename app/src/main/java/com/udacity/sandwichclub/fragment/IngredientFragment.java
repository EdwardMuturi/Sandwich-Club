package com.udacity.sandwichclub.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.sandwichclub.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.ingredients_tv)
    TextView tvIngredients;

    private static List<String> ingr= new ArrayList<>();

    public IngredientFragment() {
        // Required empty public constructor
    }

    public static IngredientFragment newInstance(List<String> ingredient) {
        IngredientFragment fragment = new IngredientFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        ingr =ingredient;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_ingridient, container, false);
        unbinder= ButterKnife.bind(this, view);
        tvIngredients.setText(TextUtils.join(", ", ingr));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
