package com.example.textvieweditor;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnTextEditorDialog,btnCancel,btnSubmit;
    EditText edtString;
    RadioButton radioButtonUppercase,radioButtonLowercase,radioButtonInitcaps;
    CheckBox checkBoxReverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListners();

    }
    private void initViews(){
        btnTextEditorDialog = findViewById(R.id.btnTextEditorDialog);
    }
    private void initListners(){
        btnTextEditorDialog.setOnClickListener(new btnTextEditorDialogListner());
    }
    class btnTextEditorDialogListner implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Dialog textEditorDialog  = new Dialog(MainActivity.this);
            textEditorDialog.setTitle("Text Editor");
            textEditorDialog.setContentView(R.layout.text_editor_dialog);
            edtString = textEditorDialog.findViewById(R.id.edtString);
            radioButtonUppercase = textEditorDialog.findViewById(R.id.radioBtnUppercase);
            radioButtonLowercase = textEditorDialog.findViewById(R.id.radioBtnLowerCase);
            radioButtonInitcaps = textEditorDialog.findViewById(R.id.radioBtnInitCap);
            checkBoxReverse = textEditorDialog.findViewById(R.id.checkBoxReverse);
            btnSubmit = textEditorDialog.findViewById(R.id.btnSubmit);
            btnCancel = textEditorDialog.findViewById(R.id.btnCancel);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String userString = edtString.getText().toString();
                    if (checkBoxReverse.isChecked()) {
                        if (radioButtonUppercase.isChecked()) {
                            userString = userString.toUpperCase();
                        } else if (radioButtonLowercase.isChecked()) {
                            userString = userString.toLowerCase();
                        } else if (radioButtonInitcaps.isChecked() && !userString.isEmpty()) {
                            userString = userString.substring(0, 1).toUpperCase() + userString.substring(1).toLowerCase();
                        }

                        StringBuilder reverseString = new StringBuilder();
                        for (int i = userString.length() - 1; i >= 0; i--) {
                            reverseString.append(userString.charAt(i));
                        }
                        btnTextEditorDialog.setText(reverseString.toString());

                    } else {
                        if (radioButtonUppercase.isChecked()) {
                            userString = userString.toUpperCase();
                        } else if (radioButtonLowercase.isChecked()) {
                            userString = userString.toLowerCase();
                        } else if (radioButtonInitcaps.isChecked() && !userString.isEmpty()) {
                            userString = userString.substring(0, 1).toUpperCase() + userString.substring(1).toLowerCase();
                        }
                        btnTextEditorDialog.setText(userString);
                    }

                    textEditorDialog.dismiss();
                }
            });

            textEditorDialog.show();

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textEditorDialog.dismiss();
                }
            });
        }
    }
}