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
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId())
                {
                    case R.id.textedit :
                        fragment=new TextEditor();
                        loadFragment(fragment);
                        break;
                    case R.id.programs:
                        fragment=new TextEditor();
                        loadFragment(fragment);
                        break;
                    case R.id.settings:
                        fragment=new TextEditor();
                        loadFragment(fragment);
                        break;
                }
            }
        });

    }
    private  void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentcontainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

}