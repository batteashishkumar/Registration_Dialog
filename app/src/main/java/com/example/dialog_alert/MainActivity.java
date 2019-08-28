package com.example.dialog_alert;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
EditText name,phone,email,password;
Button btn;
RadioGroup radio;
int a,b,c,d,e;
StringBuilder s;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s=new StringBuilder();
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn=findViewById(R.id.btn);
        radio= findViewById(R.id.radio);
        alertDialog  = new AlertDialog.Builder(MainActivity.this).create();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkname();
                checkphone();
                checkemail();
                checkpassword();
                checkradio();
                validate();
            }
        });
    }

    private void validate() {
        if(a==1&b==1&c==1&d==1&e==1)
        {
            alertDialog.setTitle("CONGRATS");
            alertDialog.setMessage("you are genius , registration successful");
            alertDialog.show();
        }
        else{
            alertDialog.setTitle("ERROR");
            alertDialog.setMessage(""+s);
            alertDialog.show();
            int length=s.length();
            s.delete(0,length-1);

        }
    }


    private void checkradio() {
        if(radio.getCheckedRadioButtonId() == -1)
        {
            s.append("-check radio\n");
            a=0;
        }
        else
        {
a=1;
        }
    }

    private void checkpassword() {
       String pass= password.getText().toString();
       if(isValidPassword(pass))
       {b=1;}
       else {
           s.append("-password should be 8 lenght contain alphabet small & caps,symbol,number\n");
           password.setText("");
           b=0;
       }

    }

    private void checkphone() {
        String pho=phone.getText().toString();
        if(TextUtils.isEmpty(pho))
        {
            s.append("-enter phone number\n");
            c=0;
        }
       else if(pho.length()<10){
            s.append("-number should be 10 digits\n");
            c=0;
        }
       else{
c=1;
        }

        
    }

    private void checkemail() {
        String ema= email.getText().toString();
        if(isValidEmail(ema))
        {
            d=1;
        }
        else {
            s.append("-email is invalid\n");
            email.setText("");
            d=0;
        }


        
    }

    private void checkname() {
        String nam=name.getText().toString();
        if(TextUtils.isEmpty(nam))
        {
            s.append("-enter name\n");
            e=0;
        }
        else if(nam.length()<5){
            s.append("-name should be atleast 5 chacters\n");
            e=0;
        }
        else{
            e=1;

        }
        
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();


        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked)
                   
                    break;
            case R.id.radio_ninjas:
                if (checked)
                   
                    break;
        }
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
    public static boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
