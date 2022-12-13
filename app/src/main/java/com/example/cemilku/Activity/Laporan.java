package com.example.cemilku.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cemilku.Activity.DetailLaporan;
import com.example.cemilku.Activity.SaveAccount;
import com.example.cemilku.R;

import java.util.Calendar;


public class Laporan extends Fragment {

    DatePickerDialog datePickerDialog;
    EditText et_1, et_2;
    TextView tv_1;
    Button btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laporan, container, false);

        tv_1 = view.findViewById(R.id.laporan_txt_namaUser);
        tv_1.setText(SaveAccount.readDataPembeli(getActivity()).getNama_user());

//        initDatePicker();
//        btn_date1.setText(getTodaysDate());


        et_1 = view.findViewById(R.id.laporan_et_dariTanggal);
        et_1.setInputType(InputType.TYPE_NULL);
        et_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr =Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // Date picker dialog

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        et_1.setText(dayOfMonth+"/"+(monthOfYear + 1 + "/" + year));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        et_2 = view.findViewById(R.id.laporan_et_sampaiTanggal);
        et_2.setInputType(InputType.TYPE_NULL);
        et_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr =Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // Date picker dialog

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        et_2.setText(dayOfMonth+"/"+(monthOfYear + 1 + "/" + year));
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        return view;
    }
}
