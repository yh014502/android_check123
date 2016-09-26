package iii.org.tw.android_project;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private TextView view1,view2,view3,view4,view5;
    private UIHandler handler;
    private String []database;
    private String []data12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view1=(TextView)findViewById(R.id.view1);
        view2=(TextView)findViewById(R.id.view2);
        view3=(TextView)findViewById(R.id.view3);
        view4=(TextView)findViewById(R.id.view4);
        view5=(TextView)findViewById(R.id.view5);

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
                String s4_3 = s3_3.substring(0,3);
                String s5_3 = s3_3.substring(4,7);
                String s6_3 = s3_3.substring(8,11);
                String s7_3 = s3_3.substring(12,15);


                System.out.println(s3_3);


                int p1_4 = data.indexOf("<h2>1");
                String s1_4 = data.substring(p1_4);
                System.out.println(s1_4);
                String s4_4=s1_4.substring(4,14);
                System.out.println(s4_4);


                database=new String[10];
                database[0]=s3;
                database[1]=s3_1;
                database[2]=s4_2;
                database[3]=s5_2;
                database[4]=s6_2;
                database[5]=s4_3;
                database[6]=s5_3;
                database[7]=s6_3;
                database[8]=s7_3;
                database[9]=s4_4;


                Message mesg =new Message();
                Bundle data1 = new Bundle();
                data1.putStringArray("database",database);
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
            data12=data.getStringArray("database");
            for(String i:data12){
                Log.d("brad",i);
            }
            view1.setText(data12[0]);
            view2.setText(data12[1]);
            view3.setText(data12[2]+"、"+data12[3]+"、"+data12[4]);
            view4.setText(data12[5]+"、"+data12[6]+"、"+data12[7]+"、"+data12[8]);
            view5.setText(data12[9]);
        }
    }

    public void gotocheck(View v){
        Intent it =new Intent(this,checkActivity.class);
        it.putExtra("data12",data12);
        startActivity(it);
    }

}


