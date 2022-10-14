package com.example.gameshop;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.gameshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static String account_name;
    public static Boolean premium = false;
    public static SQLiteDatabase myDB;

    public static void reset() {
        myDB.execSQL("DROP TABLE IF EXISTS 'accounts';");
        myDB.execSQL("CREATE TABLE 'accounts' ('name' text, 'password' text, 'premium' bool, 'card' text, 'addacc' text);");
        myDB.execSQL("INSERT INTO 'accounts' ('name', 'password', 'premium', 'card', 'addacc') VALUES ('me', '123', '1', '1111222233334444', 'me@mail.ru');");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = this.openOrCreateDatabase("AccountInfo", MODE_PRIVATE, null);
        //reset();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    }
}