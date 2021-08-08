package com.example.android_project_12_realmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<Realm> {

    private ProgressBar prg_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Realm realm = Realm.getDefaultInstance();
        //Log.i("myRealm", realm.getConfiguration().getRealmFileName());

        /*Note note = new Note();
        note.setNoteId(1);
        note.setTitle("note 1");
        note.setDate("may 16");
        note.setNote("this is note");
        note.setTime("12 : 25");

        realm.beginTransaction();
        realm.copyFromRealm(note);
        realm.commitTransaction();*/

        //Note note = realm.where(Note.class).findFirst();
        //Log.i("mySql", note == null ? "note is not exists" : "title is " + note.getTitle());

        /*RealmResults<Note> notes = realm.where(Note.class).findAll();

        for(Note note : notes){
            Log.i("mySql", note == null ? "note is not exists" : "title is " + note.getTitle());
        }*/

        //Note note = realm.where(Note.class).equalTo("noteId", 2).findFirst();

        /*RealmResults<Note> notes = realm.where(Note.class)
                .equalTo("data", "may 6")
                .or()
                .equalTo("noteId", 1)
                .findAll();
        for (Note note : notes) {
            Log.i("mySql", note == null ? "note is not exists" : "title is " + note.getTitle());
        }*/
        /*realm.beginTransaction();
        RealmResults<Note> notes = realm.where(Note.class).findAll();
        notes.get(0).deleteFromRealm();
        realm.commitTransaction();*/

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                prg_loading.setVisibility(View.VISIBLE);
                for (int i = 0; i < 1000; i++) {
                    Note note = new Note();
                    note.setNoteId(i);
                    note.setTitle("note " + i);
                    note.setDate("may 16");
                    note.setNote("this is note " + i);
                    note.setTime("12 : 25");
                    realm.copyToRealmOrUpdate(note);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                prg_loading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                prg_loading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        prg_loading = findViewById(R.id.prg_loading);
    }

    @Override
    public void onChange(Realm realm) {
        Toast.makeText(this, "onChange", Toast.LENGTH_SHORT).show();
    }
}