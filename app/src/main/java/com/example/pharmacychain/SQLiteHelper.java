package com.example.pharmacychain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "your_database.db";
    private static final int DATABASE_VERSION = 1;

    // Конструктор
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод создания базы данных
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы аптеки
        db.execSQL("CREATE TABLE аптеки (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "адрес TEXT UNIQUE, " +
                "телефон TEXT, " +
                "электронная_почта TEXT, " +
                "рабочие_часы TEXT);");

        // Создание таблицы препараты
        db.execSQL("CREATE TABLE препараты (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "название TEXT UNIQUE, " +
                "производитель TEXT, " +
                "тип TEXT, " +
                "цена REAL, " +
                "форма_выпуска TEXT, " +
                "рецептурный INTEGER);");

        // Создание таблицы учет
        db.execSQL("CREATE TABLE учет (" +
                "название_препарата TEXT," +
                "адрес_аптеки TEXT," +
                "количество_препаратов INTEGER," +
                "PRIMARY KEY (название_препарата, адрес_аптеки)," +
                "FOREIGN KEY (название_препарата) REFERENCES препараты(название)," +
                "FOREIGN KEY (адрес_аптеки) REFERENCES аптеки(адрес)" +
                ");");

        // Ваши запросы на добавление данных
        db.execSQL(insertIntoPharmacies);
        db.execSQL(insertIntoMedicines);
        db.execSQL(insertIntoInventory);
    }

    // Метод обновления базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Обновление базы данных при изменении версии
        // (в данном примере - просто удаление и создание заново)
        db.execSQL("DROP TABLE IF EXISTS аптеки");
        db.execSQL("DROP TABLE IF EXISTS препараты");
        db.execSQL("DROP TABLE IF EXISTS учет");
        onCreate(db);
    }

    public String[] getTableNames() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'android_%' AND name NOT LIKE 'sqlite_%'", null);

        List<String> tableNamesList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String tableName = cursor.getString(cursor.getColumnIndex("name"));
                tableNamesList.add(tableName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return tableNamesList.toArray(new String[0]);
    }

    private final String insertIntoPharmacies =
            "INSERT INTO аптеки (адрес, телефон, электронная_почта, рабочие_часы) VALUES " +
                    "('ул. Пушкина, 10', '123-456-7890', 'pushkin@pharmacy.com', '8:00 - 22:00'), " +
                    "('пр. Ленина, 20', '234-567-8901', 'lenin@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Гоголя, 30', '345-678-9012', 'gogol@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Чехова, 40', '456-789-0123', 'chekhov@pharmacy.com', '7:00 - 23:00'), " +
                    "('пр. Мира, 50', '567-890-1234', 'mir@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Садовая, 60', '678-901-2345', 'sad@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Лесная, 70', '789-012-3456', 'les@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Озерная, 80', '890-123-4567', 'lake@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Горная, 90', '901-234-5678', 'mount@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Полевая, 100', '912-345-6789', 'field@pharmacy.com', '7:00 - 23:00'), " +
                    "('пр. Весенний, 110', '923-456-7890', 'spring@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Зимняя, 120', '934-567-8901', 'winter@pharmacy.com', '7:00 - 23:00'), " +
                    "('пр. Осенний, 130', '945-678-9012', 'autumn@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Летняя, 140', '956-789-0123', 'summer@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Заречная, 150', '967-890-1234', 'river@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Южная, 160', '978-901-2345', 'south@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Северная, 170', '989-012-3456', 'north@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Восточная, 180', '990-123-4567', 'east@pharmacy.com', '7:00 - 23:00'), " +
                    "('ул. Западная, 190', '991-234-5678', 'west@pharmacy.com', '8:00 - 22:00'), " +
                    "('ул. Центральная, 200', '992-345-6789', 'center@pharmacy.com', '7:00 - 23:00');";



    private final String insertIntoMedicines =
            "INSERT INTO препараты (название, производитель, тип, цена, форма_выпуска, рецептурный) VALUES " +
                    "('Аспирин', 'Bayer', 'Болеутоляющее', 50.00, 'Таблетки', FALSE), " +
                    "('Парацетамол', 'GSK', 'Жаропонижающее', 30.00, 'Таблетки', FALSE), " +
                    "('Амоксициллин', 'Sandoz', 'Антибиотик', 70.00, 'Капсулы', TRUE), " +
                    "('Ибупрофен', 'Advil', 'Противовоспалительное', 45.00, 'Таблетки', FALSE), " +
                    "('Омепразол', 'Prilosec', 'Противоязвенное', 100.00, 'Капсулы', TRUE), " +
                    "('Лоратадин', 'Claritin', 'Противоаллергическое', 35.00, 'Таблетки', FALSE), " +
                    "('Метформин', 'Glucophage', 'Противодиабетическое', 50.00, 'Таблетки', TRUE), " +
                    "('Атенолол', 'Tenormin', 'Противогипертензивное', 60.00, 'Таблетки', TRUE), " +
                    "('Фуросемид', 'Lasix', 'Мочегонное', 40.00, 'Таблетки', TRUE), " +
                    "('Варфарин', 'Coumadin', 'Антикоагулянт', 80.00, 'Таблетки', TRUE), " +
                    "('Глибенкламид', 'Diabeta', 'Противодиабетическое', 55.00, 'Таблетки', TRUE), " +
                    "('Лизиноприл', 'Prinivil', 'Противогипертензивное', 60.00, 'Таблетки', TRUE), " +
                    "('Левотироксин', 'Synthroid', 'Гормональное', 120.00, 'Таблетки', TRUE), " +
                    "('Симвастатин', 'Zocor', 'Гиполипидемическое', 90.00, 'Таблетки', TRUE), " +
                    "('Азитромицин', 'Zithromax', 'Антибиотик', 80.00, 'Капсулы', TRUE), " +
                    "('Алипразолам', 'Xanax', 'Транквилизатор', 110.00, 'Таблетки', TRUE), " +
                    "('Гидрохлортиазид', 'Microzide', 'Мочегонное', 40.00, 'Таблетки', TRUE), " +
                    "('Цетиризин', 'Zyrtec', 'Противоаллергическое', 35.00, 'Таблетки', FALSE), " +
                    "('Напроксен', 'Aleve', 'Противовоспалительное', 47.00, 'Таблетки', FALSE), " +
                    "('Дексаметазон', 'Decadron', 'Гормональное', 130.00, 'Таблетки', TRUE);";

    private final String insertIntoInventory =
            "INSERT INTO учет (название_препарата, адрес_аптеки, количество_препаратов) VALUES " +
                    "('Аспирин', 'ул. Пушкина, 10', 20), " +
                    "('Парацетамол', 'пр. Ленина, 20', 15), " +
                    "('Амоксициллин', 'ул. Гоголя, 30', 10), " +
                    "('Ибупрофен', 'ул. Чехова, 40', 25), " +
                    "('Омепразол', 'пр. Мира, 50', 30), " +
                    "('Лоратадин', 'ул. Садовая, 60', 18), " +
                    "('Метформин', 'ул. Лесная, 70', 22), " +
                    "('Атенолол', 'ул. Озерная, 80', 12), " +
                    "('Фуросемид', 'ул. Горная, 90', 8), " +
                    "('Варфарин', 'ул. Полевая, 100', 16), " +
                    "('Глибенкламид', 'пр. Весенний, 110', 14), " +
                    "('Лизиноприл', 'ул. Зимняя, 120', 20), " +
                    "('Левотироксин', 'пр. Осенний, 130', 9), " +
                    "('Симвастатин', 'ул. Летняя, 140', 30), " +
                    "('Азитромицин', 'ул. Заречная, 150', 15), " +
                    "('Алипразолам', 'ул. Южная, 160', 10), " +
                    "('Гидрохлортиазид', 'ул. Северная, 170', 20), " +
                    "('Цетиризин', 'ул. Восточная, 180', 25), " +
                    "('Напроксен', 'ул. Западная, 190', 12), " +
                    "('Дексаметазон', 'ул. Центральная, 200', 14);";

}
