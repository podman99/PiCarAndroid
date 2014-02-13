package uk.co.keithjasper.picarandroid;

import java.util.Locale;

import org.codeandmagic.android.gauge.GaugeView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DashboardActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		GaugeView gauge = new GaugeView(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			if (position == 1) {
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			} else if (position == 2) {
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			} else if (position == 3) {
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			} else {
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
						position + 1);
				fragment.setArguments(args);
				return fragment;
			}

		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_dashboard_dummy,
					container, false);
			Log.d("PAGE NUMBER", DummySectionFragment.ARG_SECTION_NUMBER);
			if (getArguments().getInt("section_number", 0) == 1) {
				rootView = inflater.inflate(R.layout.fragment_dashboard,
						container, false);
				final TextView rpmValue = (TextView) rootView
						.findViewById(R.id.rpmValue);
				
				rpmValue.setWidth(200);
				final Handler handler = new Handler();
			    Runnable rpmRunable = new Runnable() {
			        public void run() {
			            while (true) {  
			                try {
			                    Thread.sleep(500);
			                }    
			                catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			                handler.post(new Runnable(){
			                    @SuppressLint("UseValueOf")
								public void run() {
			                    	RandomRevs rpm = new RandomRevs();
			            			double rpmvalue = rpm.getRevs();
			            			String rpmv = new Double(rpmvalue).toString();
			            			Log.v("RPM", rpmv);
			                    	rpmValue.setText(rpmv);
			                }
			            });
			            }
			        }
			    };
			    new Thread(rpmRunable).start();
				


				final TextView mphValue = (TextView) rootView
						.findViewById(R.id.mphValue);
				
				mphValue.setWidth(200);
			    Runnable mphRunnable = new Runnable() {
			        public void run() {
			            while (true) {  
			                try {
			                    Thread.sleep(500);
			                }    
			                catch (InterruptedException e) {
			                    e.printStackTrace();
			                }
			                handler.post(new Runnable(){
			                    @SuppressLint("UseValueOf")
								public void run() {
			                    	RandomMph mph = new RandomMph();
			            			double mphvalue = mph.getMph();
			            			String mphv = new Double(mphvalue).toString();
			            			Log.v("MPH", mphv);
			            			mphValue.setText(mphv);
			                }
			            });
			            }
			        }
			    };
			    new Thread(mphRunnable).start();
				
				
			} else if (getArguments().getInt("section_number", 0) == 2) {
				TextView dummyTextView = (TextView) rootView
						.findViewById(R.id.section_label);
				dummyTextView.setText("I am the code which generates page 2");
			} else if (getArguments().getInt("section_number", 0) == 3) {
				rootView = inflater.inflate(R.layout.fragment_reverse_camera,
						container, false);
			} else {

				TextView dummyTextView = (TextView) rootView
						.findViewById(R.id.section_label);
				dummyTextView
						.setText("I am the default page which should never be seen :)");
			}
			return rootView;
		}
		
		public void closeApp() {
		
			System.exit(0);
			super.onDestroy();
		}
	}


	
	public static class RandomRevs {
		public RandomRevs() {
		}

		public double getRevs() {
			return Math.floor(Math.random() * (5000 - 0 + 1) + 0);
		}
	}
	
	public static class RandomMph {
		public RandomMph() {
		}

		public double getMph() {
			return Math.floor(Math.random() * (120 - 0 + 1) + 0);
		}
	}
}
