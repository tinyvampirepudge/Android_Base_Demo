package com.tiny.demo.firstlinecode.templates.temp1.view;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.tiny.demo.firstlinecode.R;

public class MvpTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        initFrag();
    }

    private void initFrag() {
        Bundle bundle = new Bundle();
        bundle.putString("txt", MvpTestActivity.class.getSimpleName());
        MvpTestFragment fragment = MvpTestFragment.newInstance(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
