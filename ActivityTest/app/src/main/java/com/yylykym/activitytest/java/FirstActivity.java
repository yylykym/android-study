package com.yylykym.activitytest.java;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yylykym.activitytest.R;
import com.yylykym.activitytest.SecondActivity;
import com.yylykym.activitytest.databinding.FirstLayoutBinding;

import java.util.Objects;

public class FirstActivity extends AppCompatActivity {

    private FirstLayoutBinding firstLayoutBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstLayoutBinding = FirstLayoutBinding.inflate(getLayoutInflater());
        setContentView(firstLayoutBinding.getRoot());

        // 注册回调
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Log.d("FirstActivity", "returned data is " + Objects.requireNonNull(result.getData()).getStringExtra("data_return"));
            }
        });

        firstLayoutBinding.button1.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            var data = "Hello SecondActivity";
            intent.putExtra("extra_data", data);
            launcher.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_item) {
            Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
        }

        /*
          Resource IDs will be non-final by default in Android Gradle Plugin version 8.0,
          avoid using them in switch case statements
          可以在 gradle.properties里添加 android.nonFinalResIds=false 取消这种构建方式
         */

//        switch (item.getItemId()) {
//            case R.id.add_item ->
//                // 处理 "Add" 菜单项的点击事件
//                    Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
//            case R.id.remove_item ->
//                // 处理 "Remove" 菜单项的点击事件
//                    Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
//        }
        return true;
    }
}
