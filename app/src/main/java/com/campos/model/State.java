package com.campos.model;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

public enum State {
    AL, AK, AZ, AR, CA, CO, CT, DE, FL, GA, HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT, VT, VA, WA, WV, WI, WY;

    public static void fillSpinner(Spinner spinner, Context context) {
        List<State> list = new LinkedList<>();
        for (State state : State.values())
            list.add(state);
        ArrayAdapter<State> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setPrompt("Select a State");
        spinner.setAdapter(arrayAdapter);
    }
}
