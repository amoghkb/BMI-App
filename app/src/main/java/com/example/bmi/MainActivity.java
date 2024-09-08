package com.example.bmi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    Toolbar tool1;
    Intent I1;
    @SuppressLint("SetTextI18n")
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText e1 = findViewById(R.id.height); // Height input in feet
        EditText e2 = findViewById(R.id.weight); // Weight input in kilograms
        Button b1 = findViewById(R.id.button);   // Calculate button
        TextView t1 = findViewById(R.id.showtext); // TextView to display the result

        // Set a click listener for the button
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input from the EditText fields
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();

                // Check if inputs are not empty
                if (!s1.isEmpty() && !s2.isEmpty()) {
                    try {
                        // Convert the input to double for calculations
                        double h1 = Double.parseDouble(s1); // Height in feet
                        double w2 = Double.parseDouble(s2); // Weight in kilograms

                        // Convert height from feet to meters
                        double totalHeightInMeters = h1 * 0.3048;

                        // Calculate BMI
                        double totalValue = w2 / Math.pow(totalHeightInMeters, 2);

                        // Display BMI category based on calculated value
                        if (totalValue < 18.5) {
                            t1.setText("You Are Underweight");
                        } else if (totalValue >= 18.5 && totalValue < 24.9) {
                            t1.setText("You Are Normal weight");
                        } else if (totalValue >= 25 && totalValue < 29.9) {
                            t1.setText("You Are Overweight");
                        } else {
                            t1.setText("You Are Obese");
                        }
                    } catch (NumberFormatException e) {
                        t1.setText("Invalid input. Please enter valid numbers.");
                    }
                } else {
                    // If inputs are empty, prompt the user to enter values
                    t1.setText("Please enter both height and weight");
                }
            }
        });


        tool1=findViewById(R.id.newtoolbar);

    I1=new Intent(MainActivity.this,Tips.class);
        setSupportActionBar(tool1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mytoolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.tips){
            startActivity(I1);
        }
        return super.onOptionsItemSelected(item);
    }
}
