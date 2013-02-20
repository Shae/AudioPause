package com.klusman.audiopause;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



public class Main extends ListActivity {
	
	private List<String> songs = new ArrayList<String>();
	private List<File> song2 = new ArrayList<File>();
	private MediaPlayer mp = new MediaPlayer();
	private String musicArray[];
	Object mySongs[];
	//List<File> files = new ArrayList<File>();

    File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);  //works
	//String pathString = path.toString();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        updatePlayList();
        
        Button BUTTON = (Button)findViewById(R.id.stopBtn);
        BUTTON.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mp.stop();
				
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
   private void updatePlayList(){
//    	//File[] music = new File(pathString).listFiles();
//    	Collection<? extends File> mp3s = findAllMp3s(music);
	Collection<File> files = FileUtils.listFiles(path,TrueFileFilter.INSTANCE,TrueFileFilter.INSTANCE);
    	if(files != null){

    		mySongs = files.toArray();
    		
    		Iterator<File> itr = files.iterator(); 
    		while(itr.hasNext()) {

    			String name = itr.next().getName();
    			songs.add(name);
    		    Log.i("SONG", name);
    		    

    		} 
    		

   			ArrayAdapter<String> songList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songs);
   			setListAdapter(songList);


    		
    		Log.i("# of Songs", String.valueOf(files.size()));

    	}else{
    		Log.i("Songs", "No Songs Found");
    	}
   	
   }
    
    
}
