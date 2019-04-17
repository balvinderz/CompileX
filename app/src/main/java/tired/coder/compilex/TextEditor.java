package tired.coder.compilex;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class TextEditor  extends Fragment {
    TextInputEditText editText;
    Toolbar toolbar;
    int flag=1;
    int indexofpreviousspace=0;
    int i=1;
    public static String[] keywords={"from","is","def","and","False","True","for","return","None","continue"};

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {


        }

        @Override
        public void afterTextChanged(Editable s) {
            editText.removeTextChangedListener(this);
            String text=s.toString();
            Log.i("loggings",text);

             /* if(text.equals("from") ) {
                Log.i("callingnumberoftimes",String.valueOf(i));
                i++;
                String html="<font color='#fca90f'>"+text+"</font>";
                editText.setText(Html.fromHtml(text.replaceAll("from","<font color='#fca90f'>from</font>")));

            } */
             int spaceindex;
             Log.i("logginstring",editText.getText().toString());
             if(s.length()!=0)
             if(text.charAt(s.length()-1)==' ') {
                 spaceindex = getspaceindex(s.toString(), s.length() - 1);
                 SpannableString spannableString = new SpannableString(s.toString());
                 ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.keywordcolor));

                 String word=s.toString().substring(spaceindex+1,s.length()-1);
                 Log.i("wordsis",word);

                 if(isKeyword(word)) {
                     s.setSpan(foregroundColorSpan, spaceindex+1,s.length()-1, Spanned.SPAN_INCLUSIVE_INCLUSIVE );

                     editText.setSelection(s.length());
                 }
             }
            editText.addTextChangedListener(this);

        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.texteditorlayout,container,false);
        LinearLayout linearLayout=view.findViewById(R.id.lin);
          editText=view.findViewById(R.id.code);
          editText.addTextChangedListener(textWatcher);
          setHasOptionsMenu(true);
          Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
             inflater.inflate(R.menu.toolbarintexteditor,menu);
        MenuItem menuItem=menu.findItem(R.id.run);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.i("code",editText.getText().toString());
                sendRequest(editText.getText().toString(),getActivity().getApplicationContext());
                return false;
            }
        });
    }
    public static int getspaceindex(String string,int lastspaceindex)
    {
        int i=lastspaceindex-1;

        while(string.charAt(i)!=' '&&string.charAt(i)!='\n'&& i!=0)
            i--;
        if(i==0)
            return -1;
        return i;
    }
    public static Boolean isKeyword(String word)

    {
        int i;
        for(i=0;i<keywords.length;i++)
            if(word.equals(keywords[i]))
                return true;
        return  false;
    }
    public static void sendRequest(final String code, Context context)
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url="http://192.168.1.107:3000/";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.i("responseis",response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("code", code); //Add the data you'd like to send to the server.
                return MyData;
            }
        };
        queue.add(MyStringRequest);

    }


}

