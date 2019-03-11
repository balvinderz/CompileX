package tired.coder.compilex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    int mSelectedItem;
    BottomNavigationView bottomNavigationView;
    private static final String SELECTED_ITEM = "arg_selected_item";
    MenuItem selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                selectFragment(menuItem);
            }
        });
        if(savedInstanceState!=null)
        {
            mSelectedItem=savedInstanceState.getInt(SELECTED_ITEM,0);
            selectedItem=bottomNavigationView.getMenu().findItem(mSelectedItem);
        }
        else
            selectedItem=bottomNavigationView.getMenu().getItem(0);
        selectFragment(selectedItem);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }

}
