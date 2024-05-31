package com.jayesh.gstcalculator;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.jayesh.gstcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    EditText rupees, percentage;
//    TextView originalAmountShow, gstAmountShow, prePostGSTAmountShow, exclusiveInclusiveText, gstAmountPercentageText, prePostGSTText, cgstText, sgstText, cgstShow, sgstShow;
//    Button calculateExclusive, calculateInclusive;
//    LinearLayout cardLayout;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);         // To prevent from screenshot and screen recording
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        getSupportActionBar().hide();

//        rupees = (EditText) findViewById(R.id.rupees);
//        percentage = (EditText) findViewById(R.id.percentage);
//        calculateExclusive = (Button) findViewById(R.id.calculateExclusive);
//        calculateInclusive = (Button) findViewById(R.id.calculateInclusive);
//        originalAmountShow = (TextView) findViewById(R.id.originalAmountShow);
//        gstAmountShow = (TextView) findViewById(R.id.gstAmountShow);
//        prePostGSTAmountShow = (TextView) findViewById(R.id.prePostGSTAmountShow);
//        exclusiveInclusiveText = (TextView) findViewById(R.id.exclusiveInclusiveText);
//        gstAmountPercentageText = (TextView) findViewById(R.id.gstAmountPercentageText);
//        prePostGSTText = (TextView) findViewById(R.id.prePostGSTText);
//        cardLayout = findViewById(R.id.cardLayout);
//        cgstText = (TextView) findViewById(R.id.cgstText);
//        sgstText = (TextView) findViewById(R.id.sgstText);
//        cgstShow = (TextView) findViewById(R.id.cgstShow);
//        sgstShow = (TextView) findViewById(R.id.sgstShow);

        binding.cardLayout.setVisibility(View.GONE);

        binding.calculateExclusive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(binding.rupees.getText()) && TextUtils.isEmpty(binding.percentage.getText()))
                {
                    binding.rupees.setError("Required");
                    binding.rupees.requestFocus();

                    binding.percentage.setError("Required");
                }
                else if (TextUtils.isEmpty(binding.rupees.getText()))
                {
                    binding.rupees.setError("Required");
                    binding.rupees.requestFocus();
                }
                else if (TextUtils.isEmpty(binding.percentage.getText()))
                {
                    binding.percentage.setError("Required");
                    binding.percentage.requestFocus();
                }
                else
                {
                    float originalValue, gstValue, gstPercentage, postGST, a, newCGST;

                    binding.cardLayout.setVisibility(View.VISIBLE);

                    originalValue = Float.parseFloat(binding.rupees.getText().toString());
                    gstPercentage = Float.parseFloat(binding.percentage.getText().toString());

                    gstValue = (originalValue * gstPercentage) / 100;
                    postGST = originalValue + gstValue;
                    a = gstPercentage / 2;

                    newCGST = gstValue / 2;


                    String x = String.valueOf(originalValue);
                    String y = String.format("%.2f",postGST);
                    String z = String.format("%.2f",gstValue);
                    String w = String.format("%.2f",newCGST);

                    Toast.makeText(MainActivity.this, "Exclusive GST Calculated", Toast.LENGTH_SHORT).show();

                    binding.exclusiveInclusiveText.setText("Exclusive GST");
                    binding.gstAmountPercentageText.setText("GST " + "(" + gstPercentage + "%)");
                    binding.prePostGSTText.setText("Post-GST Amt.");

                    binding.originalAmountShow.setText("₹" + x);
                    binding.gstAmountShow.setText("₹" + z);
                    binding.prePostGSTAmountShow.setText("₹" + y);

                    binding.cgstText.setText("CGST" + "(" + a + "%)");
                    binding.sgstText.setText("SGST" + "(" + a + "%)");
                    binding.cgstShow.setText("₹" + w);
                    binding.sgstShow.setText("₹" + w);


                    closeKeyboard();
                }
            }
        });

        binding.calculateInclusive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(binding.rupees.getText()) && TextUtils.isEmpty(binding.percentage.getText()))
                {
                    binding.rupees.setError("Required");
                    binding.rupees.requestFocus();

                    binding.percentage.setError("Required");
                }
                else if (TextUtils.isEmpty(binding.rupees.getText()))
                {
                    binding.rupees.setError("Required");
                    binding.rupees.requestFocus();
                }
                else if (TextUtils.isEmpty(binding.percentage.getText()))
                {
                    binding.percentage.setError("Required");
                    binding.percentage.requestFocus();
                }
                else
                {
                    float originalValue, gstValue, gstPercentage, preGST, p, a, newCGST;

                    binding.cardLayout.setVisibility(View.VISIBLE);

                    originalValue = Float.parseFloat(binding.rupees.getText().toString());
                    gstPercentage = Float.parseFloat(binding.percentage.getText().toString());

                    p = 100 / (100 + gstPercentage);
                    gstValue = originalValue - (originalValue * p);
                    preGST = originalValue - gstValue;

                    newCGST = gstValue / 2;

                    a = gstPercentage / 2;

                    String x = String.valueOf(originalValue);
                    String y = String.format("%.2f",preGST);
                    String z = String.format("%.2f",gstValue);
                    String w = String.format("%.2f",newCGST);

                    Toast.makeText(MainActivity.this, "Inclusive GST Calculated", Toast.LENGTH_SHORT).show();

                    binding.exclusiveInclusiveText.setText("Inclusive GST");
                    binding.gstAmountPercentageText.setText("GST " + "(" + gstPercentage + "%)");
                    binding.prePostGSTText.setText("Pre-GST Amt.");

                    binding.originalAmountShow.setText("₹" + x);
                    binding.gstAmountShow.setText("₹" + z);
                    binding.prePostGSTAmountShow.setText("₹" + y);

                    binding.cgstText.setText("CGST" + "(" + a + "%)");
                    binding.sgstText.setText("SGST" + "(" + a + "%)");
                    binding.cgstShow.setText("₹" + w);
                    binding.sgstShow.setText("₹" + w);

                    closeKeyboard();
                }
            }
        });

    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}