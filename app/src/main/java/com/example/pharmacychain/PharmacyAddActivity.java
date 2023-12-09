package com.example.pharmacychain;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PharmacyAddActivity extends AppCompatActivity {
    private EditText addressEditText, phoneEditText, emailEditText, hoursEditText;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_add);

        dbHelper = new SQLiteHelper(this);

        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        hoursEditText = findViewById(R.id.hoursEditText);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStore();
            }
        });
    }

    private void addStore() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("адрес", addressEditText.getText().toString());
        values.put("телефон", phoneEditText.getText().toString());
        values.put("электронная_почта", emailEditText.getText().toString());
        values.put("рабочие_часы", hoursEditText.getText().toString());

        long newRowId = db.insert("аптеки", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Аптека добавлена успешно!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ошибка при добавлении аптеки", Toast.LENGTH_SHORT).show();
        }

        db.close();
        finish(); // закрыть активити после добавления
    }
}
