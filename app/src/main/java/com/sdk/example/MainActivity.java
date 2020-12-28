package com.sdk.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdk.ecommerce.Application;
import com.sdk.ecommerce.data.Channel;
import com.sdk.ecommerce.data.Customer;
import com.sdk.ecommerce.data.Language;
import com.sdk.ecommerce.presentation.custom.PaymentViewCustom;
import com.sdk.ecommerce.util.Utils;
import com.sdk.example.data.authorization.request.AuthorizationRequest;
import com.sdk.example.data.authorization.request.Device;
import com.sdk.example.data.authorization.request.Header;
import com.sdk.example.data.authorization.request.Merchant;
import com.sdk.example.data.authorization.request.Order;
import com.sdk.example.data.authorization.response.AuthorizationResponse;
import com.sdk.example.data.savetoken.SaveTokenRequest;
import com.sdk.example.data.token.response.AccessTokenResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static String ENDPOINT = "https://apiqa.ecore.mobi";
    private static String TENANT = "aunatest";
    private static String MERCHANT = "928";

    private String mAmount = "";
    private String mPurchaseNumber = "";

    EditText etAmount;
    EditText etPurchaseNumber;
    Button btnPay;
    RelativeLayout viewLoading;
    Application SDKapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){

        viewLoading = findViewById(R.id.loading_view);

        etAmount = findViewById(R.id.et_amount);
        etPurchaseNumber = findViewById(R.id.et_purchase_number);
        btnPay = findViewById(R.id.btn_pay);

        AndroidNetworking.initialize(this);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment();
            }
        });

    }

    private void payment() {

        mAmount = etAmount.getText().toString();
        if (mAmount == "") {
            etAmount.setError("Ingresar un monto a pagar.");
            return;
        }

        mPurchaseNumber = etPurchaseNumber.getText().toString();
        if (mPurchaseNumber == "") {
            etPurchaseNumber.setError("Ingresar un número de pedido.");
            return;
        }

        ApiSecurity();
    }

    private void ApiSecurity() {

        viewLoading.setVisibility(View.VISIBLE);

        String userName = "pgaldamez@quiputech.com";
        String password = "Alemania#2006";
        String base = userName + ":" + password;
        String authHeader = "Basic " + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        NetworkService service = new NetworkService(this);

        Call<AccessTokenResponse> call = service.getAPI().callAccessToken(authHeader, TENANT, MERCHANT);
        call.enqueue(new Callback<AccessTokenResponse>() {

            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "onResponse: "+ response.body().toString());

                    viewLoading.setVisibility(View.GONE);

                    //configuracion del SDK
                    PaymentViewCustom sdkCustom = new PaymentViewCustom();
                    sdkCustom.setLogo(R.drawable.logo_visa);
                    sdkCustom.setLogoScaleType(PaymentViewCustom.ScaleType.CENTER_CROP);
                    sdkCustom.setBackgroundColor(R.color.light_green_600);
                    sdkCustom.setButtonPayColor(R.color.light_green_800);
                    sdkCustom.setEnableCVV(true);
                    sdkCustom.setSaveCard(true);
                    Application.setCustom(sdkCustom);

                    //datos del cliente
                    Customer customer = new Customer();
                    customer.setFirstName("Farid");
                    customer.setLastName("Gamarra Floreano");
                    customer.setEmail("farid@hotmail.com");
                    customer.setPhone("957798775");

                    // MDDS
                    HashMap<String, String> data = new HashMap<String, String>();
                    data.put("MDD4", "jordonez@otcperu.com");
                    data.put("MDD21", "0");              //Cliente Frecuente: 0= NO 1=SI
                    data.put("MDD30", "40116061");       //ID del beneficiario
                    data.put("MDD32", "JD40116061");     //Código o ID del cliente
                    //data.put("MDD33", "DNI");          //Tipo de documento
                    //data.put("MDD34", "40116061");     //Numero de documento
                    data.put("MDD63", "DNI");            //Tipo de documento
                    data.put("MDD65", "45678901");       //Número de documento del beneficiario
                    data.put("MDD75", "Invitado");       //Tipo de registro del cliente
                    data.put("MDD77", "100");            //Días desde registro del cliente

                    // si se manda token se levanta formulario para usar tokinId
                    //customer.setTokenId("4041659888887904");
                    //customer.setCardMask("4000 **** **** 0002");
                    Application.setCustomer(customer);

                    Application.setApplicationContext(MainActivity.this);
                    Application.setLanguage(Language.SPANISH);
                    Application.setChannel(Channel.MOBILE);
                    Application.setEndpoint(ENDPOINT);
                    Application.setTenantId(TENANT);
                    Application.setMerchantId(MERCHANT);
                    Application.setMDD(data);
                    Application.setPublishableKey(response.body().getPublishableKey());

                    SDKapp = Application.getInstance();
                    SDKapp.openPaymentActivity(MainActivity.this, mAmount, mPurchaseNumber);

                }else {
                    try {
                        Log.i(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        viewLoading.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                viewLoading.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }

    private void ApiAuthorization(String data) {

        try {
            JSONObject json_contact = new JSONObject(data);
            final String binNumber = json_contact.getString("bin");
            final String lastFour = json_contact.getString("lastFourDigits");
            String channel = json_contact.getString("channel");
            final String transactionToken = json_contact.getString("transactionToken");

            Log.i(TAG, "Authorization: " + transactionToken + " - " + channel);

            AuthorizationRequest authorizationRequest = new AuthorizationRequest();

            Header header = new Header();
            header.setExternalId(UUID.randomUUID().toString());
            authorizationRequest.setHeader(header);

            Merchant merchant = new Merchant();
            merchant.setMerchantId(Application.getMerchantId());
            authorizationRequest.setMerchant(merchant);

            Device device = new Device();
            device.setTerminalId("1");
            device.setCaptureType("manual");
            device.setUnattended(false);
            authorizationRequest.setDevice(device);

            Order order = new Order();
            order.setChannel(Application.getChannel().name());
            order.setAmount(Utils.parseAmount(etAmount.getText().toString()));
            order.setCurrency(Application.getCurrency().name());
            order.setCountable(true);
            order.setPurchaseNumber(etPurchaseNumber.getText().toString());
            order.setTransactionToken(transactionToken);
            authorizationRequest.setOrder(order);

            viewLoading.setVisibility(View.VISIBLE);

            NetworkService service = new NetworkService(this);
            Call<JsonObject> call = service.getAPI().callAuthorization(
                    Application.getAccessToken(),
                    Application.getTenantId(),
                    authorizationRequest);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    viewLoading.setVisibility(View.GONE);

                    if (response.isSuccessful()) {

                        Gson gson = new Gson();
                        AuthorizationResponse authorizationResponse = gson.fromJson(response.body() ,AuthorizationResponse.class);

                        SaveTokenRequest request = new SaveTokenRequest();
                        request.setBin(binNumber);
                        request.setLastFourDigits(lastFour);
                        request.setToken(transactionToken);
                        request.setCardExpirationYear(authorizationResponse.getCustomFields().getCARDEXPIRATIONYEAR());
                        request.setCardExpirationMonth(authorizationResponse.getCustomFields().getCARDEXPIRATIONMONTH());
                        request.setFamilyGroup("1111222233");
                        request.setReferenceNumber("1235s");
                        request.setMaskCardNumber(binNumber + "*******" + lastFour);

                        saveToken(request);
                    }else{
                        try {
                            dialogResult(MainActivity.this, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    viewLoading.setVisibility(View.GONE);

                    t.printStackTrace();

                    Log.i(TAG, "onError: " + t.getMessage());
                    dialogResult(MainActivity.this, t.getMessage());
                }
            });


        } catch (JSONException e) {
            Log.e(TAG, "Authorization: ", e);
        }

    }

    private void saveToken(SaveTokenRequest request) {

        viewLoading.setVisibility(View.VISIBLE);
        //for save token
        Network2Service service = new Network2Service(this);
        service.getAPI().callSaveToken(request).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                viewLoading.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    dialogResult(MainActivity.this, response.body().toString());
                }else{
                    try {
                        dialogResult(MainActivity.this, response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                viewLoading.setVisibility(View.GONE);

                t.printStackTrace();

                Log.i(TAG, "onError: " + t.getMessage());
                dialogResult(MainActivity.this, t.getMessage());
            }
        });

    }

    private void dialogResult(Activity context, String msg){
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("SDK - ECOMMERCE")
                .setMessage(msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        dialog1.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
        TextView textView = dialog.findViewById(android.R.id.message);
        textView.setScroller(new Scroller(context));
        textView.setVerticalScrollBarEnabled(true);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String resultString = "";

        if (requestCode == Application.REQUEST_PAYMENT) {

            if (data != null) {
                if (resultCode == Activity.RESULT_OK) {

                    resultString = data.getExtras().getString(Application.KEY_SUCCESS);

                    ApiAuthorization(resultString);
                    Log.i(TAG, "onActivityResult: " + resultString);

                } else {

                    resultString = data.getExtras().getString(Application.KEY_ERROR);
                    dialogResult(MainActivity.this, resultString);
                }
            }else {
                Toast.makeText(this, "Cancelado....", Toast.LENGTH_SHORT).show();
            }

        }
    }


}