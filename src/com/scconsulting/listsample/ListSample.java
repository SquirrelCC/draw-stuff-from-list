package com.scconsulting.listsample;

import java.util.Arrays;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class ListSample extends Activity {
	
	private ListView listView;
	private List<String> list;
	private List<String> messageArray;
	private ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_sample);
		
		list = Arrays.asList(getResources().getStringArray(R.array.list_array));
		messageArray = Arrays.asList(getResources().getStringArray(R.array.how_to_draw_array));
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_sample, menu);
		return true;
	}
	
	@Override
	public void onResume(){
    	super.onResume();
    	 listView = (ListView)findViewById(R.id.listView1);
         listView.setAdapter(arrayAdapter);
         listView.setOnItemClickListener(new OnItemClickListener() {
         	@Override
         	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
         		
         		//Toast.makeText(ListSample.this, "You clicked " + position, Toast.LENGTH_LONG).show();
         		Toast.makeText(ListSample.this, messageArray.get(position),
         				Toast.LENGTH_LONG).show();
         		
         		Intent intent = new Intent(ListSample.this, DrawingActivity.class);
         		intent.putExtra("shape", position);
   	       		startActivityForResult(intent, 0);

         	}
         });
 	}
	
	public void onClickExit(View view) {
		Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
	}

}
