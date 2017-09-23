package com.example.afreen.kalpshala.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afreen.kalpshala.R;
import com.example.afreen.kalpshala.customClass.AppController;
import com.example.afreen.kalpshala.databasehandler.AppConfig;
import com.example.afreen.kalpshala.utils.GalleryUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Registration extends AppCompatActivity implements View.OnClickListener {

    TextView txt_login;
    EditText edt_fname, edt_lname, edt_email, edt_password, edt_cpassword;
    Button btn_register;
    String email, password, fname, lname;
    StringRequest strReq;
    ImageView img_camera, img_profile_pic;
    int PICK_PHOTO_FOR_AVATAR = 100;
    private final int RESULT_CROP = 400;
    private final int GALLERY_ACTIVITY_CODE = 200;
    SharedPreferences sharedPreferences;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);
        txt_login = (TextView) findViewById(R.id.txt_login);
        edt_fname = (EditText) findViewById(R.id.edt_fname);
        edt_lname = (EditText) findViewById(R.id.edt_lname);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_cpassword = (EditText) findViewById(R.id.edt_cpassword);
        btn_register = (Button) findViewById(R.id.btn_register);
      /*  img_camera = (ImageView) findViewById(R.id.img_camera);
        img_profile_pic = (ImageView) findViewById(R.id.img_profile_pic);*/
//        img_camera.setOnClickListener(this);
        txt_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_login:
                Intent i = new Intent(Registration.this, LoginActivity.class);
                startActivity(i);
                Registration.this.finish();
                break;
           /* case R.id.img_camera:
                pickImage();
                break;*/
            case R.id.btn_register:
                if (edt_fname.getText().toString().trim().isEmpty() && edt_lname.getText().toString().trim().isEmpty() && edt_lname.getText().toString().trim().isEmpty() && edt_email.getText().toString().trim().isEmpty() && edt_password.getText().toString().trim().isEmpty() && edt_cpassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please, fill out all details first", Toast.LENGTH_SHORT).show();

                } else if (edt_fname.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter first name", Toast.LENGTH_SHORT).show();
                } else if (edt_lname.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter last name", Toast.LENGTH_SHORT).show();

                } else if (edt_email.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter email address", Toast.LENGTH_SHORT).show();

                } else if (edt_password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_SHORT).show();

                } else if (edt_cpassword.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter confirm password", Toast.LENGTH_SHORT).show();

                } else {
                    if (isValidEmail(edt_email.getText().toString())) {
                        if (edt_password.getText().toString().trim().length() >= 5) {
                            if (edt_password.getText().toString().trim().equals(edt_cpassword.getText().toString().trim())) {
                                email = edt_email.getText().toString().trim();
                                fname = edt_fname.getText().toString().trim();
                                lname = edt_lname.getText().toString().trim();
                                password = edt_password.getText().toString().trim();
                                if (uploadData()) {
                                    Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "password mismatched", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "password legth is atleast 5", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "email address is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    public void pickImage() {

        Intent gallery_Intent = new Intent(getApplicationContext(), GalleryUtil.class);

        startActivityForResult(gallery_Intent, GALLERY_ACTIVITY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_ACTIVITY_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String picturePath = data.getStringExtra("picturePath");
                //perform Crop on the Image Selected from Gallery
                Log.d("Response", "Croppdfs gfsfg");

                performCrop(picturePath);
            }
        }

        if (requestCode == RESULT_CROP) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");
                // Set The Bitmap Data To ImageView
                img_profile_pic.setImageBitmap(selectedBitmap);
                img_profile_pic.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }

    }

    private void performCrop(String picUri) {
        try {
            //Start Crop Activity

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            File f = new File(picUri);
            Uri contentUri = Uri.fromFile(f);

            cropIntent.setDataAndType(contentUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 280);
            cropIntent.putExtra("outputY", 280);

            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            Log.d("Response", "Croppingggggggggggg");

            startActivityForResult(cropIntent, RESULT_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public static Bitmap getCroppedBitmap(Bitmap bitmap) {

        final int width = 80;
        final int height = bitmap.getHeight();
        final Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        final Path path = new Path();
        path.addCircle(
                (float) (width / 2)
                , (float) (height / 2)
                , (float) Math.min(width, (height / 2))
                , Path.Direction.CCW);

        final Canvas canvas = new Canvas(outputBitmap);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return outputBitmap;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean uploadData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering..");
        progressDialog.setCancelable(true);
        {
            progressDialog.show();
            strReq = new StringRequest(Request.Method.POST, AppConfig.URL_register, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.d("Response", s.toString());
                    try {
                        JSONObject jObj = new JSONObject(s);
                        boolean error = jObj.getBoolean("error");
                        if (!error) {
                            Toast.makeText(getApplicationContext(), "Upload successfully.", Toast.LENGTH_LONG).show();
                            progressDialog.hide();
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("fname", fname);
                            editor.putString("lname", lname);
                            editor.putString("email", email);
                            //  editor.putString("profile_pic", profile_pic);
                            editor.putBoolean("login_status", true);
                            editor.apply();

                            Intent intent = new Intent(Registration.this, LoginActivity.class);
                            startActivity(intent);
                            Registration.this.finish();
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
                    params.put("fname", fname);
                    params.put("lname", lname);
                    params.put("password", password);
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(strReq);
        }

        return false;
    }
}

