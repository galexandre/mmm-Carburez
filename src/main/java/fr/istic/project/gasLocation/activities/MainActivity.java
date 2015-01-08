package fr.istic.project.gasLocation.activities;

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

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

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
        
        /*GasStationDatabase g;
		try {
			//g = new GasStationDatabase(this.getApplicationContext());
			//g.createDataBase();
			DataManager data = new DataManager(this.getApplicationContext());
			Long l = (long) 1;
			data.saveStation();
			List<Gas> ggg = data.getGasList();// GasLinkedToStation(l);
			System.out.println("hello");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        
    }
    
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }
    
    
	public void openFilter(View v){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
	     
		 alertDialogBuilder.setTitle(this.getTitle()+ " decision");
		 alertDialogBuilder.setMessage("Are you sure?");
		 // set positive button: Yes message
		 alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// go to a new activity of the app
					Intent positveActivity = new Intent(getApplicationContext(),
                           MainActivity.class);
		            startActivity(positveActivity);	
				}
			  });
		 // set negative button: No message
		 alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// cancel the alert box and put a Toast to the user
					dialog.cancel();
					Toast.makeText(getApplicationContext(), "You chose a negative answer", 
							Toast.LENGTH_LONG).show();
				}
			});
		 // set neutral button: Exit the app message
		 alertDialogBuilder.setNeutralButton("Exit the app",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// exit the app and go to the HOME
					MainActivity.this.finish();
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

//        @Override
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
