package com.nineinfosys.android.estatecalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    ImageView profilePictureView;
    EditText amount,rate;
    public Toolbar toolbar;
EditText residence,stock,savings,vehicles,retiremnt,life,other,debts,funeral,charitable,state;
    TextView total,federal;
    Button cal,calclear;
    EstateCalculator estateCalculator;
    DecimalFormat f = new DecimalFormat("##.00");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        residence=(EditText)findViewById(R.id.residenceid);
        residence.setText("0");
        stock=(EditText)findViewById(R.id.stockid);
        stock.setText("0");
        savings=(EditText)findViewById(R.id.savingsid);
        savings.setText("0");
        vehicles=(EditText)findViewById(R.id.vehiclesid);
        vehicles.setText("0");
        retiremnt=(EditText)findViewById(R.id.retirementid);
        retiremnt.setText("0");
        life=(EditText)findViewById(R.id.lifeid);
        life.setText("0");
        other=(EditText)findViewById(R.id.othersid);
        other.setText("0");
        debts=(EditText)findViewById(R.id.debtsid);
        debts.setText("0");
        funeral=(EditText)findViewById(R.id.funeralid);
        funeral.setText("0");
        charitable=(EditText)findViewById(R.id.charitableid);
        charitable.setText("0");
        state=(EditText)findViewById(R.id.stateid);
        state.setText("0");
        cal=(Button)findViewById(R.id.buttoncalculate);
        calclear=(Button)findViewById(R.id.buttonclear);
        total=(TextView)findViewById(R.id.totalid);
        federal=(TextView)findViewById(R.id.federaltvid);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff);


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mNavigationView.setItemIconTintList(null);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();


                //communicate
                if (menuItem.getItemId() == R.id.Share) {
                    final String appPackageName = getPackageName();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    String shareBodyText = "https://play.google.com/store/apps/details?id=" + appPackageName;
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject/Title");
                    intent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                    startActivity(Intent.createChooser(intent, "Choose sharing method"));

                }

                if (menuItem.getItemId() == R.id.AppStore) {
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://developer?id=GeniusNine+Info+Systems+LLP")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=GeniusNine+Info+Systems+LLP")));
                    }
                }

                if (menuItem.getItemId() == R.id.GetApps) {

                    /*Intent intent=new Intent(MainActivityDrawer.this,RequestApp.class);
                    startActivity(intent);
*/

                }


                if (menuItem.getItemId() == R.id.RateUs) {
                    final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }


                }


                return false;
            }


        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        cal.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        double residenceValue = Double.parseDouble(residence.getText().toString().trim());
        double stockValue = Double.parseDouble(stock.getText().toString().trim());
        double savingsValue = Double.parseDouble(savings.getText().toString().trim());
        double vehiclesValue = Double.parseDouble(vehicles.getText().toString().trim());
        double retiremntValue = Double.parseDouble(retiremnt.getText().toString().trim());
        double lifeValue = Double.parseDouble(life.getText().toString().trim());
        double otherValue = Double.parseDouble(other.getText().toString().trim());
        double debtsValue = Double.parseDouble(debts.getText().toString().trim());
        double funeralValue = Double.parseDouble(funeral.getText().toString().trim());
        double charitableValue = Double.parseDouble(charitable.getText().toString().trim());
        double stateValue = Double.parseDouble(state.getText().toString().trim());
        estateCalculator=new EstateCalculator(residenceValue,stockValue,savingsValue,vehiclesValue,retiremntValue,lifeValue,otherValue,debtsValue,funeralValue,charitableValue,stateValue);
        total.setText(String.valueOf(f.format(estateCalculator.totaltax())));
        federal.setText(String.valueOf(f.format(estateCalculator.federabletax())));

    }
});
        calclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                residence.setText("0");
                 stock.setText("0");
                savings.setText("0");
                   vehicles.setText("0");
                retiremnt.setText("0");
                 life.setText("0");
                other.setText("0");
               debts.setText("0");
                funeral.setText("0");
                charitable.setText("0");
                state.setText("0");
                 total.setText("0");
                federal.setText("0");
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure you want to close App?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                finish();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }


    }
}
