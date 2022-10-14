package com.example.gameshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.gameshop.databinding.FragmentFourthBinding;
import com.google.android.material.snackbar.Snackbar;

public class FourthFragment extends Fragment {
    private FragmentFourthBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FourthFragment.this)
                        .navigate(R.id.action_FourthFragment_to_FirstFragment);
            }
        });

        binding.buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String card = binding.editName.getText().toString();
                if (card.length() == 16) {
                    MainActivity.myDB.execSQL("UPDATE accounts SET premium = '1', card = '" + card + "' WHERE name = '" + MainActivity.account_name + "';");
                    MainActivity.premium = true;
                    NavHostFragment.findNavController(FourthFragment.this)
                            .navigate(R.id.action_FourthFragment_to_FirstFragment);
                } else {
                    Snackbar.make(view, "Incorrect card number, must be 16 digits", Snackbar.LENGTH_LONG)
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
