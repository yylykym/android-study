package com.yylykym.activitytest.java;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;

import com.yylykym.activitytest.databinding.SecondLayoutBinding;

public class SecondActivity extends AppCompatActivity {

    private SecondLayoutBinding secondLayoutBinding;

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
        secondLayoutBinding = SecondLayoutBinding.inflate(getLayoutInflater());
        setContentView(secondLayoutBinding.getRoot());
        var extraData = getIntent().getStringExtra("extra_data");
        Log.d("SecondActivity", "extra data is " + extraData);
        secondLayoutBinding.button2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("data_return", "Hello FirstActivity");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
