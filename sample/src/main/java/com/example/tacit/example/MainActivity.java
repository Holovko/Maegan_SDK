package com.example.tacit.example;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tacitinnovations.core.core.api.model.TableOrder;
import com.tacitinnovations.core.sdk.MGNExchangeAdditionalPaymentData;
import com.tacitinnovations.core.sdk.MGNExchangeDiscount;
import com.tacitinnovations.core.sdk.MGNExchangePayment;
import com.tacitinnovations.core.sdk.MGNExchangePaymentData;
import com.tacitinnovations.core.sdk.MGNExchangePlaceOrderResult;
import com.tacitinnovations.core.sdk.MGNExchangeUserData;
import com.tacitinnovations.core.sdk.MGNOrderExchangeCalcTotals;
import com.tacitinnovations.core.sdk.MGNOrderExchangeOrder;
import com.tacitinnovations.core.sdk.MaeganSDK;
import com.tacitinnovations.core.sdk.app.SDKError;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int CALL_BACK_CODE = 29;
    private static final int BRAND_ID = 1563;
    private MGNOrderExchangeCalcTotals mMgnOrderExchangeCalcTotals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Solution for SDK start customer activity
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(MaeganSDK.RESULT_CODE)) {
            int resultCode = extras.getInt(MaeganSDK.RESULT_CODE);
            switch (resultCode) {
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
        (findViewById(R.id.btn_restaurant_menu)).setOnClickListener(this);
        (findViewById(R.id.btn_restaurant_receipt)).setOnClickListener(this);
        (findViewById(R.id.btn_edit_prepared_order)).setOnClickListener(this);
        (findViewById(R.id.btn_order_history)).setOnClickListener(this);
        (findViewById(R.id.btn_barcode)).setOnClickListener(this);
        (findViewById(R.id.btn_credit_card)).setOnClickListener(this);
        (findViewById(R.id.btn_cards)).setOnClickListener(this);
    }

    private void openListApp() {
        MaeganSDK.getInstance().presentRestaurantList(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void openOneBrandApp() {
        MaeganSDK.getInstance().presentRestaurantList(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void openDetailRestaurant() {
        MaeganSDK.getInstance().presentRestaurant(this, 7147, 0, BRAND_ID, CALL_BACK_CODE);
    }

    private void openRestaurantMenu() {
        MaeganSDK.getInstance().presentRestaurantMenu(this, 7147, "InRestaurantQuickOrder", 1, CALL_BACK_CODE);
    }

    private void openOrderReceipt() {
        if (mMgnOrderExchangeCalcTotals == null){
            Toast.makeText(this, "Please make an order first", Toast.LENGTH_SHORT).show();
            return;
        }
        MGNOrderExchangeOrder mgnOrderExchangeOrder = new MGNOrderExchangeOrder();
        mgnOrderExchangeOrder.setLocationId(mMgnOrderExchangeCalcTotals.getLocationId());
        mgnOrderExchangeOrder.setOrderType("InRestaurantQuickOrder");
        mgnOrderExchangeOrder.setOrderMenuItems(mMgnOrderExchangeCalcTotals.getOrderMenuItems());
        mgnOrderExchangeOrder.setUserGuid(mMgnOrderExchangeCalcTotals.getUserGuid());
        mgnOrderExchangeOrder.setUserData(new MGNExchangeUserData("Serena", "Laurence", "serena_df@test.com", "5555555555", "12345"));
        mgnOrderExchangeOrder.setTableReference("table RED");

        MGNExchangePaymentData mgnExchangePaymentData = new MGNExchangePaymentData();
        mgnExchangePaymentData.setExternalPaymentId("Payment ID");
        mgnExchangePaymentData.setExternalPaymentObject("HMAC");
        mgnExchangePaymentData.setTipAmount(1.5f);
        mgnExchangePaymentData.setTotalAmount(6.0f);
        List<MGNExchangePayment> mgnExchangePayments = new ArrayList<>();
        MGNExchangePayment payment1 = new MGNExchangePayment("Venutize", 3.50f, new MGNExchangeAdditionalPaymentData("Visa"));
        MGNExchangePayment payment2 = new MGNExchangePayment("Venutize", 4.0f, new MGNExchangeAdditionalPaymentData("MasterCard"));
        mgnExchangePayments.add(payment1);
        mgnExchangePayments.add(payment2);
        mgnExchangePaymentData.setPayments(mgnExchangePayments);

        mgnOrderExchangeOrder.setPaymentData(mgnExchangePaymentData);
        MGNExchangeDiscount mgnExchangeDiscount = new MGNExchangeDiscount("discountId", "Cool Discount", 3.15f);
        List<MGNExchangeDiscount> discountList = new ArrayList<>();
        discountList.add(mgnExchangeDiscount);
        mgnOrderExchangeOrder.setDiscounts(discountList);
        mgnOrderExchangeOrder.setDigitalReceiptNotes("Some digital Receipt Notes");

        MaeganSDK.getInstance().placeOrder(this, mgnOrderExchangeOrder, CALL_BACK_CODE);
    }

    private void editPreparedOrder() {
        MaeganSDK.getInstance().editOrderDetails(this, 7147, "InRestaurantQuickOrder", 1, CALL_BACK_CODE);
    }

    private void presentOrderHistory() {
        MaeganSDK.getInstance().presentOrderHistory(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void presentCreditCardSelect() {
        MaeganSDK.getInstance().presentCreditCardSelect(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void presentBarcode() {
        MaeganSDK.getInstance().presentBarcode(this, BRAND_ID, CALL_BACK_CODE);
    }

    private void presentCards() {
        MaeganSDK.getInstance().presentGiftCards(this, BRAND_ID, CALL_BACK_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CALL_BACK_CODE) {
            switch (resultCode) {
                case RESULT_OK: {
                    if (data != null) {
                        proceedWithTheResultFromSdk(data);
                    }
                    break;
                }
                case RESULT_CANCELED: {
                    if (data != null && data.hasExtra(MaeganSDK.RESULT_ERROR)) {
                        SDKError error = data.getParcelableExtra(MaeganSDK.RESULT_ERROR);
                        if (error != null) {
                            Log.d(TAG, "onActivityResult: error code:" + error.getCode());
                            Log.d(TAG, "onActivityResult: error message:" + error.getMessage());
                        }
                    }
                    break;
                }
            }
        }
    }

    /**
     * Handle the success result from SDK
     *
     * @param intent
     */
    private void proceedWithTheResultFromSdk(@NonNull Intent intent) {
        if (intent.hasExtra(MaeganSDK.RESULT_TABLE_ORDER)) {
            final TableOrder order = intent.getParcelableExtra(MaeganSDK.RESULT_TABLE_ORDER);
        }

        /**
         * in case of {@link #openRestaurantMenu()} and {@link #editPreparedOrder()}
         */
        if (intent.hasExtra(MaeganSDK.RESULT_PREPARE_ORDER)) {
            final MGNOrderExchangeCalcTotals preparedOrder = intent.getParcelableExtra(MaeganSDK.RESULT_PREPARE_ORDER);
            mMgnOrderExchangeCalcTotals = preparedOrder; // to prepare demo data for order receipt
        }

        /**
         * in case of {@link #openOrderReceipt()}
         */
        if (intent.hasExtra(MaeganSDK.RESULT_PLACE_ORDER)) {
            final MGNExchangePlaceOrderResult orderPlaceResult =  intent.getParcelableExtra(MaeganSDK.RESULT_PLACE_ORDER);
        }
        Log.d(TAG, "onActivityResult: order:" + intent.getExtras().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list:
                openListApp();
                break;
            case R.id.btn_detail:
                openDetailRestaurant();
                break;
            case R.id.btn_restaurant_menu:
                openRestaurantMenu();
                break;
            case R.id.btn_restaurant_receipt:
                openOrderReceipt();
                break;
            case R.id.btn_edit_prepared_order:
                editPreparedOrder();
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
            case R.id.btn_cards:
                presentCards();
                break;
        }
    }
}
