package com.example.pharmacychain;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class InventoryAddActivity extends AppCompatActivity {
    private Spinner medicineSpinner, pharmacySpinner;
    private EditText quantityEditText;
    private TextView quantityInfoTextView;
    private RadioGroup operationRadioGroup;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_add);

        dbHelper = new SQLiteHelper(this);

        medicineSpinner = findViewById(R.id.medicineSpinner);
        pharmacySpinner = findViewById(R.id.pharmacySpinner);
        quantityEditText = findViewById(R.id.quantityEditText);
        quantityInfoTextView = findViewById(R.id.quantityInfoTextView);
        operationRadioGroup = findViewById(R.id.operationRadioGroup);

        // Заполнение Spinner данными из таблиц "препараты" и "магазины"
        populateSpinner("препараты", medicineSpinner);
        populateSpinner("аптеки", pharmacySpinner);

        // Обработчик события выбора элемента в Spinner для обновления информации о количестве препаратов
        pharmacySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                updateQuantityInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Ничего не делаем в этом случае
            }
        });

        medicineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                updateQuantityInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Ничего не делаем в этом случае
            }
        });

        Button addButton = findViewById(R.id.addInventoryButton);
        addButton.setOnClickListener(view -> addInventory());
    }

    private void populateSpinner(String tableName, Spinner spinner) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        List<String> items = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                if (tableName.equals("препараты")) {
                    String itemName = cursor.getString(cursor.getColumnIndex("название"));
                    items.add(itemName);
                } else {
                    String itemName = cursor.getString(cursor.getColumnIndex("адрес"));
                    items.add(itemName);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateQuantityInfo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectedMedicine = medicineSpinner.getSelectedItem().toString();
        String selectedStore = pharmacySpinner.getSelectedItem().toString();

        Cursor cursor = db.rawQuery("SELECT количество_препаратов FROM учет WHERE название_препарата = ? AND адрес_аптеки = ?",
                new String[]{selectedMedicine, selectedStore});

        int quantity = 0;
        if (cursor.moveToFirst()) {
            quantity = cursor.getInt(cursor.getColumnIndex("количество_препаратов"));
        }

        quantityInfoTextView.setText("Количество препаратов в аптеке: " + quantity);

        cursor.close();
        db.close();
    }

    private void addInventory() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selectedMedicine = medicineSpinner.getSelectedItem().toString();
        String selectedStore = pharmacySpinner.getSelectedItem().toString();
        int quantityChange = Integer.parseInt(quantityEditText.getText().toString());

        // Получаем выбранную радиокнопку
        RadioButton selectedOperationButton = findViewById(operationRadioGroup.getCheckedRadioButtonId());
        String selectedOperation = selectedOperationButton.getText().toString();

        // Проверяем, существует ли запись с выбранным сочетанием препарат-магазин
        Cursor cursor = db.rawQuery("SELECT * FROM учет WHERE название_препарата = ? AND адрес_аптеки = ?",
                new String[]{selectedMedicine, selectedStore});

        if (cursor.moveToFirst()) {
            // Если запись существует, обновляем количество препаратов в ней в зависимости от операции
            int currentQuantity = cursor.getInt(cursor.getColumnIndex("количество_препаратов"));

            if (selectedOperation.equals("Поступление")) {
                currentQuantity += quantityChange;
            } else if (selectedOperation.equals("Продажа")) {
                currentQuantity -= quantityChange;

                if (currentQuantity < 0) {
                    Toast.makeText(this, "Недостаточно препаратов в наличии", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            ContentValues updateValues = new ContentValues();
            updateValues.put("количество_препаратов", currentQuantity);

            int rowsAffected = db.update("учет", updateValues,
                    "название_препарата = ? AND адрес_аптеки = ?",
                    new String[]{selectedMedicine, selectedStore});

            if (rowsAffected > 0) {
                Toast.makeText(this, "Запись в учете обновлена успешно!", Toast.LENGTH_SHORT).show();
                cursor.close();
                db.close();
                finish(); // закрыть активити после добавления
            } else {
                Toast.makeText(this, "Ошибка при обновлении записи в учете", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Если запись не существует, создаем новую запись
            ContentValues insertValues = new ContentValues();

            // Устанавливаем количество препаратов в зависимости от операции
            if (selectedOperation.equals("Поступление")) {
                insertValues.put("название_препарата", selectedMedicine);
                insertValues.put("адрес_аптеки", selectedStore);
                insertValues.put("количество_препаратов", quantityChange);

                long newRowId = db.insert("учет", null, insertValues);

                if (newRowId != -1) {
                    Toast.makeText(this, "Запись в учете добавлена успешно!", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    db.close();
                    finish(); // закрыть активити после добавления
                } else {
                    Toast.makeText(this, "Ошибка при добавлении записи в учет", Toast.LENGTH_SHORT).show();
                }
            } else if (selectedOperation.equals("Продажа")) {
                if (quantityChange < 0) {
                    Toast.makeText(this, "Недостаточно препаратов в наличии", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}
