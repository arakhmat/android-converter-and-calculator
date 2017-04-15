package com.example.akhme_000.myapplication.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.akhme_000.myapplication.R;


public class KeyboardFragment extends Fragment implements View.OnClickListener {//} implements View.OnClickListener {

    public enum ButtonEnum {
        ZERO           ('0'),
        ONE            ('1'),
        TWO            ('2'),
        THREE          ('3'),
        FOUR           ('4'),
        FIVE           ('5'),
        SIX            ('6'),
        SEVEN          ('7'),
        EIGHT          ('8'),
        NINE           ('9'),
        A              ('A'),
        B              ('B'),
        C              ('C'),
        D              ('D'),
        E              ('E'),
        F              ('F'),
        DOT            ('.'),
        MINUS          ('-'),
        PLUS           ('+'),
        OPEN_BRACKET   ('('),
        CLOSED_BRACKET (')'),
        MULTIPLY       ('*'),
        DIVIDE         ('/'),
        POWER          ('^'),
        CLEAR          ('\0'),
        BACKSPACE      ('\0'),
        EQUALS         ('\0');

        private final char character;
        ButtonEnum(char character) {
            this.character = character;
        }
        public char getCharacter() { return character; }
    }

    static ButtonEnum buttonEnum;

    private OnKeyboardInteractionListener mListener;

    public KeyboardFragment() {}

    public static KeyboardFragment newInstance() {
        KeyboardFragment fragment = new KeyboardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.keyboard_layout, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnKeyboardInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnKeyboardInteractionListener");
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button number_buttons[] = new Button[16];
        number_buttons[0] =  (Button) getActivity().findViewById(R.id.zero_button);
        number_buttons[1] =  (Button) getActivity().findViewById(R.id.one_button);
        number_buttons[2] =  (Button) getActivity().findViewById(R.id.two_button);
        number_buttons[3] =  (Button) getActivity().findViewById(R.id.three_button);
        number_buttons[4] =  (Button) getActivity().findViewById(R.id.four_button);
        number_buttons[5] =  (Button) getActivity().findViewById(R.id.five_button);
        number_buttons[6] =  (Button) getActivity().findViewById(R.id.six_button);
        number_buttons[7] =  (Button) getActivity().findViewById(R.id.seven_button);
        number_buttons[8] =  (Button) getActivity().findViewById(R.id.eight_button);
        number_buttons[9] =  (Button) getActivity().findViewById(R.id.nine_button);
        number_buttons[10] = (Button) getActivity().findViewById(R.id.a_button);
        number_buttons[11] = (Button) getActivity().findViewById(R.id.b_button);
        number_buttons[12] = (Button) getActivity().findViewById(R.id.c_button);
        number_buttons[13] = (Button) getActivity().findViewById(R.id.d_button);
        number_buttons[14] = (Button) getActivity().findViewById(R.id.e_button);
        number_buttons[15] = (Button) getActivity().findViewById(R.id.f_button);
        for (int i = 0; i < 16; i++) {
            number_buttons[i].setOnClickListener(this);
        }

        Button minus_button = (Button) getActivity().findViewById(R.id.minus_button);
        minus_button.setOnClickListener(this);
        Button dot_button = (Button) getActivity().findViewById(R.id.dot_button);
        dot_button.setOnClickListener(this);

        Button plus_button = (Button) getActivity().findViewById(R.id.plus_button);
        plus_button.setOnClickListener(this);
        Button open_bracket_button = (Button) getActivity().findViewById(R.id.open_bracket_button);
        open_bracket_button.setOnClickListener(this);
        Button closed_bracket_button = (Button) getActivity().findViewById(R.id.closed_bracket_button);
        closed_bracket_button.setOnClickListener(this);

        Button multiply_button = (Button) getActivity().findViewById(R.id.multiply_button);
        multiply_button.setOnClickListener(this);
        Button divide_button = (Button) getActivity().findViewById(R.id.divide_button);
        divide_button.setOnClickListener(this);
        Button power_button = (Button) getActivity().findViewById(R.id.power_button);
        power_button.setOnClickListener(this);

        Button equals_button = (Button) getActivity().findViewById(R.id.equals_button);
        equals_button.setOnClickListener(this);

        Button clear_button = (Button) getActivity().findViewById(R.id.clear_button);
        clear_button.setOnClickListener(this);
        Button backspace_button = (Button) getActivity().findViewById(R.id.backspace_button);
        backspace_button.setOnClickListener(this);

    }

    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.zero_button:
                mListener.onKeyboardInteraction(buttonEnum.ZERO);
                break;
            case R.id.one_button:
                mListener.onKeyboardInteraction(buttonEnum.ONE);
                break;
            case R.id.two_button:
                mListener.onKeyboardInteraction(buttonEnum.TWO);
                break;
            case R.id.three_button:
                mListener.onKeyboardInteraction(buttonEnum.THREE);
                break;
            case R.id.four_button:
                mListener.onKeyboardInteraction(buttonEnum.FOUR);
                break;
            case R.id.five_button:
                mListener.onKeyboardInteraction(buttonEnum.FIVE);
                break;
            case R.id.six_button:
                mListener.onKeyboardInteraction(buttonEnum.SIX);
                break;
            case R.id.seven_button:
                mListener.onKeyboardInteraction(buttonEnum.SEVEN);
                break;
            case R.id.eight_button:
                mListener.onKeyboardInteraction(buttonEnum.EIGHT);
                break;
            case R.id.nine_button:
                mListener.onKeyboardInteraction(buttonEnum.NINE);
                break;
            case R.id.a_button:
                mListener.onKeyboardInteraction(buttonEnum.A);
                break;
            case R.id.b_button:
                mListener.onKeyboardInteraction(buttonEnum.B);
                break;
            case R.id.c_button:
                mListener.onKeyboardInteraction(buttonEnum.C);
                break;
            case R.id.d_button:
                mListener.onKeyboardInteraction(buttonEnum.D);
                break;
            case R.id.e_button:
                mListener.onKeyboardInteraction(buttonEnum.E);
                break;
            case R.id.f_button:
                mListener.onKeyboardInteraction(buttonEnum.F);
                break;
            case R.id.dot_button:
                mListener.onKeyboardInteraction(buttonEnum.DOT);
                break;
            case R.id.minus_button:
                mListener.onKeyboardInteraction(buttonEnum.MINUS);
                break;
            case R.id.plus_button:
                mListener.onKeyboardInteraction(buttonEnum.PLUS);
                break;
            case R.id.open_bracket_button:
                mListener.onKeyboardInteraction(buttonEnum.OPEN_BRACKET);
                break;
            case R.id.closed_bracket_button:
                mListener.onKeyboardInteraction(buttonEnum.CLOSED_BRACKET);
                break;
            case R.id.multiply_button:
                mListener.onKeyboardInteraction(buttonEnum.MULTIPLY);
                break;
            case R.id.divide_button:
                mListener.onKeyboardInteraction(buttonEnum.DIVIDE);
                break;
            case R.id.power_button:
                mListener.onKeyboardInteraction(buttonEnum.POWER);
                break;
            case R.id.backspace_button:
                mListener.onKeyboardInteraction(buttonEnum.BACKSPACE); // delete symbol
                break;
            case R.id.clear_button:
                mListener.onKeyboardInteraction(buttonEnum.CLEAR); // clear screen
                break;
            case R.id.equals_button:
                mListener.onKeyboardInteraction(buttonEnum.EQUALS); // evaluate the input
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnKeyboardInteractionListener {

        void onKeyboardInteraction(ButtonEnum keyboard_input);
    }
}
