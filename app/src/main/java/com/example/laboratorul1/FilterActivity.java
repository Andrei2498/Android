package com.example.laboratorul1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FilterActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_filter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.meniu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.Home:
        openHomeMenu();
        return true;
      case R.id.Cart:
        openCartMenu();
        return true;
      case R.id.settings:
        openSettingsMenu();
        return true;
      case R.id.Login:
        openLoginMenu();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  public void openCartMenu() {
    Intent intent = new Intent(this, ShoppingCartActivity.class);
    startActivity(intent);
  }

  public void openHomeMenu() {
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

  public void openLoginMenu() {
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }

  public void openSettingsMenu(){
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);
  }
}