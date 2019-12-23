package com.wuhai.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.wuhai.mvvm.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn01;
    private EditText name;
    private EditText addr;
    private ActivityMainBinding binding;

    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layouts.ac_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mStudent = new Student("路飞","海贼王","https://www.baidu.com/img/bd_logo1.png");
        binding.setStu(mStudent);//model和布局绑定

        User user = new User();
        user.firstName.set("google");
        user.lastName.set("vivo");
        user.age.set(18);
        binding.setUser(user);

        //取布局id 方式1
//        View view = binding.getRoot();
//        btn01 = (Button) view.findViewById(R.id.btn01);
//        name = (EditText) view.findViewById(R.id.name);
//        addr = (EditText) view.findViewById(R.id.addr);

        //取布局id 方式2
        btn01 = binding.btn01;
        name = binding.name;
        addr = binding.addr;

        btn01.setOnClickListener(this);
        binding.btn02.setOnClickListener(this);
        binding.btn03.setOnClickListener(this);
        binding.btn04.setOnClickListener(this);
        binding.list2.setOnClickListener(this);
        binding.changUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                String nameStr = name.getText().toString().trim();
                String addrStr = addr.getText().toString().trim();

                //示例 不做效果展示了，这里只是重新绑定model而已
//                binding.setStu(new Student(nameStr,addrStr));

                //示例2
                Student student = binding.getStu();
                student.setName(nameStr);
                student.setAddr(addrStr);
                break;
            case R.id.chang_user:
                String firstNameStr = name.getText().toString().trim();
                String lastNameStr = addr.getText().toString().trim();

                User user = binding.getUser();
                user.firstName.set(firstNameStr);
                user.lastName.set(lastNameStr);
                break;
            case R.id.btn02:
                ListActivity.startActivity(this);
                break;
            case R.id.list2:
                List2Activity.startActivity(this);
                break;
            case R.id.btn03:
                binding.nameTv.setText("宋阿呆");
                Toast.makeText(this, binding.getStu().getName(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn04:
                Toast.makeText(this, binding.getStu().getName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
