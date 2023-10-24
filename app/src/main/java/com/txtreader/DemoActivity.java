package com.txtreader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.juying.txtreaderlib.main.TxtConfig;
import com.juying.txtreaderlib.ui.HwTxtPlayActivity;
import com.juying.txtreaderlib.utils.FileProvider;

import java.io.File;

public class DemoActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 0x01;//用于请求写入外部存储的权限
    private String FilePath = Environment.getExternalStorageDirectory() + "/";//默认路径
    private Boolean Permit = false;//用于控制是否允许写入外部存储
    private EditText mEditText;//mEditText是一个EditText对象

    //初始化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText.setText(FilePath);
        Permit = CheckPermission();

    }

    //用户选择了某个选项，则获取该选项的Uri,并将其转换为文件路
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            Uri uri = data.getData();//得到uri，后面就是将uri转化成file的过程
            String path = FileProvider.getFileAbsolutePath(this, uri);
            mEditText.setText(path);
        }
    }

    //当用户选择了文件后，该方法将返回一个包含所选文件Uri的结果。
    public void chooseFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");//设置文本txt类型
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 3);
    }

    /*用于加载一个文本文件*/
    public void loadFile(View view) {
        if (Permit) {
            TxtConfig.saveIsOnVerticalPageMode(this, false);
            FilePath = mEditText.getText().toString().trim();
            if (TextUtils.isEmpty(FilePath) || !(new File(FilePath)).exists()) {
                toast("文件不存在");
            } else {
                HwTxtPlayActivity.loadTxtFile(this, FilePath);
            }
        }
    }


    /*检查权限模版
     */
    private Boolean CheckPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 安卓11，判断有没有“所有文件访问权限”权限
            if (Environment.isExternalStorageManager()) {
                return true;
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                return false;
            }
        }
        //判断检查用户是否授权相应的权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }


    /*用于处理权限请求的结果
     * 系统会弹出一个对话框，让用户选择是否授权。
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Permit = true;
                loadFile(null);
            } else {
                // 权限被拒绝
                Toast.makeText(DemoActivity.this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /*用于显示一个Toast消息。*/
    private Toast t;

    //用于显示一个Toast消息
    private void toast(String msg) {
        if (t != null) {
            t.cancel();
        }
        //Toast的长度被设置为短时间
        t = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        //显示文件上传成功
        t.show();
    }
}
