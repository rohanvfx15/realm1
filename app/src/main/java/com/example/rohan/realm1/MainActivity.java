package com.example.rohan.realm1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rohan.realm1.model.SocailAccount;
import com.example.rohan.realm1.model.User;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

public class MainActivity extends AppCompatActivity {


    private EditText name, age, socialac, status;
    private Realm myRealm;
    private RealmAsyncTask realmAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameid);
        age = (EditText) findViewById(R.id.ageid);
        socialac = (EditText) findViewById(R.id.socialacid);
        status = (EditText) findViewById(R.id.statusid);

        myRealm = Realm.getDefaultInstance();

    }

    public void addmaintread(View view) {

        final String id = UUID.randomUUID().toString();
        final String nameget = name.getText().toString();
        final int agetget = Integer.valueOf(age.getText().toString());
        final String socailacget = socialac.getText().toString();
        final String statusget = status.getText().toString();


        // first way
     /*   try {
            myRealm.beginTransaction();
            SocailAccount socailAccount = myRealm.createObject(SocailAccount.class);
            socailAccount.setName(socailacget);
            socailAccount.setStatus(statusget);

            User user = myRealm.createObject(User.class, id);

            user.setName(nameget);
            user.setAge(agetget);
            user.setSocailAccount(socailAccount);
            myRealm.commitTransaction();
        }catch (Exception e){
            myRealm.cancelTransaction();
        }*/


    // second way
        myRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SocailAccount socailAccount = realm.createObject(SocailAccount.class);
                socailAccount.setName(socailacget);
                socailAccount.setStatus(statusget);

                User user = realm.createObject(User.class, id);

                user.setName(nameget);
                user.setAge(agetget);
                user.setSocailAccount(socailAccount);
                Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // add data backroudn thread
    public void addbackgroundtread(View view) {

        final String id = UUID.randomUUID().toString();
        final String nameget = name.getText().toString();
        final int agetget = Integer.valueOf(age.getText().toString());
        final String socailacget = socialac.getText().toString();
        final String statusget = status.getText().toString();




        realmAsyncTask = myRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                SocailAccount socailAccount = realm.createObject(SocailAccount.class);
                socailAccount.setName(socailacget);
                socailAccount.setStatus(statusget);

                User user = realm.createObject(User.class, id);

                user.setName(nameget);
                user.setAge(agetget);
                user.setSocailAccount(socailAccount);


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(MainActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    };



    public void displayall(View view) {
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (realmAsyncTask != null && !realmAsyncTask.isCancelled()){
            realmAsyncTask.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRealm.close();
    }
}
