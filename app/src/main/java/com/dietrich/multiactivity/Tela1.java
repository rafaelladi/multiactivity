package com.dietrich.multiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Tela1 extends AppCompatActivity {
    EditText nome;
    EditText nota1;
    EditText nota2;
    EditText freq;
    Button button;

    static class InputFilter implements android.text.InputFilter {
        private final int min;
        private final int max;

        public InputFilter(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public CharSequence filter(CharSequence source, int i, int i1, Spanned spanned, int i2, int i3) {
            try {
                float input = Float.parseFloat(spanned.toString() + source.toString());
                if(isInRange(input))
                    return null;
            } catch(NumberFormatException ignored) {}
            return "";
        }

        private boolean isInRange(float input) {
            return input <=max && input >= min;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);

        nome = findViewById(R.id.nome);
        nota1 = findViewById(R.id.nota1);
        nota2 = findViewById(R.id.nota2);
        freq = findViewById(R.id.freq);
        button = findViewById(R.id.submitButton);

        nota1.setFilters(new InputFilter[] {new InputFilter(0, 10)});
        nota2.setFilters(new InputFilter[] {new InputFilter(0, 10)});
        freq.setFilters(new InputFilter[] {new InputFilter(0, 100)});

        button.setOnClickListener(view -> {
            Intent intent = new Intent(this, Tela2.class);
            Bundle params = new Bundle();

            if(nome.getText().toString().isEmpty() ||
                    nota1.getText().toString().isEmpty() ||
                    nota2.getText().toString().isEmpty() ||
                    freq.getText().toString().isEmpty())
                return;

            float n1 = Float.parseFloat(nota1.getText().toString());
            float n2 = Float.parseFloat(nota2.getText().toString());
            int f = Integer.parseInt(freq.getText().toString());
            float media = (n1 + n2) / 2;

            params.putString("nome", nome.getText().toString());
            params.putString("media", String.valueOf(media));
            params.putString("condicao", getCondicao(media, f));

            intent.putExtras(params);
            startActivity(intent);
        });
    }

    private String getCondicao(float media, float f) {
        if(f < 75)
            return "Reprovado por falta";

        if(media < 4)
            return "Reprovado por nota";

        if(media < 7)
            return "Final";

        return "Aprovado";
    }
}