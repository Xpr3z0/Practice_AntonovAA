package com.example.pharmacychain;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MedicineAddActivity extends AppCompatActivity {
    private EditText titleEditText, manufacturerEditText, typeEditText, priceEditText, formEditText;
    private CheckBox prescriptionCheckBox;  // Добавлен новый CheckBox
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);

        dbHelper = new SQLiteHelper(this);

        titleEditText = findViewById(R.id.titleEditText);
        manufacturerEditText = findViewById(R.id.manufacturerEditText);
        typeEditText = findViewById(R.id.typeEditText);
        priceEditText = findViewById(R.id.priceEditText);
        formEditText = findViewById(R.id.formEditText);
        prescriptionCheckBox = findViewById(R.id.prescriptionCheckBox);  // Новый CheckBox

        Button addButton = findViewById(R.id.addMedicineButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMedicine();
            }
        });
    }

    private void addMedicine() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("название", titleEditText.getText().toString());
        values.put("производитель", manufacturerEditText.getText().toString());
        values.put("тип", typeEditText.getText().toString());
        values.put("цена", Double.parseDouble(priceEditText.getText().toString()));
        values.put("форма_выпуска", formEditText.getText().toString());
        values.put("рецептурный", prescriptionCheckBox.isChecked() ? 1 : 0);  // Значение для булевого поля

        long newRowId = db.insert("препараты", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Препарат добавлен успешно!", Toast.LENGTH_SHORT).show();
            db.close();
            finish(); // закрыть активити после добавления
        } else {
            Toast.makeText(this, "Ошибка при добавлении препарата", Toast.LENGTH_SHORT).show();
            db.close();
        }
    }
}
