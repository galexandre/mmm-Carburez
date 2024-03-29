package fr.istic.project.gasLocation.activities;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.adapter.CustomViewPager;
import fr.istic.project.gasLocation.controller.DownloadController;
import fr.istic.project.gasLocation.dao.DatabaseHelper;
import fr.istic.project.gasLocation.models.Station;
import fr.istic.project.gasLocation.services.DownloadService;

public class MainActivity extends FragmentActivity  implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * Current stations resulted of the research
     */
    static List<Station> currentStations;
    
    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    CustomViewPager mViewPager;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF8800"));     
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (CustomViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setPagingEnabled(false);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        
        //Database stuff
        DatabaseHelper helper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        // calling the station dao here
        currentStations = helper.getAllStationFromPartialPostalCodeWithGases("22%");
    }
    
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }
    
    
	public void openFilter(View v){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
	     
		 alertDialogBuilder.setTitle("Filtre");
		 alertDialogBuilder.setMessage("Ensemble des boutons de filtre");
		 //alertDialog.setContentView(R.layout.custom);
		 
		 // --> Filter
		 alertDialogBuilder.setPositiveButton("Filtrer",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// Filtrer les résultats
					//Intent positveActivity = new Intent(getApplicationContext(),
                      //     MainActivity.class);
					dialog.cancel();
		            //startActivity(positveActivity);	
				}
			  });
		 // --> Annuler
		 alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// Annuler et refermer la boite de dialogue
					dialog.cancel();
				}
			});
		 
		 AlertDialog alertDialog = alertDialogBuilder.create();
		 // show alert
		 alertDialog.show();
	}

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                	Fragment fragment1 = new ListSectionFragment();
                	Bundle args1 = new Bundle();
                	args1.putParcelableArrayList("currentStations", (ArrayList<? extends Parcelable>) currentStations);
                	args1.putInt(ListSectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment1.setArguments(args1);
                    return fragment1;

                default:
                    Fragment fragment = new MapSectionFragment();
                    Bundle args = new Bundle();
                    args.putParcelableArrayList("currentStations", (ArrayList<? extends Parcelable>) currentStations);
                    args.putInt(MapSectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int position) {
        	String retour ="";
        	if (position == 0 ){
        		retour ="Stations";
        	}
        	if (position == 1){
        		retour ="Carte";
        	}
            return retour;
        }
        
    }

}
