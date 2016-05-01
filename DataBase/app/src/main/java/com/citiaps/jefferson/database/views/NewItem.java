package com.citiaps.jefferson.database.views;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author:
 */
import com.citiaps.jefferson.database.Actor;
import com.citiaps.jefferson.database.controllers.HttpGet;
import com.citiaps.jefferson.database.controllers.HttpPost;
import com.citiaps.jefferson.database.utilities.JsonHandler;
import com.citiaps.jefferson.database.utilities.SystemUtilities;

import org.json.JSONArray;
import org.json.JSONObject;


import cl.citiaps.jefferson.taller_android_bd.R;


public class NewItem extends Fragment implements OnClickListener {
    View view;
    Button botonOk;
    EditText nombre;
    EditText apellido;
    private final String URL_GET = "http://10.0.2.2:8080/sakila-backend-master5287265178802746903/actors";

    /**
     * Constructor. Obligatorio para Fragmentos!
     */
    public NewItem() {
    }// NewItem()

    /**
     * Método que crea la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.new_item, container, false);
        nombre = (EditText) view.findViewById(R.id.nombre);
        apellido = (EditText) view.findViewById(R.id.apellido);
        botonOk = (Button) view.findViewById(R.id.botonOk);
        botonOk.setOnClickListener(this);
        return view;
    }// onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)


    @Override
    public void onClick(View v) {

        Actor actor = new Actor(nombre.getText().toString(), apellido.getText().toString());
        String actorS = "{\"firstName\":\"" + actor.getNombre() + "\",\"lastName\":\"" + actor.getApellido() + "\"}";//DEBE MEJORARSE ESTA PARRTE

        try {
            SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
            if (su.isNetworkAvailable()) {
                try {
                    AsyncTask resp = new HttpPost(getActivity().getApplicationContext()).execute(actorS,URL_GET);
                    Toast.makeText(getActivity(), "se agregó a "+actor.getNombre(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
        }
    }// NewItem extends Fragment
}