package com.example.guide.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DialogInfo extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("О приложении").setIcon(android.R.drawable.ic_dialog_info).setMessage("Мобильный путеводитель сделал для вас grankin52w@mail.ru").setPositiveButton("ОК", null).create();
    }
}

