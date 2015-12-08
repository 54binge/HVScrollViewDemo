package com.binge.hvscrollviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binge.hvscrollviewdemo.bean.Car;
import com.binge.hvscrollviewdemo.bean.CarInfo;
import com.binge.hvscrollviewdemo.bean.Info;
import com.binge.hvscrollviewdemo.listener.ScrollChangeListener;
import com.binge.hvscrollviewdemo.view.MHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private List<Car> carList = new ArrayList<>();
    private LayoutInflater inflater;
    private RelativeLayout title_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = LayoutInflater.from(this);

        initData();

        title_rl = (RelativeLayout) findViewById(R.id.title_in);

        title_rl.setOnTouchListener(new MTouchListener());

        StickyListHeadersListView lv = (StickyListHeadersListView) findViewById(R.id.lv);

        lv.setOnTouchListener(new MTouchListener());

        lv.setAdapter(new CarAdapter());
    }

    class MTouchListener implements View.OnTouchListener{
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
                viewHeader.headTv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setOnTouchListener(new MTouchListener());
                convertView.setTag(viewHeader);
            } else {
                viewHeader = (ViewHeader) convertView.getTag();
            }
            viewHeader.headTv.setText(carList.get(position).getCarInfo().getAttrName());
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return carList.get(position).getCarInfo().getAttrName().hashCode();
        }

        @Override
        public int getCount() {
            return carList.size();
        }

        @Override
        public Object getItem(int position) {
            return carList.get(position);
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

            Car car = carList.get(position);
            CarInfo carInfo = car.getCarInfo();
            List<Info> infoList = carInfo.getInfoList();
            if(!infoList.isEmpty()){
//                Info info = infoList.remove(0);
//                vh.title_tv.setText(info.getName());
//                TextView t = new TextView(MainActivity.this);
//                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                layoutParams.height = 30;
//                t.setLayoutParams(layoutParams);
//                t.setText(info.getValue());
//                vh.item_ll.addView(t, vh.item_ll.getChildCount());
            }


            return convertView;
        }

        class ViewHeader {
            TextView headTv;
        }

        class ViewHolder {
            TextView title_tv;
            LinearLayout item_ll;
            MHorizontalScrollView hsv;
        }
    }

    private void initData() {
        for (int i = 1; i <= 50; i++) {
            Car car = new Car();
            car.setName("Car1");

            CarInfo carInfo = new CarInfo();
            carInfo.setAttrName("title-"+i);

            //每个分类有8个属性
            List<Info> list = new ArrayList<>();
            for (int j = 1; j <= 8; j++) {
                Info info = new Info("info-" + j, i + "-value-" + j);
                list.add(info);
            };
            carInfo.setInfoList(list);
            car.setCarInfo(carInfo);

            carList.add(car);
        }
    }


}
