package com.dietrich.multiactivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Tela2 extends AppCompatActivity {
    TextView name;
    TextView media;
    TextView cond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        name = findViewById(R.id.nomeAluno);
        media = findViewById(R.id.mediaAluno);
        cond = findViewById(R.id.condicaoAluno);

        Intent intent = getIntent();
        if(intent != null) {
            name.setText(intent.getStringExtra("nome"));
            media.setText(intent.getStringExtra("media"));
            cond.setText(intent.getStringExtra("condicao"));
        }
    }
}