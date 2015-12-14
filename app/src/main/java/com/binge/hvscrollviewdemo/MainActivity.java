package com.binge.hvscrollviewdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binge.hvscrollviewdemo.bean.CarInfo;
import com.binge.hvscrollviewdemo.bean.Info;
import com.binge.hvscrollviewdemo.bean.ValueInfo;
import com.binge.hvscrollviewdemo.listener.ScrollChangeListener;
import com.binge.hvscrollviewdemo.view.BorderTextView;
import com.binge.hvscrollviewdemo.view.MHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private List<CarInfo> carInfoList = new ArrayList<>();
    private LayoutInflater inflater;
    private RelativeLayout title_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);

        initData();

        title_rl = (RelativeLayout) findViewById(R.id.title_in);

        LinearLayout itemLL = (LinearLayout) findViewById(R.id.item_ll);

        List<ValueInfo> infoValue = carInfoList.get(0).getInfo().getInfoValue();

        Random random = new Random();

        for (int i = 0; i < infoValue.size(); i++) {
            BorderTextView borderTextView = new BorderTextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200 - 16, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.setMargins(0, 0, 16, 0);
            borderTextView.setLayoutParams(layoutParams);
            borderTextView.setPadding(20, 0, 20, 0);
            borderTextView.setGravity(Gravity.CENTER);
            int z = random.nextInt(5);
            while (z==0){
                z = random.nextInt(5);
            }
            Log.e("----", "----------" + z);
            borderTextView.setText(infoValue.get(i).getCarName() + "\n" + (50.76 * z) + "万");
            borderTextView.setBorderColor(Color.parseColor("#FFCCCCCC"));
            borderTextView.setBorderWith(1);
            borderTextView.drawBorder(true, true, true, true);
            itemLL.addView(borderTextView, i);
        }

        title_rl.setOnTouchListener(new MTouchListener());

        StickyListHeadersListView lv = (StickyListHeadersListView) findViewById(R.id.lv);

        lv.setOnTouchListener(new MTouchListener());

        lv.setAdapter(new CarAdapter());
    }

    class MTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            title_rl.findViewById(R.id.item_hsv).onTouchEvent(event);
            return false;
        }
    }

    private class CarAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            ViewHeader viewHeader;
            if (convertView == null) {
                viewHeader = new ViewHeader();
                convertView = inflater.inflate(R.layout.title, null);
                viewHeader.headTv = (BorderTextView) convertView.findViewById(R.id.tv);
                convertView.setOnTouchListener(new MTouchListener());
                convertView.setTag(viewHeader);
            } else {
                viewHeader = (ViewHeader) convertView.getTag();
            }
            viewHeader.headTv.setText(carInfoList.get(position).getTitleName());
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return carInfoList.get(position).getTitleName().hashCode();
        }

        @Override
        public int getCount() {
            return carInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return carInfoList.get(position).getInfo();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = inflater.inflate(R.layout.item, null);
                vh.title_tv = (TextView) convertView.findViewById(R.id.title);
                vh.item_ll = (LinearLayout) convertView.findViewById(R.id.item_ll);
                vh.hsv = (MHorizontalScrollView) convertView.findViewById(R.id.item_hsv);
                vh.hsv.addScrollChangeListener(new ScrollChangeListener(vh.hsv));
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            Info info = (Info) getItem(position);

            vh.title_tv.setText(info.getKeyName());

            int size = info.getInfoValue().size();

            for (int i = 0; i < size; i++) {
                boolean drawRightBorder;
                if (i == (size - 1)) {
                    drawRightBorder = false;
                } else {
                    drawRightBorder = true;
                }
                BorderTextView textView = new BorderTextView(MainActivity.this);
                textView.drawBorder(false, false, drawRightBorder, false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(20, 0, 20, 0);
                textView.setGravity(Gravity.CENTER);
                textView.setText(info.getInfoValue().get(i).getValue());
                vh.item_ll.addView(textView, i);
            }

            return convertView;
        }

        class ViewHeader {
            BorderTextView headTv;
        }

        class ViewHolder {
            TextView title_tv;
            LinearLayout item_ll;
            MHorizontalScrollView hsv;
        }
    }

    private void initData() {
        for (int i = 1; i <= 3; i++) {
            CarInfo carInfo = new CarInfo();
            carInfo.setTitleName("基本信息1");

            Info info = new Info();
            info.setKeyName("指标" + i);
            List<ValueInfo> list = new ArrayList<>();
            for (int z = 1; z <= 11; z++) {
                ValueInfo valueInfo = new ValueInfo();
                valueInfo.setCarName("奔驰" + z + "代");
                valueInfo.setValue("奔驰" + z + "代,值" + z);
                list.add(valueInfo);
            }
            info.setInfoValue(list);

            carInfo.setInfo(info);

            carInfoList.add(carInfo);
        }


        for (int i = 1; i <= 5; i++) {
            CarInfo carInfo = new CarInfo();
            carInfo.setTitleName("基本信息2");

            Info info = new Info();
            info.setKeyName("指标" + i);
            List<ValueInfo> list = new ArrayList<>();
            for (int z = 1; z <= 11; z++) {
                ValueInfo valueInfo = new ValueInfo();
                valueInfo.setCarName("奔驰" + z + "代");
                valueInfo.setValue("奔驰" + z + "代,值" + z);
                list.add(valueInfo);
            }
            info.setInfoValue(list);

            carInfo.setInfo(info);

            carInfoList.add(carInfo);
        }

        for (int i = 1; i <= 3; i++) {
            CarInfo carInfo = new CarInfo();
            carInfo.setTitleName("基本信息3");

            Info info = new Info();
            info.setKeyName("指标" + i);
            List<ValueInfo> list = new ArrayList<>();
            for (int z = 1; z <= 11; z++) {
                ValueInfo valueInfo = new ValueInfo();
                valueInfo.setCarName("奔驰" + z + "代");
                valueInfo.setValue("奔驰" + z + "代,值" + z);
                list.add(valueInfo);
            }
            info.setInfoValue(list);

            carInfo.setInfo(info);

            carInfoList.add(carInfo);
        }
    }


}
