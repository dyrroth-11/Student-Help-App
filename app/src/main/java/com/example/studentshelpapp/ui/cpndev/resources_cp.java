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

public class resources_cp extends Fragment {
    ListView listView;
    String[] Name={"Geeks for Geeks ","Cp- Algorithms","Topic specific problems",
            "Everything about DP","Graph Theory" ,"Everything about CP", "Number Theory" };
    String[] desc={"https://www.geeksforgeeks.org/",
    "https://cp-algorithms.com/",
    "https://codeforces.com/blog/entry/55274",
    "https://codeforces.com/blog/entry/67679",
    "https://www.tutorialspoint.com/graph_theory/graph_theory_fundamentals.htm",
    "https://codeforces.com/blog/entry/23054",
    "https://codeforces.com/blog/entry/77137"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.resources_cp, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        resources_cp.CustomAdapter customAdapter = new resources_cp.CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
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
            view = getLayoutInflater().inflate(R.layout.customlayout7,null);
            TextView restname_s = (TextView)view.findViewById(R.id.subject);
            restname_s.setText(Name[i]);
            TextView link = (TextView)view.findViewById(R.id.link);
            link.setText(desc[i]);
            return view;
        }
    }
}
