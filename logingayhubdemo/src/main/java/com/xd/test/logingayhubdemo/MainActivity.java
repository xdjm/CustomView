package com.xd.test.logingayhubdemo;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextInputLayout textInputLayout_user,textInputLayout_password;
    private EditText editText_user,editText_password;
    private Button button;
    private TextView webView;
    private static String LOGIN_URL = "https://github.com/login";
    private static String USER_AGENT = "User-Agent";
    private static String USER_AGENT_VALUE = "Mozilla/5.0 (Linux; Android 6.0.1; MI 4LTE Build/MMB29M; wv)";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        textInputLayout_user = (TextInputLayout)findViewById(R.id.username);
        textInputLayout_password = (TextInputLayout)findViewById(R.id.password);
        editText_user = textInputLayout_user.getEditText();
        editText_password =  textInputLayout_password.getEditText();
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        webView = (TextView) findViewById(R.id.web);
    }

    private  void simulateLogin(String bluetata, String password01234) throws Exception{
        /*
         * 第一次请求
         * grab login form page first
         * 获取登陆提交的表单信息，及修改其提交data数据（login，password）
         */
        // get the response, which we will post to the action URL(rs.cookies())
        Connection con = Jsoup.connect(LOGIN_URL);  // 获取connection
        con.header(USER_AGENT, USER_AGENT_VALUE);   // 配置模拟浏览器
        Connection.Response rs = con.execute();                // 获取响应
        Document d1 = Jsoup.parse(rs.body());       // 转换为Dom树
        List<Element> eleList = d1.select("form");  // 获取提交form表单，可以通过查看页面源码代码得知

        // 获取cooking和表单属性
        // lets make data map containing all the parameters and its values found in the form
        Map<String, String> datas = new HashMap<>();
        for (Element e : eleList.get(0).getAllElements()) {
            // 设置用户名
            if (e.attr("name").equals("login"))
                e.attr("value", bluetata);
            // 设置用户密码
            if (e.attr("name").equals("password"))
                e.attr("value", password01234);
            // 排除空值表单属性
            if (e.attr("name").length() > 0)
                datas.put(e.attr("name"), e.attr("value"));
        }

        /*
         * 第二次请求，以post方式提交表单数据以及cookie信息
         */
        Connection con2 = Jsoup.connect("https://github.com/session");
        con2.header(USER_AGENT, USER_AGENT_VALUE);
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).followRedirects(true).method(Connection.Method.POST).data(datas).cookies(rs.cookies()).execute();
        // 打印，登陆成功后的信息
        // parse the document from response

        Log.v("info",login.statusMessage());
        webView.setText(login.body());
        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            Log.v("yjm",s + " : " + map.get(s));
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void login() throws Exception{
        if(TextUtils.isEmpty(editText_user.getText().toString())||TextUtils.isEmpty(editText_password.getText().toString()))
            Snackbar.make(button,"请输入用户名和密码",Snackbar.LENGTH_SHORT).show();
        else new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    simulateLogin(editText_user.getText().toString(),editText_password.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
