package fr.istic.project.gasLocation.activities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.controller.Controller;
import fr.istic.project.gasLocation.models.DatabaseHelper;
import fr.istic.project.gasLocation.models.Gas;
import fr.istic.project.gasLocation.models.Station;
import fr.istic.project.gasLocation.services.ParserImpl;

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
    ViewPager mViewPager;
    private Controller ctl;
    private String url = "http://donnees.roulez-eco.fr/opendata/jour";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEST
        
      //We call the controller
        ctl = new Controller(this.getApplicationContext());
        ctl.DownloadData(this.url);
        try {
            ctl.UnzipFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ctl.deleteZipFile();
        try {
            ctl.parseXmlFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //FIN TEST
        
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF8800"));     
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
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
        DatabaseHelper helper = OpenHelperManager.getHelper(this.getApplicationContext(), DatabaseHelper.class);
       ParserImpl p = ctl.getParser();
        for(fr.istic.project.gasLocation.services.Station s : p.getPvd()){
        	Station stat = new Station((double)s.getLatitude(), (double)s.getLongitude(), s.getZipcode(), s.getAdress(), s.getCity(), "", "", "", "");
        	helper.addStation(stat);
        	Iterator iter1 = s.getPrices().entrySet().iterator();
        	while (iter1.hasNext()) {
        		Map.Entry ent = (Map.Entry) iter1.next();
        		//La clé de la HashMap
        		String clé = (String) ent.getKey();
        		//La Valeur de la HashMap
        		Float valeur = (Float) ent.getValue();
        		Gas g = new Gas(stat, clé, "", 10, 't', "");
        		helper.addGas(g);
        	}
        }
       /* Station s = new Station(50, 51, 49490, "lamiro", "rennes", "debut", "fin", "", "");
        helper.addStation(s);
        helper.addGas(new Gas(s, "coucou", "madate", 12, 'o', "marupture"));
        System.out.println(helper.getGas().toString());
        System.out.println("dd");*/
     // get our dao
       // RuntimeExceptionDao<Gas, Integer> gasDao = helper.getGasDao();
        // query for all of the data objects in the database
        //Gas gas = new Gas(5, 4, "sp95", new java.sql.Date(5447), (double)123, 'e', null);
       // gasDao.createIfNotExists(gasDao.createIfNotExists(gas));
        
        // calling the station dao here
        currentStations = helper.getAllStationFromPostalCodeWithGases("35000");
        
//        // BEGIN mock
//        currentStations = new ArrayList<Station>();
//        // add stations
//        Station station = new Station();
//        station.setLatitude(48.113737);
//        station.setLongitude(-1.639225);
//        station.setAddress("Total YEAH");
//        
//        currentStations.add(station);
//        // END mock
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
