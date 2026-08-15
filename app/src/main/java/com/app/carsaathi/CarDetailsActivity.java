package com.app.carsaathi;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CarDetailsActivity extends AppCompatActivity {

    ImageView ivCar, ivSeller;
    ImageButton btnBack, btnFavorite;

    TextView tvCarName, tvPrice, tvOwner, tvYear, tvKm;
    TextView tvFuel, tvCondition, tvDescription;
    TextView tvSellerName, tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_details);

        ivCar = findViewById(R.id.ivCar);
        ivSeller = findViewById(R.id.ivSeller);

        btnBack = findViewById(R.id.btnBack);
        btnFavorite = findViewById(R.id.btnFavorite);

        tvCarName = findViewById(R.id.tvCarName);
        tvPrice = findViewById(R.id.tvPrice);
        tvOwner = findViewById(R.id.tvOwner);
        tvYear = findViewById(R.id.tvYear);
        tvKm = findViewById(R.id.tvKm);
        tvFuel = findViewById(R.id.tvFuel);
        tvCondition = findViewById(R.id.tvCondition);
        tvDescription = findViewById(R.id.tvDescription);
        tvSellerName = findViewById(R.id.tvSellerName);
        tvRating = findViewById(R.id.tvRating);

        btnBack.setOnClickListener(v -> finish());

        int carId = getIntent().getIntExtra("car_id", -1);

        if (carId != -1) {
            loadCarDetails(carId);
        } else {
            Toast.makeText(
                    this,
                    "Car ID not found",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    private void loadCarDetails(int carId) {

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("id", carId);

        client.post(
                Urls.CarDetails,
                params,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(
                            int statusCode,
                            Header[] headers,
                            JSONObject response) {

                        try {

                            JSONArray array =
                                    response.getJSONArray("getdata");

                            if (array.length() > 0) {

                                JSONObject car =
                                        array.getJSONObject(0);

                                String image =
                                        car.getString("image");

                                String carName =
                                        car.getString("carname");

                                int price =
                                        car.getInt("price");

                                String owner =
                                        car.getString("owner");

                                String year =
                                        car.getString("year");

                                String km =
                                        car.getString("km");

                                String fuel =
                                        car.getString("fuel");

                                String condition =
                                        car.getString("condition");

                                String description =
                                        car.getString("discription");

                                String sellerName =
                                        car.getString("sellername");

                                String sellerRating =
                                        car.getString("sellerrating");

                                tvCarName.setText(carName);
                                tvPrice.setText("₹" + price);
                                tvOwner.setText(owner);
                                tvYear.setText(year);
                                tvKm.setText(km);
                                tvFuel.setText(fuel);
                                tvCondition.setText(condition);
                                tvDescription.setText(description);
                                tvSellerName.setText(sellerName);
                                tvRating.setText("★ " + sellerRating);

                                Glide.with(CarDetailsActivity.this)
                                        .load(image)
                                        .placeholder(R.drawable.placeholder)
                                        .error(R.drawable.error)
                                        .into(ivCar);
                            }

                        } catch (JSONException e) {

                            Toast.makeText(
                                    CarDetailsActivity.this,
                                    "Data error",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }

                    @Override
                    public void onFailure(
                            int statusCode,
                            Header[] headers,
                            Throwable throwable,
                            JSONObject errorResponse) {

                        Toast.makeText(
                                CarDetailsActivity.this,
                                "Server error",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );
    }
}