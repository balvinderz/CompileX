package tired.coder.compilex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new TextEditor());
        bottomNavigationView = findViewById(R.id.bottom_navigation);
      bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              Fragment fragment=null;
              switch (menuItem.getItemId())
              {
                  case R.id.textedit :
                      fragment=new TextEditor();
                      break;
                  case R.id.programs:
                      fragment=new TextEditor();
                      break;
                  case R.id.settings:
                      fragment=new Settings();
                      break;
              }
              return loadFragment(fragment);


          }
      });

    }
    private  boolean loadFragment(Fragment fragment)
    {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentcontainer, fragment)
                    .commit();
            return true;
        }
    return  false;

    }

}