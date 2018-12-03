package com.example.picmymedcode.View;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.picmymedcode.Controller.PicMyMedApplication;
import com.example.picmymedcode.Controller.PicMyMedController;
import com.example.picmymedcode.Model.CareProvider;
import com.example.picmymedcode.Model.Patient;
import com.example.picmymedcode.Model.Problem;
import com.example.picmymedcode.Model.Record;
import com.example.picmymedcode.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class RecordFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SearchRecordAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManage;
    public ArrayList<Problem> problemArrayList;
    public ArrayList<Record> allRecordArrayList = new ArrayList<Record>(),filteredDataList;
    public ArrayList<Record> filteredDataListTwo = new ArrayList<Record>();
    public SearchView searchView;
    public Button searchLocation;

    Patient patient;
    int PLACE_PICKER_REQUEST = 1;
    View v;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_record, container, false);

        /*for (int i = 0; i<problemArrayList.size(); i++){
            AllrecordArrayList.addAll(problemArrayList.get(i).getRecordList());
        }*/
        manageRecyclerview();
        searchView = v.findViewById(R.id.searchRecord);
        for (Problem problem : problemArrayList) {
            if (problem.getRecordList() != null) {
                allRecordArrayList.addAll(problem.getRecordList());
            }
        }



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("query", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredDataList = filter(allRecordArrayList, newText);
                mAdapter.setFilter(filteredDataList);
                return true;
            }
        });
        searchLocation = v.findViewById(R.id.record_search_location_button);

        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {

                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Log.d("query", query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        filteredDataList = filter(filteredDataListTwo, newText);
                        mAdapter.setFilter(filteredDataList);
                        return true;
                    }
                });
                //PicMyMedController.searchForProbByBodyLocation(problemArrayList,);
            }
        });


        return v;
    }

    public void manageRecyclerview(){


        if (PicMyMedApplication.getLoggedInUser().isPatient()) {
            problemArrayList = ((Patient)PicMyMedApplication.getLoggedInUser()).getProblemList();
        } else {
            problemArrayList = ((Patient)PicMyMedController.getPatient(CareProviderProblemActivity.name)).getProblemList();
        }

        mRecyclerView = v.findViewById(R.id.fragment_record_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManage = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManage);
        mAdapter = new SearchRecordAdapter(getContext(),allRecordArrayList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private ArrayList<Record> filter(ArrayList<Record> recordArrayList, String newText) {
        newText=newText.toLowerCase();
        String text;
        String text2;
        filteredDataList=new ArrayList<>();
        for(Record dataFromDataList:recordArrayList){
            text=dataFromDataList.getDescription().toLowerCase();
            text2=dataFromDataList.getTitle().toLowerCase();
            if(text.contains(newText) || text2.contains(newText)){
                filteredDataList.add(dataFromDataList);
            }
        }

        return filteredDataList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {

            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(getActivity(),data);
                String locationName = place.getName().toString();
                Location location = new Location("");
                double lat = place.getLatLng().latitude;
                double lon = place.getLatLng().longitude;
                location.setLatitude(lat);
                location.setLongitude(lon);


                filteredDataListTwo = PicMyMedController.searchForRecByGeolocation(problemArrayList,location);
                searchLocation.setText(locationName);
                mAdapter =  new SearchRecordAdapter(getContext(), filteredDataListTwo);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}