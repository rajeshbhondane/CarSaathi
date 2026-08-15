package com.app.carsaathi;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.carsaathi.Pojo.StoreItem;
import com.app.carsaathi.adapter.CarAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class StoreFragment extends Fragment {
    RecyclerView rvStore ;
    ArrayList<StoreItem> items;
    CarAdapter adapter ;
    SearchView sv ;
    TextView contentDescription;
    ImageButton ibFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        rvStore = view.findViewById(R.id.rvStore);
        contentDescription = view.findViewById(R.id.contentDescription);
        sv = view.findViewById(R.id.sv);
        ibFilter = view.findViewById(R.id.ibFilter);
        items = new ArrayList<>();
        adapter = new CarAdapter(getContext(),items);
        rvStore.setAdapter(adapter);
        rvStore.setLayoutManager(new LinearLayoutManager(getContext()));
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(Urls.getAllDetails,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray array = response.getJSONArray("getdata");
                    for (int i= 0 ; i< array.length();i++){
                        JSONObject main = array.getJSONObject(i);
                        String name = main.getString("carname");
                        String image = main.getString("image");
                        int price = main.getInt("price");
                        String km = main.getString("mileage");
                        String location = main.getString("distance");
                        String type = main.getString("details");

                        items.add(new StoreItem(image,name,price,type,km,location));
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getContext(), "server error", Toast.LENGTH_SHORT).show();
            }
        });


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                searchItem(query);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        ibFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilter();
            }
        });
        return view ;
    }

    private void showFilter() {
        String[] options = {
                "Price: Low to High",
                "Price: High to Low",
                "Mileage: Low to High",
                "Mileage: High to Low"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Filter");
        builder.setItems(options,(dialog,which) ->{
            switch (which)
            {
                case 0:
                    sortByPriceLowToHigh();
                    break;

                case 1:
                    sortByPriceHighToLow();
                    break;

                case 2:
                    sortByMileageLowToHigh();
                    break;

                case 3:
                    sortByMileageHighToLow();
                    break;
            }

        });
        builder.show();
    }

    private void sortByMileageHighToLow() {
        items.sort((car1, car2) ->
                Integer.compare(
                        Integer.parseInt(car2.getKm()),
                        Integer.parseInt(car1.getKm())
                ));

        adapter.notifyDataSetChanged();
    }

    private void sortByMileageLowToHigh() {
        items.sort((car1, car2) ->
                Integer.compare(
                        Integer.parseInt(car1.getKm()),
                        Integer.parseInt(car2.getKm())
                ));

        adapter.notifyDataSetChanged();
    }

    private void sortByPriceHighToLow() {
        items.sort((car1,car2)->
                Integer.compare(car2.getPrice(), car1.getPrice()));
        adapter.notifyDataSetChanged();
    }

    private void sortByPriceLowToHigh() {
        items.sort((car1,car2)->
                Integer.compare(car1.getPrice(), car2.getPrice()));
        adapter.notifyDataSetChanged();
    }

    private void searchItem(String query) {
        ArrayList<StoreItem> searchList = new ArrayList<>();
        searchList.clear();
        for(StoreItem obj :items){
            if(obj.getCarName().toLowerCase().contains(query.toLowerCase())) {
                searchList.add(obj);
            }
        }
        if(searchList.isEmpty()){
            contentDescription.setText("Search not found");
            contentDescription.setVisibility(View.VISIBLE);
            rvStore.setVisibility(View.GONE);
        }else{
            contentDescription.setVisibility(View.GONE);
            rvStore.setVisibility(View.VISIBLE);

            CarAdapter adapter = new CarAdapter(getContext(),searchList);
            rvStore.setAdapter(adapter);
        }
    }
}