package com.example.gameshop;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.gameshop.databinding.FragmentThirdBinding;
import com.google.android.material.snackbar.Snackbar;

public class ThirdFragment extends Fragment {
    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        binding.buttonThird.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                String name = binding.editName.getText().toString();
                String pass = binding.editPassword.getText().toString();
                try {
                    Cursor c = MainActivity.myDB.rawQuery("SELECT password FROM accounts WHERE name = '" + name + "'", null);
                    Cursor cu = MainActivity.myDB.rawQuery("SELECT premium FROM accounts WHERE name = '" + name + "'", null);
                    c.moveToFirst();
                    cu.moveToFirst();
                    if (c.getString(c.getColumnIndex("password")).equals(pass)) {
                        MainActivity.account_name = name;
                        MainActivity.premium = cu.getString(cu.getColumnIndex("premium")).equals("1");
                        NavHostFragment.findNavController(ThirdFragment.this)
                                .navigate(R.id.action_ThirdFragment_to_FirstFragment);
                    } else {
                        Snackbar.make(view, "Incorrect name or password, try again", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } catch(Exception e) {
                    Snackbar.make(view, "Incorrect name or password, try again", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}