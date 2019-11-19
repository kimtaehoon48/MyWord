### README.md
TextView w0 = (TextView)findViewById(R.id.w0);
        TextView w1 = (TextView)findViewById(R.id.w1);
        TextView w2 = (TextView)findViewById(R.id.w2);
        TextView w3 = (TextView)findViewById(R.id.w3);
        TextView w4 = (TextView)findViewById(R.id.w4);
        TextView w5 = (TextView)findViewById(R.id.w5);
        TextView w6 = (TextView)findViewById(R.id.w6);
        TextView w7 = (TextView)findViewById(R.id.w7);
        TextView w8 = (TextView)findViewById(R.id.w8);
        TextView w9 = (TextView)findViewById(R.id.w9);



dd
        TextView m0 = (TextView)findViewById(R.id.m0);
        TextView m1 = (TextView)findViewById(R.id.m1);
        TextView m2 = (TextView)findViewById(R.id.m2);
        TextView m3 = (TextView)findViewById(R.id.m3);
        TextView m4 = (TextView)findViewById(R.id.m4);
        TextView m5 = (TextView)findViewById(R.id.m5);
        TextView m6 = (TextView)findViewById(R.id.m6);
        TextView m7 = (TextView)findViewById(R.id.m7);
        TextView m8 = (TextView)findViewById(R.id.m8);
        TextView m9 = (TextView)findViewById(R.id.m9);

       // int i = 2;
       // for (int k = (i * 10)-1; k < (i * 10) + 9; ) {
        for(int k=-1; k<199;){
            w0.setText(words[k + 1]);
            m0.setText(meaning[k + 1]);
            k++;
            w1.setText(words[k + 1]);
            m1.setText(meaning[k + 1]);
            k++;
            w2.setText(words[k + 1]);
            m2.setText(meaning[k + 1]);
            k++;
            w3.setText(words[k + 1]);
            m3.setText(meaning[k + 1]);
            k++;
            w4.setText(words[k + 1]);
            m4.setText(meaning[k + 1]);
            k++;
            w5.setText(words[k + 1]);
            m5.setText(meaning[k + 1]);
            k++;
            w6.setText(words[k + 1]);
            m6.setText(meaning[k + 1]);
            k++;
            w7.setText(words[k + 1]);
            m7.setText(meaning[k + 1]);
            k++;
            w8.setText(words[k + 1]);
            m8.setText(meaning[k + 1]);
            k++;
            w9.setText(words[k + 1]);
            m9.setText(meaning[k + 1]);
            k++;

            if (m9.getText() != null && m9.getText().equals("")) {
                break;

            }}}





