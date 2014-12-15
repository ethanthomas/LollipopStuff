package com.developers.obsidian.lollipopstuff.core;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.developers.obsidian.lollipopstuff.R;
import com.developers.obsidian.lollipopstuff.circularreveal.CircularRevealFragment;
import com.developers.obsidian.lollipopstuff.pagerslidingtabstrip.PSTSNesterFragment;


public class Home extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mLeftDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    LinearLayout drawer;
    TextView title;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mFragmentTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mTitle = mDrawerTitle = getTitle();
        mFragmentTitles = getResources().getStringArray(R.array.fragments);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftDrawer = (ListView) findViewById(R.id.left_drawer);
        drawer = (LinearLayout) findViewById(R.id.drawer);

        title = (TextView) findViewById(R.id.title);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mLeftDrawer.setAdapter(new CustomAdapter(this, mFragmentTitles));

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.invisible_0);

        mLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                ((CustomAdapter) parent.getAdapter()).selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null) {
            selectItem(0);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(drawer);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//       MenuInflater inflater = getMenuInflater();
//       inflater.inflate(R.menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(drawer)) {
                    mDrawerLayout.closeDrawer(drawer);
                } else {
                    mDrawerLayout.openDrawer(drawer);
                }
                return true;
        }
        return true;
    }

    private void selectItem(int position) {
        Fragment newFragment = new PSTSNesterFragment();
        FragmentManager fm = getSupportFragmentManager();
        switch (position) {
            case 0:
                newFragment = new PSTSNesterFragment();
                break;

            case 1:
                newFragment = new CircularRevealFragment();
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:


            case 5:


            case 6:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:obsidiandevelopers@gmail.com" + "?subject=HF Navigator - Feedback" + "&body=");
                intent.setData(data);
                startActivity(intent);
                break;

            case 7:
                Intent intent1 = new Intent(getApplicationContext(), AboutDeveloper.class);
                startActivity(intent1);
                break;

        }

        if (position == 6 || position == 7) {


        } else {

            fm.beginTransaction().replace(R.id.content_frame, newFragment).commit();
            mLeftDrawer.setItemChecked(position, true);
            setTitle(mFragmentTitles[position]);
            mDrawerLayout.closeDrawer(drawer);

        }


    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    public class CustomAdapter extends BaseAdapter {

        Context context;
        String[] mTitle;
        int[] mIcon;
        LayoutInflater inflater;
        private int selectedItem;

        public CustomAdapter(Context context, String[] title) {
            this.context = context;
            this.mTitle = title;
        }

        public void selectItem(int selectedItem) {
            this.selectedItem = selectedItem;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public Object getItem(int position) {
            return mTitle[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            TextView txtTitle;
//            ImageView imgIcon;
            View v;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = inflater.inflate(R.layout.new_drawer_list_item, parent, false);

            txtTitle = (TextView) itemView.findViewById(R.id.drawerItemText);

            v = itemView.findViewById(R.id.view);

            txtTitle.setText(mTitle[position]);

            if (position == 5) {
                v.setVisibility(View.VISIBLE);
            }

            if (position != 6 && position != 7) {
                txtTitle.setTypeface(null, position == selectedItem ? Typeface.BOLD : Typeface.NORMAL);
                itemView.setBackgroundColor(position == selectedItem ? Color.parseColor("#dfdfdf") : Color.TRANSPARENT);
            }

            return itemView;
        }
    }
}