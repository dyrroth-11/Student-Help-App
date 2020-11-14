package com.example.studentshelpapp.ui.notes;

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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentshelpapp.R;

public class bookdesc extends Fragment {
    ListView listView;
    String[] Name={"Automata theory, Languages",
            "Classical Dynamics",
            "Classical Mechanics",
            "Concise Inorganic chemistry",
            "Cryptography and Network Security",
            "Data Structures using C",
            "Digital Image processing",
            "Discrete Mathematics",
            "Discrete Mathematics",
            "Finitite Element Analysis",
            "Graph Theory",
            "Introduction to Probabilty Models",
            "Let Us C",
            "Linear Algebra",
            "Natural language Processing",
            "Non linear programming",
            "Number Theory",
            "Quantitative Chemical Analysis",
            "Theory and Algorithms",
            "Theory of Numbers",
            "Thomas Calculus",
            "Topology and Functional Analysis"
    };
    String Author[] = {"John E, R Motwani",
            "Donald T, Greenwood",
            "Goldstein",
            "J.D. Lee",
            "William Stallings",
            "Reema Thareja",
            "Rafael C. Gonzalez",
            "Kenneth H rosen, Manohar",
            "F Thomson, Albert R Meyer",
            "Tirupati R. Chandrupatla, S.S. Rao",
            "Narsingh Deo",
            "Sheldon S. Ross",
            "Yashwant Kanetkar",
            "Stepehn H. Friedberg",
            "Victoria Fromkin, Robert Rodman",
            "Bazaara Shetty",
            "Niven, Zuckerman",
            "Arthur Isreal Vogel",
            "Bazara Shetty",
            "Ivan Niven, Herbert S",
            "Maurice D. Weir",
            "James Munkres",

    };
    String desc[] = {"https://drive.google.com/drive/folders/1QpgLDxoJWUxCFD_nVF0RKDmJdRtVBofF",
            "https://drive.google.com/drive/folders/1SU28Sau9dgABw_JztuoAgYX8xCU6AyRO",
            "https://drive.google.com/drive/folders/1vB638ekRysHMd5571WQOb_15nKmFT1WZ",
            "https://drive.google.com/drive/folders/19BAWGWIPuLWDUkTT8gALP2OctMf6wAw_",
            "https://drive.google.com/drive/folders/1B5pfOwLfyLVJvBNGfOngv-oQBGOVhRvg",
            "https://drive.google.com/drive/folders/1bAhg5XmIVhYfrGgGAhJlIcg0amp7NpZl",
            "https://drive.google.com/drive/folders/1NLW7y8_MO48x7dS3bBw4ori446Sqila-",
            "https://drive.google.com/drive/folders/1mnYE4T0ocZMIxtQ5_XeAA96OkU4Uxtx6",
            "https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-042j-mathematics-for-computer-science-fall-2010/readings/MIT6_042JF10_notes.pdf",
            "https://drive.google.com/drive/folders/1M8Wx997Xp5YZgwVTSEeKdJ93cliy-hCz",
            "https://drive.google.com/drive/folders/18kre7tUJDubiNs10ttr1iRZlj_GD-LIE",
            "https://drive.google.com/drive/folders/1n5Z4_OAElxMl5tOG4Kc5rzSWzYHNPRRT",
            "https://drive.google.com/drive/folders/1s1SggF5CtLsx_pJC305K8bgbqjf_sabk",
            "https://drive.google.com/drive/folders/12f4CjYJtC_CJbbNTT9uHWow7yjq27trR",
            "https://drive.google.com/drive/folders/1vdDxAurUiYOCscv2QxCoa87GsPEFK4TU",
            "https://drive.google.com/drive/folders/1Ps1_E749l39wnoe3u1iKGKPvqvnYUZPK",
            "https://drive.google.com/drive/folders/1c_Md9CDSc_pL5HSXqjpvc8GkvqGQqGIB",
            "https://drive.google.com/drive/folders/19BAWGWIPuLWDUkTT8gALP2OctMf6wAw_",
            "https://drive.google.com/drive/folders/1Ps1_E749l39wnoe3u1iKGKPvqvnYUZPK",
            "https://drive.google.com/drive/folders/1c_Md9CDSc_pL5HSXqjpvc8GkvqGQqGIB",
            "https://drive.google.com/drive/folders/19ClYkJlv8epGdK1pfx1c9hwdjz-t5pwg",
            "https://drive.google.com/drive/folders/1d_5TfO9m10sdA6Z2hAmVgi1gyV6ak3U0",

    };
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookdesc, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        bookdesc.CustomAdapter customAdapter = new CustomAdapter();
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
            view = getLayoutInflater().inflate(R.layout.customlayout5,null);
            TextView subject = (TextView)view.findViewById(R.id.subject);
            subject.setText(Name[i]);
            TextView author = (TextView)view.findViewById(R.id.author);
            author.setText(Author[i]);
            TextView link = (TextView)view.findViewById(R.id.link);
            link.setText(desc[i]);
            return view;
        }
    }
}