package com.example.ebooks_admin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class AdminMenu extends AppCompatActivity {

    //background Animation
    CoordinatorLayout coordinatorLayout;
    AnimationDrawable animationDrawable ;

    private Button Add, Logout, Delete;

    //firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        //For Add Button
        Add = (Button) findViewById(R.id.sltadd);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDetails();
            }
        });

        //For Logout Button
        Logout = (Button) findViewById(R.id.sltlogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                finish();
                openAdminPanel();
            }
        });

        //For Delete Button
        Delete = (Button) findViewById(R.id.sltdelete);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteDetails();
            }
        });


        //background Animation
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.menuLayout);
        animationDrawable = (AnimationDrawable) coordinatorLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

    }

    //add function
    public void openAddDetails(){
        Intent intent = new Intent(this, AdminAddDetails.class);
        startActivity(intent);
    }

    //logout function
    public void openAdminPanel(){
        deleteCache(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //delete function
    public void openDeleteDetails(){
        Intent intent = new Intent(this, AdminDeleteDetails.class);
        startActivity(intent);
    }


    //clear app cache
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
