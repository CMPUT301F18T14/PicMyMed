package com.example.mukha.picmymedcode.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mukha.picmymedcode.Model.Problem;
import com.example.mukha.picmymedcode.Model.ProblemList;
import com.example.mukha.picmymedcode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class ProblemActivity extends AppCompatActivity {
    private static final String FILENAME = "file2.sav";
    public Date date;
    private RecyclerView mRecyclerView;
    private ProblemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    private View.OnClickListener mListener;
    public ArrayList<Problem> problemArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_activity);
<<<<<<< HEAD:app/src/main/java/com/example/mukha/picmymedcode/View/ProblemActivity.java

        manageRecyclerview();


        Button addproblembutton = findViewById(R.id.problem_save_button);
=======
        Toolbar topToolbar = (Toolbar) findViewById(R.id.problemTop_toolbar);
        setSupportActionBar(topToolbar);
        ActionMenuView bottomToolbar = (ActionMenuView)findViewById(R.id.problemBottom_toolbar);
        Menu bottomMenu = bottomToolbar.getMenu();

        mRecyclerView = findViewById(R.id.problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(problemList.getProblemList());
        mRecyclerView.setAdapter(mAdapter);





/**
        Button addproblembutton = findViewById(R.id.add_problem_button);
>>>>>>> development:app/src/main/java/com/example/mukha/picmymedcode/View/ProblemActivity.java
        addproblembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent problemIntent = new Intent(ProblemActivity.this,AddProblemActivity.class);
                startActivity(problemIntent);
            }
        }); **/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.problem_top_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_addProblem:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent problemIntent = new Intent(ProblemActivity.this,AddProblemActivity.class);
                startActivity(problemIntent);

            case R.id.action_viewProfile:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent profileIntent = new Intent(ProblemActivity.this, ProfileActivity.class);
                startActivity(profileIntent);

<<<<<<< HEAD:app/src/main/java/com/example/mukha/picmymedcode/View/ProblemActivity.java


    }


    public void manageRecyclerview(){
        //to clear my file
        //problemArrayList.clear();
        //saveInFile();
        mRecyclerView = findViewById(R.id.problem_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new ProblemAdapter(ProblemActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);
=======
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
>>>>>>> development:app/src/main/java/com/example/mukha/picmymedcode/View/ProblemActivity.java
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        mAdapter = new ProblemAdapter(ProblemActivity.this, problemArrayList);
        mRecyclerView.setAdapter(mAdapter);


    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type typeListProblem = new TypeToken<ArrayList<Problem>>() {
            }.getType();
            problemArrayList = gson.fromJson(reader, typeListProblem);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);

            Gson gson = new Gson();
            gson.toJson(problemArrayList,osw);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
