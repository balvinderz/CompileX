package tired.coder.compilex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TextEditor  extends Fragment {
    TextInputEditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.texteditorlayout,container,false);
        ScrollView scrollView=view.findViewById(R.id.scrollviewtext);
          editText=view.findViewById(R.id.code);
        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
            }
        });
        return view;
    }
}
