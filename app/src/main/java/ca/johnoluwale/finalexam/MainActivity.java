package ca.johnoluwale.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinnerObjSides;
    String spinnerDiceSides;
    int spinnerIdSides, text;
    EditText editTextSides;
    TextView txtViewOne, txtViewTwo;
    ArrayAdapter<Integer> adapter;
    List<Integer> intList;
    ListView listViewHistory;
    String addDice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerObjSides = findViewById(R.id.spinnerDiceValue);
        Integer[] sides = new Integer[]{2, 4, 6, 8};
        intList = new LinkedList<>(Arrays.asList(sides));
        adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, intList);
        spinnerObjSides.setAdapter(adapter);

        Button btnRollOnce = findViewById(R.id.rollOnceBtn);
        Button btnRollTwice = findViewById(R.id.rollTwiceBtn);
        Button btnAddSides = findViewById(R.id.addDiceSides);

        editTextSides = findViewById(R.id.editTextNumber);
        txtViewOne = findViewById(R.id.firstTextView);
        txtViewTwo = findViewById(R.id.secondTextView);
        listViewHistory = findViewById(R.id.historyListView);
        btnAddSides.setOnClickListener(this);
        btnRollOnce.setOnClickListener(this);
        btnRollTwice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        spinnerDiceSides = spinnerObjSides.getSelectedItem().toString();
        spinnerIdSides =Integer.parseInt(spinnerDiceSides);
        Dice objDice = new Dice(spinnerIdSides);
        if (id == R.id.addDiceSides){
            addDice = editTextSides.getText().toString();
            try {//using a try and catch to handle the NumberFormatException error
                text = Integer.parseInt(addDice);
                intList.add(text);//add values to the spinner
                Collections.sort(intList); //sorting the entered custom dice side values numerically
                adapter.notifyDataSetChanged();//notifies the observer there's a change
                editTextSides.setText("");
            }catch (Exception e){
                Toast.makeText(this, "Enter a value to continue", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.rollOnceBtn){
            txtViewOne.setText(String.valueOf(objDice.getSideUp()));
            txtViewTwo.setVisibility(View.INVISIBLE);//setting second textview to invisible when rolling once
        }else if(id == R.id.rollTwiceBtn){
            txtViewOne.setText(String.valueOf(objDice.getSideUp()));
            objDice.roll();
            txtViewTwo.setText(String.valueOf(objDice.getSideUp()));
            txtViewTwo.setVisibility(View.VISIBLE);
        }
    }
}