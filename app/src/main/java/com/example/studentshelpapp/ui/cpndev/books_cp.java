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

public class books_cp extends Fragment {
    ListView listView;
    String[] Name={"Competitive Programmer's Handbook",
            "Introduction to Algorithms",
            "Algorithms by Robert Sedgewick",
            "The Algorithm Design Manual by Steve S.",
            "Algorithms and data structures"};
    String[] desc={"https://cses.fi/book/book.pdf",
    "https://web.ist.utl.pt/~fabio.ferreira/material/asa/clrs.pdf",
    "https://github.com/Mcdonoughd/CS2223/blob/master/Books/Algorithhms%204th%20Edition%20by%20Robert%20Sedgewick%2C%20Kevin%20Wayne.pdf",
    "http://mimoza.marmara.edu.tr/~msakalli/cse706_12/SkienaTheAlgorithmDesignManual.pdf",
    "http://translate.google.ru/translate?sl=ru&tl=en&js=n&prev=_t&hl=ru&ie=UTF-8&layout=2&eotf=1&u=http%3A%2F%2Fe-maxx.ru%2Falgo%2F&act=url"};
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.books_cp, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        books_cp.CustomAdapter customAdapter = new books_cp.CustomAdapter();
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
            view = getLayoutInflater().inflate(R.layout.customlayout6,null);
            TextView restname_s = (TextView)view.findViewById(R.id.subject);
            restname_s.setText(Name[i]);
            TextView link = (TextView)view.findViewById(R.id.link);
            link.setText(desc[i]);
            return view;
        }
    }
}
