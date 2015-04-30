package com.example.emptyandroid;

import java.util.ArrayList;
import java.util.List;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import android.support.v4.app.FragmentManager;  
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;  
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;  

public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener,OnPageChangeListener{

	
	 /** 
     * 顶部Tab的title 
     */  
    private String [] mTabTitles;  
      
    /** 
     * ViewPager对象的引用 
     */  
    private ViewPager mViewPager;  
      
    /** 
     * 装载Fragment的容器，我们的每一个界面都是一个Fragment 
     */  
    private List<Fragment> mFragmentList;  
      
    /** 
     * ActionBar对象的引用 
     */  
    private ActionBar mActionBar;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 //从资源文件在获取Tab的title  
        mTabTitles = getResources().getStringArray(R.array.tab_title);  
        mFragmentList =  new ArrayList<Fragment>();  
          
        mViewPager = (ViewPager) findViewById(R.id.viewPager);  
        //设置Adapter  
        mViewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager(),mFragmentList));  
        //设置监听  
        //mViewPager.setOnPageChangeListener(this);  
          
          
        //获取Action实例我们使用getSupportActionBar()方法  
        mActionBar = getSupportActionBar();  
          
        //隐藏Title  
        mActionBar.setDisplayShowTitleEnabled(false);  
        //隐藏Home logo  
        mActionBar.setDisplayShowHomeEnabled(false);  
        //设置ActionBar的导航模式为Tab  
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
          
          
        //为ActionBar添加Tab并设置TabListener  
        for(int i=0; i<mTabTitles.length; i++){  
             ActionBar.Tab tab = mActionBar.newTab();  
             tab.setText(mTabTitles[i]);  
             tab.setTabListener(this);  
             mActionBar.addTab(tab, i);  
        }  
          
          
        //将Fragment加入到List中，并将Tab的title传递给Fragment  
        for(int i=0; i<mTabTitles.length; i++){  
            Fragment fragment = new ItemFragment();  
            Bundle args = new Bundle();  
            args.putString("arg", mTabTitles[i]);  
            fragment.setArguments(args);  
            mFragmentList.add(fragment);  
        }  
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
		
	}

	@Override
	public void onPageSelected(int arg0) {
        mActionBar.setSelectedNavigationItem(arg0);  
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());  
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
		
	}

}
