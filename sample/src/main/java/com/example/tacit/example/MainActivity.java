package com.example.tacit.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tacitinnovations.core.api.model.TableOrder;
import com.tacitinnovations.core.sdk.MaeganSDK;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int CALL_BACK_CODE = 29;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btn_list)).setOnClickListener(this);
        (findViewById(R.id.btn_detail)).setOnClickListener(this);
        (findViewById(R.id.btn_one)).setOnClickListener(this);
        (findViewById(R.id.btn_order_history)).setOnClickListener(this);
        (findViewById(R.id.btn_barcode)).setOnClickListener(this);
        (findViewById(R.id.btn_credit_card)).setOnClickListener(this);
    }

    private void openListApp() {
        MaeganSDK.getInstance().presentRestaurantList(this, CALL_BACK_CODE);
    }
    private void openOneApp() {
        MaeganSDK.getInstance().presentRestaurantList(this, CALL_BACK_CODE);
    }

    private void openDetailRestaurant() {
        MaeganSDK.getInstance().presentRestaurant(this, 7145, 0, CALL_BACK_CODE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_list:
                openListApp();
                break;
            case R.id.btn_detail:
                openDetailRestaurant();
                break;
            case R.id.btn_one:
                openOneApp();
                break;
            case R.id.btn_order_history:
                break;
            case R.id.btn_barcode:
                break;
            case R.id.btn_credit_card:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CALL_BACK_CODE) {
            switch (resultCode) {
                case RESULT_OK: {
                    TableOrder order = data.getParcelableExtra(MaeganSDK.RESULT_TABLE_ORDER);
                    Log.d(TAG, "onActivityResult: order:" + order);
                    break;
                }
                case RESULT_CANCELED: {
                    if(data!=null && data.getExtras().containsKey(MaeganSDK.RESULT_ERROR_CODE)){
                        Log.d(TAG, "onActivityResult: error code:"
                                + data.getExtras().getInt(MaeganSDK.RESULT_ERROR_CODE));
                    }
                    break;
                }
            }
        }
    }
}
