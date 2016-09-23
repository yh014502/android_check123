package iii.org.tw.android_project;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class checkActivity extends AppCompatActivity {
    private TextView shownum;
    private ListView list_check;

    private String[] dataset = {"789","524","634","678","564","755"};
    private MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        shownum=(TextView)findViewById(R.id.shownum);
        list_check=(ListView)findViewById(R.id.list_check);

        adapter = new MyAdapter();
        list_check.setAdapter(adapter);

    }

    private void start(){
        boolean isGet = false;
        if(shownum.length()==3){
            String num=shownum.getText().toString();
            Log.d("brad",num);
            for(int i=0;i<dataset.length;i++){
                if(num.equals(dataset[i])){
                    //shownum.append("恭喜中獎");
                    isGet=true;
                    Log.d("brad","OK"+dataset[i]);
                    break;

                }else{
                    isGet=false;
                    //shownum.append("再接再厲");
                    Log.d("brad","no"+dataset[i]);
                }
            }
            shownum.append(isGet ? "恭喜中獎" : "再接再厲");
        }
    }


    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(checkActivity.this);
        }

        @Override
        public int getCount() {
            return dataset.length;
        }

        @Override
        public Object getItem(int position) {
            return dataset[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = inflater.inflate(R.layout.layout_itemv1, parent, false);
            }

            TextView title = (TextView) convertView.findViewById(R.id.itemv1_title);
            if (position%2==0) {
                convertView.setBackgroundColor(Color.YELLOW);
                title.setTextColor(Color.BLUE);
            }else{
                convertView.setBackgroundColor(Color.GRAY);
                title.setTextColor(Color.RED);
            }
            title.setText(dataset[position]);

            return convertView;
        }
    }


    public void btnnum(View v){
        switch (v.getId()){
            case R.id.btn0:
                shownum.append(""+0);
                start();
                break;
            case R.id.btn1:
                shownum.append(""+1);
                start();
                break;
            case R.id.btn2:
                shownum.append(""+2);
                start();
                break;
            case R.id.btn3:
                shownum.append(""+3);
                start();
                break;
            case R.id.btn4:
                shownum.append(""+4);
                start();
                break;
            case R.id.btn5:
                shownum.append(""+5);
                start();
                break;
            case R.id.btn6:
                shownum.append(""+6);
                start();
                break;
            case R.id.btn7:
                shownum.append(""+7);
                start();
                break;
            case R.id.btn8:
                shownum.append(""+8);
                start();
                break;
            case R.id.btn9:
                shownum.append(""+9);
                start();
                break;
            case R.id.btnclean:
                shownum.setText("");
        }
    }
}