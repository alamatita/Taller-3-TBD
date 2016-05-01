package com.citiaps.jefferson.database.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cl.citiaps.jefferson.taller_android_bd.R;

import com.citiaps.jefferson.database.Actor;
import com.citiaps.jefferson.database.controllers.HttpGet;
import com.citiaps.jefferson.database.utilities.JsonHandler;
import com.citiaps.jefferson.database.utilities.SystemUtilities;

/**
 * @author: Jefferson Morales De la Parra
 * Clase Fragmento (Lista) que se utiliza para mostrar una lista de items
 */
public class ItemList extends ListFragment {

    private BroadcastReceiver br = null;
    private final String URL_GET = "http://10.0.2.2:8080/sakila-backend-master5287265178802746903/actors";
    private Actor[] actorsList;

    /**
     * Constructor. Obligatorio para Fragmentos!
     */
    public ItemList() {
    }// ItemList()

    /**
     * Método que se llama una vez que se ha creado la actividad que contiene al fragmento
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }// onActivityCreated(Bundle savedInstanceState)

    /**
     * Método que escucha las pulsaciones en los items de la lista
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Actor actor=actorsList[position];
        String item = "Nombre: "+ actor.getNombre()+"\nApellido: "+actor.getApellido()+"\nActualización: "+actor.getLastUpdate()+"\nId Actor: "+actor.getActorId()+".";
        Fragment itemDetail = new ItemDetail();
        Bundle arguments = new Bundle();
        arguments.putString("item", item);
        itemDetail.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, itemDetail);
        transaction.addToBackStack(null);
        transaction.commit();
    }// onListItemClick(ListView l, View v, int position, long id)

    /**
     * Método que se ejecuta luego que el fragmento es creado o restaurado
     */
    @Override
    public void onResume() {
        IntentFilter intentFilter = new IntentFilter("httpData");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                JsonHandler jh = new JsonHandler();
                actorsList = jh.getActors(intent.getStringExtra("data"));
                String[] actorsStr = new String[actorsList.length];
                for (int i=0;i<actorsList.length;i++){
                    actorsStr[i]=" "+actorsList[i].getNombre()+" "+actorsList[i].getApellido();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity()
                        , android.R.layout.simple_list_item_1, actorsStr);
                setListAdapter(adapter);
            }
        };
        getActivity().registerReceiver(br, intentFilter);
        SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
        if (su.isNetworkAvailable()) {
            try {
                new HttpGet(getActivity().getApplicationContext()).execute(URL_GET);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onResume();
    }// onResume()

    /**
     * Método que se ejecuta luego que el fragmento se detiene
     */
    @Override
    public void onPause() {
        if (br != null) {
            getActivity().unregisterReceiver(br);
        }
        super.onPause();
    }// onPause()

}// ItemList extends ListFragment