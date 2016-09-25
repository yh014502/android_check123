package iii.org.tw.android_project;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class numViewActivity extends AppCompatActivity {
    private TextView view1,view2,view3,view4;
    private UIHandler handler;
    private Object []database;
    private StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_view);
        view1=(TextView)findViewById(R.id.view1);
        view2=(TextView)findViewById(R.id.view2);
        view3=(TextView)findViewById(R.id.view3);
        view4=(TextView)findViewById(R.id.view4);

        handler = new UIHandler();
        MyThread mt=new MyThread();
        mt.start();
    }
    private class MyThread extends Thread{
        @Override
        public void run() {
            try {
                URL url = new URL("http://invoice.etax.nat.gov.tw/");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.connect();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String data = reader.readLine();

                System.out.println(data);
                System.out.println(data.indexOf("特別獎"));
                int p1 = data.indexOf("特別獎");
                String s1 = data.substring(p1);
                System.out.println(s1);
                int p2 = s1.indexOf("<span");
                int p3 = s1.indexOf("</span>");
                String s2 = s1.substring(p2,p3);
                System.out.println(s2);
                String s3 = s2.substring(s2.length()-8);
                System.out.println(s3);

                int p1_1 = data.indexOf("特獎");
                String s1_1 = data.substring(p1_1);
                int p2_1 = s1_1.indexOf("<span");
                int p3_1 = s1_1.indexOf("</span>");
                String s2_1 = s1_1.substring(p2_1,p3_1);
                String s3_1 = s2_1.substring(s2_1.length()-8);
                System.out.println(s3_1);

                int p1_2 = data.indexOf("頭獎");
                String s1_2 = data.substring(p1_2);
                int p2_2 = s1_2.indexOf("<span");
                int p3_2 = s1_2.indexOf("</span>");
                String s2_2 = s1_2.substring(p2_2,p3_2);
                String s3_2 = s2_2.substring(s2_2.length()-26);
                String s4_2=s3_2.substring(0,8);
                String s5_2=s3_2.substring(9,17);
                String s6_2=s3_2.substring(18,26);
                System.out.println(s4_2+","+s5_2+","+s6_2);

                int p1_3 = data.indexOf("增開六獎");
                String s1_3 = data.substring(p1_3);
                int p2_3 = s1_3.indexOf("<span");
                int p3_3 = s1_3.indexOf("</span>");
                String s2_3 = s1_3.substring(p2_3,p3_3);
                String s3_3 = s2_3.substring(s2_3.length()-15);
                System.out.println(s3_3);

                database=new Object[5];
                database[0]=s3;
                database[1]=s3_1;
                database[2]=s4_2;
                database[3]=s5_2;
                database[4]=s6_2;
                database[5]=s3_3;

                Message mesg = handler.obtainMessage();
                Bundle data1 = new Bundle();
                data1.putString("database",s3);
                mesg.setData(data1);
                handler.sendMessage(mesg);

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        }

    private class UIHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data=msg.getData();
            String data12=data.getString("database");
            Log.d("brad",""+data12);
        }
    }

    }


