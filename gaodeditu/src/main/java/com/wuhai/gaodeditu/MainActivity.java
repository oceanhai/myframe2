package com.wuhai.gaodeditu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wuhai.gaodeditu.databinding.AcMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AcMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_main);
        binding = DataBindingUtil.setContentView(this,R.layout.ac_main);

        binding.btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
        binding.btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                BasicMapActivity.startActivity(this);
                break;
            case R.id.btn02:
                MarkerActivity.startActivity(this, binding.btn02.getText().toString().trim());
                break;
            case R.id.btn03:
                MarkerActivity.startActivity(this, binding.btn03.getText().toString().trim());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}
