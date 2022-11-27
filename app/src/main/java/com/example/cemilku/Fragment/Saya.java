package com.example.cemilku.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cemilku.Activity.AkunActivity;
import com.example.cemilku.Activity.Login;
import com.example.cemilku.Activity.SaveAccount;
import com.example.cemilku.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Saya#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Saya extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View nullic, nullhead1;
    private AppCompatButton nulbtyes, nullbtno;

    public Saya() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Saya.
     */
    // TODO: Rename and change types and number of parameters
    public static Saya newInstance(String param1, String param2) {
        Saya fragment = new Saya();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    //@SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saya, container, false);
        TextView nama1, nama2;
        AppCompatButton btn1, btn2, btn3;

        //Memanggil nama dan email
        nama1 = view.findViewById(R.id.saya_username);
        nama1.setText(SaveAccount.readDataPembeli(getActivity()).getNama_user());

        nama2 = view.findViewById(R.id.saya_email);
        nama2.setText(SaveAccount.readDataPembeli(getActivity()).getEmail());

        btn1 = (AppCompatButton) view.findViewById(R.id.saya_btnAkun);
        btn2= (AppCompatButton) view.findViewById(R.id.saya_btnAbout);
        btn3 = (AppCompatButton) view.findViewById(R.id.saya_btnLogout);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AkunActivity.class));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertLogOut();
            }

        }); return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_saya, container, false);


    }

    //AlertLogout
    @SuppressLint({"ResourceType", "MissingInflatedId"})
    private void alertLogOut() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.activity_logout, null);
        alertDialogBuilder.setView(view);
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
        nullic = view.findViewById(R.drawable.logoutilustrasi);
        nullhead1 = view.findViewById(R.id.logout_txt);
        nulbtyes = view.findViewById(R.id.btn_yes);
        nulbtyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = getActivity().getSharedPreferences("myapp-data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("status", false);
                editor.putString("email", null);
                editor.commit();
                startActivity(new Intent(getActivity(), Login.class));
                Toast.makeText(getActivity(), "Berhasil Logout", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        nullbtno = view.findViewById(R.id.btn_no);
        nullbtno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });
    }

}