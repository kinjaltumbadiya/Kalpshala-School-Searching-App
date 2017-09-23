package com.example.afreen.kalpshala.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afreen.kalpshala.MainActivity;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.customClass.AppController;
import com.example.afreen.kalpshala.databasehandler.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

   // private CallbackManager mcallbackmanager;
    private ProgressDialog mProgressDialog;
  //  private FacebookCallback<LoginResult> mCallback;
    TextView lg_forget, lg_signup;
    EditText lg_password;
    EditText lg_email;
    Button lg_login;
    //LoginButton lg_fblogin;
    String email;
    String password;
   // private ProfileTracker mProfileTracker;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
   // SignInButton signInButton;
   // private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 420;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
     */   setContentView(R.layout.activity_login);

        //   FacebookSdk.sdkInitialize(this.getApplicationContext());
        //   mcallbackmanager = CallbackManager.Factory.create();

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        boolean login_status = sharedPreferences.getBoolean("login_status", false);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (login_status) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            LoginActivity.this.finish();
        } else {
            editor.putBoolean("login_status", false);
            editor.apply();
        }
        lg_signup = (TextView) findViewById(R.id.lg_signup);
        lg_forget = (TextView) findViewById(R.id.lg_forget);
        lg_login = (Button) findViewById(R.id.lg_login);
        //     lg_fblogin = (LoginButton) findViewById(R.id.lg_fblogin);

//        lg_fblogin.setReadPermissions(Arrays.asList(
        //"public_profile", "email", "user_birthday", "user_friends"));
        lg_email = (EditText) findViewById(R.id.lg_email);
        lg_password = (EditText) findViewById(R.id.lg_password);
        //      signInButton = (SignInButton) findViewById(R.id.lg_googlelogin);
        //setGooglePlusButtonText(signInButton, "Login with Google");
        lg_forget.setOnClickListener(this);
        lg_login.setOnClickListener(this);
        lg_signup.setOnClickListener(this);
    }
    //    signInButton.setOnClickListener(this);

      /*//  mCallback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("onSuccess");
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                String accessToken = loginResult.getAccessToken().getToken();
                Log.i("accessToken", accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.i("LoginActivity", response.toString());
                        // Get facebook data from login
                        Bundle bFacebookData = getFacebookData(object);
                        Log.i("InfoProfile", bFacebookData.get("first_name") + "," + bFacebookData.get("last_name") + "," + bFacebookData.get("email") + "," + bFacebookData.get("profile_pic"));
                        progressDialog.dismiss();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        String fname = bFacebookData.get("first_name").toString();
                        String lname = bFacebookData.get("last_name").toString();
                     //   String email = bFacebookData.get("email").toString();

                        String profile_pic = bFacebookData.get("profile_pic").toString();
                        editor.putString("fname", fname);
                        editor.putString("lname", lname);
                        editor.putString("email", email);
                        editor.putString("profile_pic", profile_pic);
                        editor.putBoolean("login_status", true);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                LoginManager.getInstance().logOut();
                progressDialog.dismiss();
            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("onError");
                Log.v("LoginActivity", e.getCause().toString());
                progressDialog.dismiss();

            }
        };

        lg_fblogin.registerCallback(mcallbackmanager, mCallback);
        initializeGPlusSettings();
    }*/

   /* private void initializeGPlusSettings() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }*/

   /* protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                tv.setPadding(0, 0, 20, 0);
                return;
            }
        }
    }*/

  /*  private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

   /* private void handleGPlusSignInResult(GoogleSignInResult result) {
        Log.d("", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            //Fetch values
            String personName = acct.getGivenName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String familyName = acct.getFamilyName();

            Log.e("", "Name: " + personName +
                    ", email: " + email +
                    ", Image: " + personPhotoUrl +
                    ", Family Name: " + familyName);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("fname", personName);
            editor.putString("lname", familyName);
            editor.putString("email", email);
            editor.putString("profile_pic", personPhotoUrl);
            editor.putBoolean("login_status", true);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();

        } else {
            Log.e("","Not Found");
        }
    }*/


    protected void onStart() {
        super.onStart();
        /*OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {

            Log.d("", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleGPlusSignInResult(result);
        } else {

            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleGPlusSignInResult(googleSignInResult);
                }
            });
        }*/
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=200");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        } catch (JSONException e) {
            Log.d("error", "Error parsing JSON");
            return null;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.lg_login:
                if (lg_email.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter email", Toast.LENGTH_SHORT).show();
                } else if (lg_password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValidEmail(lg_email.getText().toString().trim())) {

                        if (lg_password.getText().toString().trim().length() >= 5) {
                            email = lg_email.getText().toString().trim();
                            password = lg_password.getText().toString().trim();
                           // Toast.makeText(getApplicationContext(), "login clicked", Toast.LENGTH_SHORT).show();
                            logindb();
                        } else {
                            Toast.makeText(getApplicationContext(), "password legth is atleast 5", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.lg_forget:
                break;
            case R.id.lg_signup:
                Intent i = new Intent(LoginActivity.this, Registration.class);
                startActivity(i);
                LoginActivity.this.finish();
                break;
           /* case R.id.lg_googlelogin:
                signIn();
                break;*/
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public void logindb() {
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Login check...");
            progressDialog.setCancelable(true);
            {
                progressDialog.show();
                StringRequest strReq = new StringRequest(Request.Method.POST, AppConfig.URL_login, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d("Response", s.toString());
                        try {
                            JSONObject jObj = new JSONObject(s);
                            boolean error = jObj.getBoolean("error");

                            if (!error) {
                                Toast.makeText(getApplicationContext(), "Login successfully.", Toast.LENGTH_LONG).show();
                                progressDialog.hide();

                                String fname = jObj.getString("fname");
                                String lname = jObj.getString("lname");

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("fname", fname);
                                editor.putString("lname", lname);
                                editor.putString("email", email);

                                //editor.putString("profile_pic", personPhotoUrl);
                                editor.putBoolean("login_status", true);
                                editor.apply();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Called", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                String errormsg = jObj.getString("error_msg");
                                Toast.makeText(getApplicationContext(), errormsg + "", Toast.LENGTH_LONG).show();
                                progressDialog.hide();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Json error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.hide();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getApplicationContext(), "volleyError error:" + volleyError.getMessage(), Toast.LENGTH_LONG).show();
                        progressDialog.hide();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("email", email);
                        params.put("password", password);

                        return params;
                    }
                };
                AppController.getInstance().addToRequestQueue(strReq);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGPlusSignInResult(result);
        } else {
            mcallbackmanager.onActivityResult(requestCode, resultCode, data);
        }*/
    }
}
