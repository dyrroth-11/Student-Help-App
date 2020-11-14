package com.example.studentshelpapp.ui.notes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.studentshelpapp.R;

public class notedesc extends Fragment{
    ListView listView;
    String[] Name={"Algebra: MA 103",
    "Algorithms",
    "Algorithms 1",
    "Algorithms 2",
    "Artificial Intelligence",
    "Basic Electrical Engineering",
    "Biology 101",
    "Classical, Quantum and Relativsitc physics",
    "Compliers",
    "Computer Networks",
    "Computer Organisation and Architechture",
    "Computer System organisation",
    "Contemporary India in globalised era",
    "CY 101 lab",
    "Data Structures",
    "Development of Society",
    "Differnetial equations",
    "Discrete Mathematics",
    "Discrete Structures",
    "Distributed sytems",
    "Distribution Theory",
    "EC lab records",
    "Electrical Technology",
    "Electrodynamics",
    "Electrodynamics and Optics",
    "Electronics and Instrumentation",
    "Engineering Maths 1",
    "Engineering Maths 1",
    "Engineering Maths 2",
    "Enginnering Drawing",
    "Enginnering Mechanics",
    "Essentials of Biochemistry",
    "Financial management",
    "Finite Element Analysis",
    "Fluid Dynamics",
    "Fuzzy Set Theory",
    "Graph Theory",
    "Group Theory",
    "Heat and Mass transfer",
    "High performance parallel programming",
    "Human Values",
    "Image Processing",
    "Intelligent Game designing",
    "Introduction to biomedical engineering",
    "Introduction to chemistry",
    "Introduction to computer programing",
    "Introduction to Electronics",
    "Introduction to industrial management",
    "ITW -1",
    "ITW 104",
    "Linear Algebra",
    "Linear Algebra",
    "MA 101",
    "MA 102",
    "Machine Learning",
    "Machine Translation",
    "Manufacturing Practices",
    "Mathematical Methods",
    "Mathematical Modelling",
    "Mechanics 101",
    "Mine Closure Planning",
    "Natural language processing",
    "Network Security",
    "Number Theory",
    "Numerical Techniques",
    "Operating Systems",
    "Operations Research",
    "Opertaing Systems",
    "Optimisation Techniques",
    "Parallel and distributed algorithms",
    "Physics 101 and 102",
    "Probabilty and statistics",
    "Probabilty and statistics",
    "Real and Complex Analysis",
    "Sanskrit Bhasha",
    "Science of living System",
    "Signals and Networks",
    "Signet Lab Records",
    "Sociology Society and culture",
    "Solid state chemistry",
    "State, nation politics",
    "Switching logics and circuit design",
    "Symbolic Logic",
    "Theory of Computation",
    "Theory of Computation",
    "Topology and functional analysis",
    "Ubiquitous Computing"
};
    String desc[] = {"https://drive.google.com/drive/folders/1saMwh8XGcBT1XKh9hyuOuz-LHXRMzk-x",
    "https://drive.google.com/drive/folders/11vjZEpkexMHa7XuLNYokec29ohvcg5UQ",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zZHdOVC1LZFFsSzA",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zNkoxTGlVSGV6Um8",
    "https://drive.google.com/drive/folders/1-AEN44mhEUNpnwGuSB0vu2rRuNFcGIod",
    "https://drive.google.com/drive/folders/1_jszpDuBfpSkhBv50dEPZ7_whsUe8yjx",
    "https://drive.google.com/drive/folders/1Y-wztUZZJ0sqUdXEjbbKuLmKlalODNJo",
    "https://drive.google.com/drive/folders/1BCqgbFp-6Q634eLD7pu4hnHj-wfSgB7b",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zOUJaaTd4M3J5OGc",
    "https://drive.google.com/drive/folders/1ryoNVuC50kScCs6prPIH_k-J2SJLkpmt",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zaExNQkRiMWtxM00",
    "https://drive.google.com/drive/folders/1RuXBsaVqpRgICVPoh7hRSF95EJ-z9rFR",
    "https://drive.google.com/drive/folders/1pDi4Yjg_Kp9OTdtlifbVpnZyWEooL0zd",
    "https://drive.google.com/drive/folders/1hsmYLumi6FlUmrpDU88dohRJH7A5WN6J",
    "https://drive.google.com/drive/folders/1dzAu1aMygVMiNJYUVpc1HDldolzwyBv6",
    "https://drive.google.com/drive/folders/1TiZkh1o0MBHjyBO5kHHN92FbCLGeobzQ",
    "https://drive.google.com/drive/folders/1-iL4MHDiQBkTAq2dPG0FZcYtz9JACbbe",
    "https://drive.google.com/drive/folders/1icL-4x9kPnvIU_nylgRRvfYNcUYKiOn8",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zeEN6WGxFbjhHakU",
    "https://drive.google.com/drive/folders/1K0qWxV5kGD0GOovO1GPCE41aROCFA_Hn",
    "https://drive.google.com/drive/folders/1RVyZ0LYbUYwwiRjHg-tQxdx0BkhzjRH5",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zaDk3VW9lTXd4d1k",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zUV9SZkZSZU1hU1k",
    "https://drive.google.com/drive/folders/10RkiIhQ13Plq4DesM7t0cc3t1u7DATyo",
    "https://drive.google.com/drive/folders/1_LgHlglFVTyrFh_7jSeIr9b9BMCMTpNp",
    "https://drive.google.com/drive/folders/1NwvkEMHrBF8D3jKy2t7gXq6o4OkbSkRn",
    "https://drive.google.com/drive/folders/1D0B2csgOVoUgdZ6CZ1Btx4auqG3sQ7jN",
    "https://drive.google.com/drive/folders/1uerefKZkjlCAS42PanJp9ElrPkgXeo3p",
    "https://drive.google.com/drive/folders/1z9sEZHhXs5CtwgAcXH2VxWvCIR8Q2fRY",
    "https://drive.google.com/drive/folders/1chOUuFJHNZwcoy7BeKpRCpkdJniDRfWc",
    "https://drive.google.com/drive/folders/1L5lBnluAZZ0udHHF7KWl7kVcE-Hb85NC",
    "https://drive.google.com/drive/folders/1MeqqyiLy-ghIC00tmSZCVf7Q8bRwI7Gg",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zVWhlaFVRVzlsbzA",
    "https://drive.google.com/drive/folders/1M8Wx997Xp5YZgwVTSEeKdJ93cliy-hCz",
    "https://drive.google.com/drive/folders/1gU-b6CPq58aLhSZoUN6PCcKTXJ0GdQD9",
    "https://drive.google.com/drive/folders/1CQHpW07vJl_oQ7CiGnfu-I2-xbyFtFDJ",
    "https://drive.google.com/drive/folders/18kre7tUJDubiNs10ttr1iRZlj_GD-LIE",
    "https://drive.google.com/drive/folders/1RWCrxU9h5MyuzprmGAPxOucgEnmIODwX",
    "https://drive.google.com/drive/folders/1QJfqmDjKXV_cYHeMF_bBJ-h-fPMjov-l",
    "https://drive.google.com/drive/folders/1IJEaQGy9kI2DmOjuIAOfF0YwtLPBiCBA",
    "https://drive.google.com/drive/folders/14kIdwFDm-HUnqmEe_95oYKViWFIGkWna",
    "https://drive.google.com/drive/folders/1i0K2zu78O9I4r7j_pwPn8wm8GJzb6vS6",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zTXBROFFzTXJ0a00",
    "https://drive.google.com/drive/folders/1ZqVz5zTulbeTr6bajvmKKUPTFJng2V7T",
    "https://drive.google.com/drive/folders/1imqlDFtMe5HDxzraesvCUQTbAwI1X5jI",
    "https://drive.google.com/drive/folders/1-jjhclMSZGx9sr2AoS7fBwti-jxziHV-",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zTDNoTlFhNUZ6ZWs",
    "https://drive.google.com/drive/folders/1MG2KQrZRbJdeQirMJ4zLMv_TC_JN1lzu",
    "https://drive.google.com/drive/folders/1fq_9EQoQB8tNexykLbeMK_HWu43XNq38",
    "https://drive.google.com/drive/folders/1siuTWkLX8S2LR_8bYU12gCzZzcg3lZRO",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zdE1yUzFBMVEzeTA",
    "https://drive.google.com/drive/folders/12f4CjYJtC_CJbbNTT9uHWow7yjq27trR",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zQVFFTk5wa19vRDg",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zdzBVbkxKeWxYUUE",
    "https://drive.google.com/drive/folders/1cGNaLF1wP1DHpgGm4BDYBYysGRRRnfdr",
    "https://drive.google.com/drive/folders/17-HyDZuS4yKg1Vlkn3kA47RMZvklE_JG",
    "https://drive.google.com/drive/folders/1KWuB7QsaRnqggIwbM32PT2a-XWW5CS2O",
    "https://drive.google.com/drive/folders/1R2lAl9Qqw1-GC6ZloH9PpOR0jPhX4Rm8",
    "https://drive.google.com/drive/folders/1q3OBOTS2tTLUZ4aX8pLgOZmEu071yZ_1",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zSy1sblhNNWdSMUE",
    "https://drive.google.com/drive/folders/1BQyaMCP8HWv8Oo_6RbGczxhueuIK82bo",
    "https://drive.google.com/drive/folders/1vdDxAurUiYOCscv2QxCoa87GsPEFK4TU",
    "https://drive.google.com/drive/folders/1B5pfOwLfyLVJvBNGfOngv-oQBGOVhRvg",
    "https://drive.google.com/drive/folders/1c_Md9CDSc_pL5HSXqjpvc8GkvqGQqGIB",
    "https://drive.google.com/drive/folders/1QLeDoVDNnwPZkVMUMIyDlOD9DSCIqEpO",
    "https://drive.google.com/drive/folders/1gFCSaiajLFi8S5fPGyrN2TJY8vNn-cdQ",
    "https://drive.google.com/drive/folders/1L_UN2JesifiWKmjwj8M3LBW6bGXX2azg",
    "https://drive.google.com/drive/folders/1vtR7LXFmjkMQq3DbnYRjhSIZtMFysMT0",
    "https://drive.google.com/drive/folders/1Ps1_E749l39wnoe3u1iKGKPvqvnYUZPK",
    "https://drive.google.com/drive/folders/1U4bmxm6SAFMc6eiynYqp6bn8uvjvZkJH",
    "https://drive.google.com/drive/folders/1SU28Sau9dgABw_JztuoAgYX8xCU6AyRO",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zeDVydHkyOWVUYmM",
    "https://drive.google.com/drive/folders/1BTsJXsXFnyB5028spEQBQlQRVjOn3afE",
    "https://drive.google.com/drive/folders/1gHI6w5mOwCAQZV_zfEf5-jaV-ls7yMXk",
    "https://drive.google.com/drive/folders/1-ugZmImINk1g2NxXURLYAciWVddQGS3-",
    "https://drive.google.com/drive/folders/1B-SR5sPcJQdJYdm9jmLWun1w9-foCVnA",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zT3MxQmh6VFJVb2M",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zLXYyRTdLTzY3Y0k",
    "https://drive.google.com/drive/folders/1YcMIm-vaPL2TTbg861bDF6kuRmwWZGPL",
    "https://drive.google.com/drive/folders/1cDf2fPwbb1pKCu1zc5Lk0UJW9wVjb0VZ",
    "https://drive.google.com/drive/folders/11DVnfXa0y9lKTLNIdq4MZtXevXW_Obxh",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zeVY2aEZmNU1leFE",
    "https://drive.google.com/drive/folders/0Bx7x8HmM7p_zTXZTS0dzZFdmNjA",
    "https://drive.google.com/drive/folders/1PdYBvxHBSiFHP0Jaw1ZOPifAL9oqPYfA",
    "https://drive.google.com/drive/folders/1QpgLDxoJWUxCFD_nVF0RKDmJdRtVBofF",
    "https://drive.google.com/drive/folders/1d_5TfO9m10sdA6Z2hAmVgi1gyV6ak3U0",
    "https://drive.google.com/drive/folders/13rk_sc7Y-wIn9nZAohsvw5ljp3ld2X8l",
};
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notedesc, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        notedesc.CustomAdapter customAdapter = new CustomAdapter();
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
            view = getLayoutInflater().inflate(R.layout.customlayout4,null);
            TextView subject = (TextView)view.findViewById(R.id.subject);
            subject.setText(Name[i]);
            TextView link = (TextView)view.findViewById(R.id.link);
            link.setText(desc[i]);
            return view;
        }
    }
}