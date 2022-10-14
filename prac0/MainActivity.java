package com.example.sqlitepractice;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase myDB;

    public static void reset() {
        myDB.execSQL("DROP TABLE IF EXISTS 'medicine';");
        myDB.execSQL("CREATE TABLE 'medicine' ('name' text, 'cures' text, 'price' int);");
        myDB.execSQL("INSERT INTO 'medicine' ('name', 'cures', 'price') VALUES ('Antidot', 'poison', '400');");
        myDB.execSQL("INSERT INTO 'medicine' ('name', 'cures', 'price') VALUES ('Painkiller', 'pain', '200');");
        myDB.execSQL("INSERT INTO 'medicine' ('name', 'cures', 'price') VALUES ('Antivenom', 'poison', '150');");
        myDB.execSQL("INSERT INTO 'medicine' ('name', 'cures', 'price') VALUES ('Sedative', 'stress', '300');");
    }

    @SuppressLint("Range")
    public void outDB(){
        Cursor c = myDB.rawQuery("SELECT * FROM 'medicine'", null);
        c.moveToFirst();
        String Data = "";
        while (true) {
            Data = Data + c.getString(c.getColumnIndex("name")) + " - " + c.getString(c.getColumnIndex("cures")) + " - " + c.getInt(c.getColumnIndex("price")) + "\n";
            if (!c.moveToNext()) {
                break;
            }
        }
        TextView tv = new TextView(this);
        tv.setText(Data);
        setContentView(tv);
    }

    @SuppressLint("Range")
    public static CharSequence FindLine(CharSequence a) {
        try {
            Cursor c = myDB.rawQuery("SELECT name FROM medicine WHERE cures = '" + a.toString() + "' ORDER BY price", null);
            c.moveToFirst();
            String Data = "";
            while (true) {
                Cursor cu = myDB.rawQuery("SELECT price FROM medicine WHERE name = '" + c.getString(c.getColumnIndex("name")) + "'", null);
                cu.moveToFirst();
                Data = Data + c.getString(c.getColumnIndex("name")) + " - " + cu.getInt(cu.getColumnIndex("price")) + "\n";
                if (!c.moveToNext()) {
                    return (Data);
                }
            }
        } catch(Exception e) {
            return ("None");
        }
    }

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editText);

        myDB = this.openOrCreateDatabase("test", MODE_PRIVATE, null);
        reset();

        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextView textView = findViewById(R.id.textView);
                textView.setText(FindLine(s));
            }
        });
    }
}