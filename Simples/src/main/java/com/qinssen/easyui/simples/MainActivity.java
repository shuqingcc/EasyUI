package com.qinssen.easyui.simples;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.qinssen.easyui.baseAdapter.EasyAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_num)
    TextView tvNum;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private List<String> list = new ArrayList<>();
    private MyAdapter myAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        myAdapter = new MyAdapter();
        myAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        list.addAll(Arrays.asList("李子", "樱桃", "葡萄", "菠萝", "椰子", "草莓", "苹果", "西瓜", "香蕉", "柚子", "柠檬", "桔子", "芒果", "枣子", "猕猴桃", "水蜜桃", "荔枝", "杨梅"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //把列表设置成水平滚动
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(myAdapter);

        myAdapter.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() {
            @Override
            public void onSelected(EasyAdapter.Operation operation, int itemPosition, boolean isSelected) {
                Toast.makeText(MainActivity.this, "multiSelectedPosition:" + isSelected, Toast.LENGTH_SHORT).show();
                tvNum.setText(myAdapter.getMultiSelectedPosition().toString());
            }
        });
        myAdapter.setOnItemClickListener(new EasyAdapter.OnItemClickListener() {
            @Override
            public void onClicked(int itemPosition) {
                Toast.makeText(MainActivity.this, "clickPosition:" + itemPosition, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.btn_selectedAll, R.id.btn_clearSelected, R.id.btn_reverseSelected})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_selectedAll:
                myAdapter.selectAll();
                break;
            case R.id.btn_clearSelected:
                myAdapter.clearSelected();
                break;
            case R.id.btn_reverseSelected:
                myAdapter.reverseSelected();
                break;
        }
    }


    // 继承EasyAdapter
    private class MyAdapter extends EasyAdapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(getApplicationContext(), R.layout.item_log, null);
            return new MyViewHolder(view);
        }

        @Override
        public void whenBindViewHolder(MyViewHolder holder, int position) {
            holder.textView.setTag(position);//绑定
            holder.textView.setText(list.get(position));
            holder.cb_log.setChecked(isSelected(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CheckBox cb_log;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_single_number);
            cb_log = (CheckBox) itemView.findViewById(R.id.cb_log);
        }
    }


}
