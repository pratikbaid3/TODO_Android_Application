package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{
    EditText txtDate;
    FloatingActionButton btnAddTask;
    private ArrayList<String> mTaskName=new ArrayList<>();
    private ArrayList<String> mTaskDesc=new ArrayList<>();
    private ArrayList<String> mTaskDate=new ArrayList<>();
    private ArrayList<String> mTaskType=new ArrayList<>(); //1=GROUP 0=PERSONAL

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddTask=findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialogue_task);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                txtDate=dialog.findViewById(R.id.input_task_date);
                Button btnSave=dialog.findViewById(R.id.btn_save);
                Button btnCancil=dialog.findViewById(R.id.btn_cancel);
                final EditText taskName=dialog.findViewById(R.id.input_task_name);
                final EditText taskDescription=dialog.findViewById(R.id.input_task_desc);
                final EditText taskDate=dialog.findViewById(R.id.input_task_date);
                final RadioButton personalBtn=dialog.findViewById(R.id.personal);
                final RadioButton groupBtn=dialog.findViewById(R.id.gorup);

                txtDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        showDatePickerDialog();
                    }
                });

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        //TODO Check if the radiobtn is toggled to group or personal
                        if(personalBtn.isChecked())
                        {
                            mTaskType.add("0");
                        }
                        else
                        {
                            mTaskType.add("1");
                        }
                        initTaskData(taskName.getText().toString(),taskDescription.getText().toString(),taskDate.getText().toString());
                        dialog.dismiss();
                    }
                });

                btnCancil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        txtDate.setText(dayOfMonth+"/"+month+"/"+year);
    }

    public void initTaskData(String taskName,String taskDescription, String taskDate)
    {
        mTaskName.add(taskName);
        mTaskDesc.add(taskDescription);
        mTaskDate.add(taskDate);
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        RecyclerView taskRecyclerView=findViewById(R.id.recycler_view_task);
        RecyclerViewAdapter_Task adapter=new RecyclerViewAdapter_Task(MainActivity.this,mTaskName,mTaskDesc,mTaskDate,mTaskType);
        taskRecyclerView.setAdapter(adapter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
