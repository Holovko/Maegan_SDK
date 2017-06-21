package com.example.tacit.example;

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

        //handle callback
        Bundle extras = getIntent().getExtras();
        if(extras !=null && extras.containsKey(MaeganSDK.RESULT_CODE)){
            int callBackCode = extras.getInt(MaeganSDK.REQUEST_CALLBACK);
            int resultCode = extras.getInt(MaeganSDK.RESULT_CODE);
            switch (resultCode){
                case RESULT_OK: {
                    if(callBackCode == CALL_BACK_CODE) {
                        TableOrder order = getIntent().getParcelableExtra(MaeganSDK.RESULT_DATA);
                        Log.d(TAG, "onCreate: "+order);
                    }
                    break;
                }
            }
        }
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
}
