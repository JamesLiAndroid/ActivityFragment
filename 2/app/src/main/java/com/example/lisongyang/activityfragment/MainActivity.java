package com.example.lisongyang.activityfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{
    ToFragmentMessage messageGet;
    // TODO:修改这个标志位可以看到不同的效果
    private static final boolean IS_ACTIVITY_TO_FRAGMENT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!IS_ACTIVITY_TO_FRAGMENT) {
            BlankFragment fragment = BlankFragment.newInstance("abc", "def");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.activity_main, fragment);
            transaction.commit();
        } else {
            SimpleFragment fragment = SimpleFragment.newInstance("abc", "def");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.activity_main, fragment);
            transaction.commit();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_to_fragment:
                // 实现从Fragment向Activity发送消息
                // Toast.makeText(this, "点击事件！", Toast.LENGTH_SHORT).show();
                messageGet.toFragmentMessage("Activity To Fragment!");
                Log.d("TAG","开始发送信息");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public interface ToFragmentMessage {
        void toFragmentMessage(String msg);
    }


    public void setOnToFragmentMessage(ToFragmentMessage message) {
        this.messageGet = message;
    }



    // Fragment接口的回调方法
    @Override
    public void onFragmentInteraction(String uri) {
        // 从Fragment到Activity通信
        Log.d("TAG","从Fagment到Activity通信信息："+uri);
        Toast.makeText(MainActivity.this, "从Fagment到Activity通信信息："+uri, Toast.LENGTH_SHORT).show();
    }
}
