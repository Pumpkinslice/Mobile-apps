package com.example.gameshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.gameshop.databinding.FragmentFifthBinding;

public class FifthFragment extends Fragment {
    private FragmentFifthBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFifthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!MainActivity.premium) {
            binding.buttonGog.setEnabled(false);
        }

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FifthFragment.this)
                        .navigate(R.id.action_FifthFragment_to_FirstFragment);
            }
        });

        binding.buttonSteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com/search/?term=" + binding.editName.getText().toString()));
                startActivity(browserIntent);
            }
        });

        binding.buttonEpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.epicgames.com/eu/browse?q=" + binding.editName.getText().toString() + "&sortBy=relevancy&sortDir=DESC&count=40"));
                startActivity(browserIntent);
            }
        });

        binding.buttonGog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gog.com/ru/games?query=" + binding.editName.getText().toString() + "&order=desc:score"));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
