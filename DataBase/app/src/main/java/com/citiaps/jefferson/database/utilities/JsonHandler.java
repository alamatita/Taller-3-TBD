package com.citiaps.jefferson.database.utilities;

import android.util.Log;

import com.citiaps.jefferson.database.Actor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author: Jefferson Morales De la Parra
 * Clase que se utiliza para manipular objetos JSON
 */
public class JsonHandler {

    /**
     * Método que recibe un JSONArray en forma de String y devuelve un String[] con los actores
     */
    public Actor[] getActors(String actors) {
        try {
            JSONArray ja = new JSONArray(actors);
            Actor[] result = new Actor[ja.length()];
            Actor actor;
            for (int i = 0; i < ja.length(); i++) {
                JSONObject row = ja.getJSONObject(i);
                actor = new Actor(row.getString("firstName"), row.getString("lastName"),row.getString("lastUpdate"), row.getString("actorId"));
                result[i] = actor;
            }
            return result;
        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// getActors(String actors)


    public JSONArray getJsonActor(Actor actor) {
        try {
            JSONArray ja = new JSONArray().put(actor);
            return ja;
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }// getActors(String actors)

}// JsonHandler