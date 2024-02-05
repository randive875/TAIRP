package com.example.recipeapp.Listeners;

import com.example.recipeapp.Models.InstructionsResponse;

import java.util.List;

public interface InstructionListener {
    void fe(List<InstructionsResponse> response, String message);
    void er(String message);
}
