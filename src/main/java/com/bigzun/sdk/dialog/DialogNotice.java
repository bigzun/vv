package com.bigzun.sdk.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bigzun.sdk.R;

public abstract class DialogNotice extends Dialog {
    static final String TAG = "DialogNotice";
    Button buttonYes;
    Button buttonNo;
    TextView tvMessage;

    public DialogNotice(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    public DialogNotice(Context context, int theme) {
        super(context, theme);
        init();
    }

    public DialogNotice(Context context) {
        super(context, R.style.dialog_confirm_notice);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
    }

    public void setOneButton() {
        buttonNo.setVisibility(Button.GONE);
        buttonYes.setText(R.string.button_ok);
        setCanceledOnTouchOutside(false);
    }

    private void init() {
        setContentView(R.layout.dialog_confirm_notice);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvMessage.setText(Html.fromHtml(setMessage()));
        buttonYes = (Button) findViewById(R.id.button_yes);
        buttonNo = (Button) findViewById(R.id.button_no);
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setActionYes();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                setActionNo();
            }
        });
        getWindow().getAttributes().windowAnimations = R.style.dialog_animation_zoom;
    }

    public void setNameButtonYes(int resource) {
        buttonYes.setText(resource);
    }

    public void setNameButtonNo(int resource) {
        buttonNo.setText(resource);
    }

    public void setNameButtonYes(String title) {
        buttonYes.setText(title);
    }

    public void setNameButtonNo(String title) {
        buttonNo.setText(title);
    }

    public abstract String setMessage();

    public abstract void setActionYes();

    public abstract void setActionNo();

    public void setSizeText(float f) {
        tvMessage.setTextSize(f);
    }

}
