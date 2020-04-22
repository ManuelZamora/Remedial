package com.example.remedialahorcado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class LetrasAdaptador extends BaseAdapter {
    private String[]letras;
    private LayoutInflater letrainf;

    public  LetrasAdaptador(Context context){
        letras=new String[26];
        for (int i = 0;i<letras.length;i++){
            letras[i]=""+(char)(i+'A');
        }
        letrainf=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return letras.length;
    }

    @Override
    public Object getItem(int i ) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnletra;
        if (view==null){
            btnletra=(Button)letrainf.inflate(R.layout.letras,viewGroup,false);
        }else
        {
            btnletra=(Button)view;
        }
        btnletra.setText((letras[i]));
        return btnletra;
    }
}
