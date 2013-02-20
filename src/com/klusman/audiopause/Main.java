package com.klusman.audiopause;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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



public class Main extends ListActivity {
	//private static final String SD_PATH = new String("/sdcard/");
	private List<String> songs = new ArrayList<String>();
	private MediaPlayer mp = new MediaPlayer();
	List<File> files = new ArrayList<File>();

    File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_MUSIC);  //works
    
    //File sd = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), myfolder);
	String pathString = path.toString();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.i("blah", pathString);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void updatePlayList(){
    	File[] music = new File(pathString).listFiles();
    	Collection<? extends File> mp3s = findAllMp3s(music);
    	//int d = mp3s.size();
    	Log.i("count",  String.valueOf( mp3s.size()) );
    	//long size = music.length();
    	Log.i("update Music", "function hit");
    	if(music != null){
    		String s = String.valueOf( music[0].listFiles( new Mp3Filter() ).length);
    		Log.i("update Music", s);
    		/*
    		if(music.listFiles( new Mp3Filter() ).length >0 ){
    			Log.i("update Music", "if - hit");
    			for (File file : music.listFiles(new Mp3Filter())){
    				Log.i("update Music", "for - hit");
    				songs.add(file.getName());
    			}
    			ArrayAdapter<String> songList = new ArrayAdapter<String>(this, R.layout.activity_main, songs);
    			setListAdapter(songList);
    		
    			int aSize = songs.size();
    			Log.i("Array Size", String.valueOf(aSize));
    		}
    		*/
    		
    		
  //////  WORKING  HERE  ////////////		
   /* 		Collection files = FileUtils.listFiles(
    				dir,
    				new RegexFileFilter("^(.*?)"),
    				DirectoryFileFilter.DIRECTORY);*/
    		
//////  WORKING  HERE  ////////////	
    	}
    }
    
    
    
    private Collection <? extends File> findAllMp3s(File[] music) {

    	Log.i("MUSIC LENGTH", String.valueOf(music.length));
    	
    	for(int i = 0; i <= music.length; i++){
    		File thing = music[i];
    		
    		if(thing.isDirectory())
    		{
    			File[] foo = thing.listFiles();
    			int dirL = foo.length;
    			Log.i("DIRECTORY LENGTH", String.valueOf(dirL));
    			digIntoFile(foo);
    			
    			
    		}else{
    			addFiletoList(thing);
    		}
    		

			
		}
		return files;
	}

    
    private void addFiletoList(File file){
    	files.add(file);
    	Log.i("ADD FILE", file.toString());
    }
    
    private void digIntoFile(File[] file){
    	Log.i("SUBFILE LENGTH", String.valueOf(file.length));
    	for(int i = 0; i <= file.length; i++){
    		File subThing = file[i];
    		
    		if(subThing.isDirectory()){
    			File[] moreFiles = subThing.listFiles();
    			digIntoFile(moreFiles);
    			Log.i("SUBFILE", "Subthing was a Directory Again!");
    		}else{
    			addFiletoList(subThing);
    		}
    		
    	}
    }
    
//    private Collection <? extends File> subFile(File[] folder){
//    	//List<File> files = new ArrayList<File>();
//    	Log.i("SUBFILE LENGTH", String.valueOf(folder.length));
//    	for(int i = 0; i <= folder.length; i++){
//    		File subThing = folder[i];
//    		if(subThing.isDirectory()){
//    			subFile(subThing);
//    		}else{
//    			addFiletoList(subThing);
//    		}
//    		
//    	}
//    	
//    	
//    	return files;
//    	
//    }
    
    
//	public String pullmusicPath(){
//    	return Environment.getExternalStorageDirectory().toString() + "/Music";
//    	
//    }
    
}

class Mp3Filter implements FilenameFilter{
	public boolean accept(File dir, String name){
		return (name.endsWith(".mp3"));
	}
}