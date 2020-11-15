package com.example.studentshelpapp.ui.cpndev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.studentshelpapp.R;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class resources_dev extends Fragment {
    ListView listView;
    String[] Name={"COPS","Android Development: Udemy","W3 Schools",
            "Web developer Boot camp","Telusko for Django framework"};
    String[] desc={"https://copsiitbhu.co.in/resources/dev/",
            "https://www.udemy.com/course/complete-android-n-developer-course/",
            "https://www.udemy.com/course/the-web-developer-bootcamp/",
            "https://drive.google.com/drive/folders/1zma6Nw73NfctncG1oDvLoXFCNsjHwaj3",
            "https://www.youtube.com/watch?v=SIyxjRJ8VNY&list=PLsyeobzWxl7r2ukVgTqIQcl-1T0C2mzau"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.resources_dev, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        resources_dev.CustomAdapter customAdapter = new resources_dev.CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(desc[position]));
                startActivity(intent);
            }
        });
        return view;
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Name.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.customlayout8,null);
            TextView restname_s = (TextView)view.findViewById(R.id.subject);
            restname_s.setText(Name[i]);
            TextView link = (TextView)view.findViewById(R.id.link);
            link.setText(desc[i]);
            return view;
        }
    }
}
