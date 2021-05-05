package com.example.recyclerview_3;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.CookieHandler;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public  class MainActivity extends AppCompatActivity  {
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    String[] Bloodgroup={"O+ve", "O-ve", "A+ve", "A-ve","B+ve","B-ve","AB+ve","AB-ve"};
    ArrayList<ModelClass> data ;
    private CookieHandler future;
    private Button button;
    View.OnClickListener L;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        Spinner spin = findViewById(R.id.simpleSpinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Bloodgroup);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setPrompt("Bloodgroup");
        spin.setAdapter(aa);
        data = new ArrayList<>();
        L= new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(view.getContext())
                        .setTitle("Confirm")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String[] Tags=(String[])view.getTag();
                                String BG=Tags[1] ;
                                String name=Tags[0] ;
                             Date currentDate=new Date();

                                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                                String Currentdate=dateFormat.format(currentDate);

                                firebaseFirestore = FirebaseFirestore.getInstance();
                               firebaseFirestore.collection("/Blood Database/" + BG + "/people").document(name)
                                        .update("date",Currentdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       Toast.makeText(getApplicationContext(),  "success",Toast.LENGTH_LONG);
                                   }
                               }).addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                       Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG);
                                   }
                               });


                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        };

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String g = Bloodgroup[i];
                data.clear();
                firebaseFirestore.collection("/Blood Database/" + g + "/people").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot record : queryDocumentSnapshots) {
                            data.add(new ModelClass(
                                    Integer.parseInt(Objects.requireNonNull(record.get("sno")).toString()),
                                    Objects.requireNonNull(record.get("BG")).toString(),
                                    Objects.requireNonNull(record.get("name")).toString(),
                                    Long.parseLong(Objects.requireNonNull(record.get("phone_no")).toString()),
                                    Objects.requireNonNull(record.get("date")).toString(),
                                    Objects.requireNonNull(record.get("address")).toString(),
                                    Objects.requireNonNull(record.getId())
                            ));
                        }
                        adapter = new CustomAdapter();
                        ArrayList<ModelClass> filtered_data = new ArrayList<>();
                        Calendar Now = Calendar.getInstance();

                        DateFormat DF = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
                        for (ModelClass donar : data) {
                            try {
                                Date date = DF.parse(donar.getDate());
                                Calendar LastDate = Calendar.getInstance();
                                assert date != null;
                                LastDate.setTime(date);
                                LastDate.add(Calendar.MONTH, 3);
                                Log.d("Date", LastDate.toString());
                                if (LastDate.before(Now)) {
                                    filtered_data.add(donar);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        filtered_data.sort(Comparator.comparing(ModelClass::getDate));
                        adapter.data = filtered_data;

                        adapter.L=L;
                        recyclerView.setAdapter(adapter);
                    }
                }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });






    }


    @Override
    protected void onStop() {
        super.onStop();
        //  adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // adapter.startListening();
    }
}

