package com.example.yam.projectcustomalertdialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    AlertDialog.Builder adb;
    AlertDialog ad1;
    ArrayAdapter<Double> adp;
    Switch sc;
    int j = 0;
    String st1, st2;
    Intent t1;
    TextView tv1, tv2, tv3, tv4;
    double num, d, sum, num2;
    EditText et1, et2;
    ListView lv1;
    Double[] arr1;
    LinearLayout layout_dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv1);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        arr1 = new Double[20];
        lv1.setOnItemClickListener(this);
        lv1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


    }

    private void setArr1() {
        adp = new ArrayAdapter<Double>(this, R.layout.support_simple_spinner_dropdown_item, arr1);
        lv1.setAdapter(adp);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        t1 = new Intent(this, Main2Activity.class);
        startActivity(t1);
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void go(View view) {

        layout_dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_dialog, null);
        sc = (Switch) layout_dialog.findViewById(R.id.sc);
        et1 = (EditText) layout_dialog.findViewById(R.id.first);
        et2 = (EditText) layout_dialog.findViewById(R.id.multi);
        adb = new AlertDialog.Builder(this);
        adb.setView(layout_dialog);
        adb.setTitle("enter the data");
        adb.setPositiveButton("Enter", click);
        adb.setNegativeButton("Reset", click);
        adb.setNeutralButton("Cancel", click);
        ad1 = adb.create();
        ad1.show();

    }

    DialogInterface.OnClickListener click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            st1 = et1.getText().toString();
            st2 = et2.getText().toString();

            if (which == DialogInterface.BUTTON_POSITIVE) {
                if ((st1.equals(".")) || (st2.equals(".")) || (st1.isEmpty()) || (st2.isEmpty()) || (st1.equals("-")) || (st2.equals("-")) || (st1.equals("-.")) || (st2.equals("-.")))
                    Toast.makeText(MainActivity.this, "You must enter appropriate text", Toast.LENGTH_SHORT);

                else {
                    if (sc.isChecked()) {

                        num = Double.parseDouble(st1);
                        d = Double.parseDouble(st2);
                        arr1[0] = num;
                        num2 = num;
                        for (int j = 1; j < 20; j++) {
                            arr1[j] = num2 * d;
                            num2 = num2 * d;

                        }


                    } else {

                        num = Double.parseDouble(st1);
                        d = Double.parseDouble(st2);
                        arr1[0] = num;
                        num2 = num;
                        for (int j = 1; j < 20; j++) {
                            arr1[j] = num2 + d;
                            num2 = num2 + d;

                        }
                    }

                    setArr1();

                }
            }
            if (which == DialogInterface.BUTTON_NEUTRAL) {
                ad1.cancel();
            }

            if (which == DialogInterface.BUTTON_NEGATIVE) {
                num = 0;
                d = 0;
                for (int j = 0; j < 20; j++) {
                    arr1[j] = 0.0;
                }
                setArr1();
            }
        }
    };




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tv1.setText("X1= " + num);
        tv2.setText("d= " + d);
        tv3.setText("n= " + (position+1));
        sum = 0;
        for (j = 0; j<=position; j++){
            sum = arr1[j] + sum;
        }

        tv4.setText("Sn= " + sum);

    }
}
