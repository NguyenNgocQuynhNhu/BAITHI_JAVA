package customlistviewcustomlistview.cheng.com.baithuchanhso3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    TextView t1, t2, t3,t4;
    ImageView imageView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Detail");
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        imageView = (ImageView) findViewById(R.id.imagee);
        if (bundle.getBoolean("gerden"))
            imageView.setImageResource(R.drawable.icmale);
        else imageView.setImageResource(R.drawable.icfemale);
        t1=(TextView)findViewById(R.id.emaillll) ;
        t2 = (TextView)findViewById(R.id.textnamee);
        t3 = (TextView)findViewById(R.id.textphonee);
        t4 = (TextView)findViewById(R.id.datee);
        t2.setText(bundle.getString("name"));
        t3.setText(bundle.getString("phone"));
        t1.setText(bundle.getString("email"));
        t4.setText(bundle.getString("date"));
    }
}
