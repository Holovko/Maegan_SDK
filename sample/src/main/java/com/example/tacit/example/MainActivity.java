package com.example.tacit.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tacitinnovations.core.api.model.TableOrder;
import com.tacitinnovations.core.sdk.MaeganSDK;
import com.tacitinnovations.core.sdk.app.SDKError;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int CALL_BACK_CODE = 29;
    private static final int BRAND_ID = 460;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Solution for SDK start customer activity
        Bundle extras = getIntent().getExtras();
        if(extras !=null && extras.containsKey(MaeganSDK.RESULT_CODE)){
            int resultCode = extras.getInt(MaeganSDK.RESULT_CODE);
            switch (resultCode){
                case RESULT_OK: {
                    TableOrder order = getIntent().getParcelableExtra(MaeganSDK.RESULT_DATA);
                    Log.d(TAG, "onCreate: callback:" + order);
                    break;
                }
            }
        }

        (findViewById(R.id.btn_list)).setOnClickListener(this);
        (findViewById(R.id.btn_detail)).setOnClickListener(this);
        (findViewById(R.id.btn_one)).setOnClickListener(this);
        (findViewById(R.id.btn_order_history)).setOnClickListener(this);
        (findViewById(R.id.btn_barcode)).setOnClickListener(this);
        (findViewById(R.id.btn_credit_card)).setOnClickListener(this);
        (findViewById(R.id.btn_cards)).setOnClickListener(this);
        (findViewById(R.id.btn_list_permissions)).setOnClickListener(this);
    }

    private void openListApp() {
        MaeganSDK.getInstance().presentRestaurantList(this,BRAND_ID, CALL_BACK_CODE);
    }
    private void openOneBrandApp() {
        MaeganSDK.getInstance().presentRestaurantList(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void openDetailRestaurant() {
        MaeganSDK.getInstance().presentRestaurant(this, 7145, 0, BRAND_ID, CALL_BACK_CODE);
    }

    private void presentOrderHistory() {
        MaeganSDK.getInstance().presentOrderHistory(this,BRAND_ID, CALL_BACK_CODE);
    }

    private void presentCreditCardSelect() {
        MaeganSDK.getInstance().presentCreditCardSelect(this,BRAND_ID, CALL_BACK_CODE);
    }

    private void presentBarcode() {
        MaeganSDK.getInstance().presentBarcode(this,BRAND_ID, CALL_BACK_CODE);
    }

    private void presentCards() {
        MaeganSDK.getInstance().presentGiftCards(this, BRAND_ID, CALL_BACK_CODE);
    }



    private void getListPermissions() {
        String[] permissions = MaeganSDK.getListOfRequiredPermission();
        for (String permission : permissions) {
            Log.d(TAG, "getListPermissions: permission = "+permission);
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
                    if(data!=null && data.getExtras().containsKey(MaeganSDK.RESULT_ERROR)){
                        SDKError error = data.getExtras().getParcelable(MaeganSDK.RESULT_ERROR);
                        if(error!=null) {
                            Log.d(TAG, "onActivityResult: error code:" + error.getCode());
                            Log.d(TAG, "onActivityResult: error message:" + error.getMessage());
                        }
                    }
                    break;
                }
            }
        }
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
                openOneBrandApp();
                break;
            case R.id.btn_order_history:
                presentOrderHistory();
                break;
            case R.id.btn_credit_card:
                presentCreditCardSelect();
                break;
            case R.id.btn_barcode:
                presentBarcode();
                break;
            case R.id.btn_list_permissions:
                getListPermissions();
                break;
            case R.id.btn_cards:
                presentCards();
                break;
        }
    }
}
