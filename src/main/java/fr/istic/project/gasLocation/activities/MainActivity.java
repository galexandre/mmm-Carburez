package fr.istic.project.gasLocation.activities;

import java.util.Date;
import java.util.List;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;
import fr.istic.project.gasLocation.R;
import fr.istic.project.gasLocation.adapter.ListSectionFragment;
import fr.istic.project.gasLocation.models.DatabaseHelper;
import fr.istic.project.gasLocation.models.Gas;
import fr.istic.project.gasLocation.models.Station;

public class MainActivity extends FragmentActivity  implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        DatabaseHelper helper = new DatabaseHelper(this.getApplicationContext());
        helper.setDatabase(this.openOrCreateDatabase("" + helper.DATABASE_NAME, MODE_PRIVATE, null));
        
     // get our dao
       // RuntimeExceptionDao<Gas, Integer> gasDao = helper.getGasDao();
        // query for all of the data objects in the database
       /* Gas gas = new Gas(5, 4, "sp95", new java.sql.Date(5447), (double)123, 'e', null);
        gasDao.createIfNotExists(gasDao.createIfNotExists(gas));*/
        
        
        
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
                    return new ListSectionFragment();

                default:
                    Fragment fragment = new MapSectionFragment();
                    Bundle args = new Bundle();
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
