package com.example.dongguk;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
Button button_module1, button_module2;
    int button_location = 0;
    static int past_state1 = 0, past_state2 = 0;
    private DatabaseReference database;   // 파이어베이스 데이터베이스 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        button_location = intent.getIntExtra("but", 3);  // 전 화면의 but을 통해 몇번 위치가 눌렸는지 판단
        past_state1 = intent.getIntExtra("rms1", 3);     // 받아온 과거 상태값
        past_state2 = intent.getIntExtra("rms2", 3);
        database = FirebaseDatabase.getInstance().getReference();
        button_module1 = findViewById(R.id.btnx_1);
        button_module2 = findViewById(R.id.btnx_2);

        button_module1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                if (button_location == 1) {
                    database.child("state_1").setValue(1);           // 파이어베이스 서버에 state 값을 보내줌
                    database.child("state_2").setValue(past_state2); // 선택되지 않은 위치에서는 화면전환 했을 때 받아온 state값을 전송
                } else if (button_location == 2) {
                    database.child("state_1").setValue(past_state1);
                    database.child("state_2").setValue(1);
                }
                startActivity(intent);
            }
        });

        button_module2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                if (button_location == 1) {
                    database.child("state_1").setValue(2);
                    database.child("state_2").setValue(past_state2);
                } else if (button_location == 2) {
                    database.child("state_1").setValue(past_state1);
                    database.child("state_2").setValue(2);
                }
                startActivity(intent);
            }
        });
    }
}