package com.learning.remotecontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RemoteControlFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private TextView mSelectedTextView;
    private TextView mWorkingTextView;
    private Button mEnterButton;

    public RemoteControlFragment() {
        // Required empty public constructor
    }

    public static RemoteControlFragment newInstance(String param1, String param2) {
        RemoteControlFragment fragment = new RemoteControlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_remote_control, container, false);
        mSelectedTextView = (TextView) view.findViewById(R.id.fragment_remote_control_selectedTextView);
        mWorkingTextView = (TextView) view.findViewById(R.id.fragment_remote_control_workingTextView);

        View.OnClickListener numberButtonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String working = mWorkingTextView.getText().toString();
                String text = textView.getText().toString();
                if (working.equals("0")) {
                    mWorkingTextView.setText(text);
                } else {
                    mWorkingTextView.setText(working + text);
                }

            }
        };

        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.fragment_remote_control_tableLayout);

        int number = 1;
        for (int i = 2; i < tableLayout.getChildCount() - 1; i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < tableRow.getChildCount(); j++) {
                Button button = (Button)tableRow.getChildAt(j);
                button.setText(String.valueOf(number));
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);

        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWorkingTextView.setText("0");
            }
        });

        Button zeroButton = (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);

        mEnterButton = (Button) bottomRow.getChildAt(2);
        mEnterButton.setText("Enter");
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence working = mWorkingTextView.getText();
                if (working.length()> 0) {
                    mSelectedTextView.setText(working);
                }
                mWorkingTextView.setText("0");
            }
        });


        return view;
    }

}
