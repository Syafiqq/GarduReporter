package app.freelancer.syafiqq.gardureporter.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import app.freelancer.syafiqq.gardureporter.R;
import com.fenjuly.mylibrary.ToggleExpandLayout;

public class GarduIndexMeasurement extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardu_index_measurement);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ToggleExpandLayout layout = (ToggleExpandLayout) findViewById(R.id.toogleLayout);
        Switch switchButton = (Switch) findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked)
                {
                    layout.open();
                }
                else
                {
                    layout.close();
                }
            }
        });

    }

}
