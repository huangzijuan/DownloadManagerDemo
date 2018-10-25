package com.example.huangzijuan.downloadmanagerdemo;

import android.app.DownloadManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View download;
    private View cancel;
    private TextView result;
    private String url = "http://gdown.baidu.com/data/wisegame/8d5889f722f640c8/weixin_800.apk";

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DownloadManager.STATUS_SUCCESSFUL:
                    result.setText("" + 100);
                    Toast.makeText(MainActivity.this, "下载任务已经完成！", Toast.LENGTH_SHORT).show();
                    break;

                case DownloadManager.STATUS_RUNNING:
                    //int progress = (int) msg.obj;
                    result.setText("" + (int) msg.obj);
                    //canceledDialog();
                    break;

                case DownloadManager.STATUS_FAILED:
                    break;

                case DownloadManager.STATUS_PENDING:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = findViewById(R.id.download);
        download.setOnClickListener(this);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        result = (TextView) findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        if (v == download) {
            new Thread(new DownLoadRunnable(this,url, handler)).start();
        } else if (v == cancel) {

        }
    }
}
