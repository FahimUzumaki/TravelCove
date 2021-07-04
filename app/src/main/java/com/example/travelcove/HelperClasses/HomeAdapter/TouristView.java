package com.example.travelcove.HelperClasses.HomeAdapter;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travelcove.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class TouristView extends AppCompatActivity {

    ImageView imageView;
    TextView textView, textDesc;
    TouristHelperClass th;
    String placeName;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String description , userID , notImplemented = "Data not implemented yet";
    ToggleButton favouriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_view);

        imageView = findViewById(R.id.imageViewItem2);
        textView = findViewById(R.id.textViewName2);
        textDesc = findViewById(R.id.textViewDesc2);
        favouriteButton = findViewById(R.id.favouriteButton);
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        userID = fAuth.getCurrentUser().getUid();

        Intent intent = getIntent();

        if(intent.getExtras() != null) {
            th = (TouristHelperClass) intent.getSerializableExtra("tourist");

            imageView.setImageResource(th.getImage());
            textView.setText(th.getTitle());
            placeName = th.getTitle();

            add_favourite();
            retrieve_info();
        }

        else Log.i("TAG" , "No intent data found");
    }

    private void retrieve_info() {

        DocumentReference documentReference = fStore.collection("places").document(placeName);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            description = documentSnapshot.getString("info");
                            String str = description.replace("\\n", "\n");

                            String how = "How will you go?", where = "Where will you stay?";
                            String eat = "What will you eat?", perfectTime = "Perfect time to visit:";
                            String sometips = "Some other things you need to know:";
                            int startPos, endPos;
                            SpannableString ss = new SpannableString(str);

                            startPos = str.indexOf(perfectTime);
                            if (startPos >= 0) {

                                endPos = startPos + perfectTime.length();
                                ss.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            startPos = str.indexOf(how);
                            if (startPos >= 0) {

                                endPos = startPos + how.length();
                                ss.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            startPos = str.indexOf(where);
                            if (startPos >= 0) {

                                endPos = startPos + where.length();
                                ss.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            startPos = str.indexOf(eat);
                            if (startPos >= 0) {

                                endPos = startPos + eat.length();
                                ss.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            startPos = str.indexOf(sometips);
                            if (startPos >= 0) {

                                endPos = startPos + sometips.length();
                                ss.setSpan(new StyleSpan(Typeface.BOLD), startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

                            textDesc.setText(ss);

                        }
                        else textDesc.setText(notImplemented);

                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TouristView.this, "Error ! " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void add_favourite() {

        DocumentReference documentReference = fStore.collection("favourites").document(userID);



        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if(documentSnapshot.exists()){


                            if(documentSnapshot.getString(placeName) != null){
                                if(documentSnapshot.getString(placeName).equals("true"))
                                    favouriteButton.setChecked(true);

                            }
                        }
                    }
                });

        favouriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){

                    Map<String , Object> data = new HashMap<>();
                    data.put(placeName , "true");
                    documentReference.set(data , SetOptions.merge());
                }

                else{

                    Map<String , Object> data = new HashMap<>();
                    data.put(placeName , "false");
                    documentReference.set(data , SetOptions.merge());
                }
            }
        });
    }
}