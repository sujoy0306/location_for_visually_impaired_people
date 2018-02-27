package com.example.sujoybanik.location;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTTs;
    Button elevator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elevator=(Button) findViewById(R.id.buttonel);
        mTTs=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                    if(status==TextToSpeech.SUCCESS) {
                        int result = mTTs.setLanguage(Locale.ENGLISH);
                        if(result==TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED)
                        {
                            Log.e("TTS","TTs not supported");
                        }
                        else
                        {
                            elevator.setEnabled(true);
                        }
                    }
                    else
                    {
                        Log.e("TTs","Initialization failed");
                    }

            }
        });
        elevator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg="Elevator";
                speak(msg);
            }
        });

    }
    private void speak(String s)
    {
        mTTs.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        if(mTTs!=null)
        {
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
}
