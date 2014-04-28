package com.me.chessview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		menu();
	}

	private void menu(){

		ImageButton NewGame = (ImageButton) findViewById(R.id.menuNewGame);
		ImageButton Settings = (ImageButton) findViewById(R.id.menuSettings);
		ImageButton Leaderboard = (ImageButton) findViewById(R.id.menuLeaderboard);

		NewGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Game.class);
				startActivity(i);

			}
		});

		Settings.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Settings.class);
				startActivity(i);
				
			}
		});

		Leaderboard.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Leaderboard.class);
				startActivity(i);
				

			}
		});

	}

}
