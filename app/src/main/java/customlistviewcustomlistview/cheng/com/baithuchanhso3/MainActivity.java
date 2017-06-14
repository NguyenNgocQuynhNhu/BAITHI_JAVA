package customlistviewcustomlistview.cheng.com.baithuchanhso3;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> arrayContact;
    private ContactAdapter adapter;
    private EditText edtName;
    private EditText edtNumber;
    private RadioButton rbtnMale;
    private RadioButton rbtnFemale;
    private Button btnAddContact;
    private ListView lvContact;
    private EditText Eddelte;
    private EditText emaill;
    private EditText datee;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setWidget();
        arrayContact = new ArrayList<>();
        adapter = new ContactAdapter(this, R.layout.item_contact_listview, arrayContact);
        lvContact.setAdapter(adapter);
        checkAndRequestPermissions();
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogConfirm(position);

            }
        });
    }


    private void checkAndRequestPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS
        };
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
        }
    }

    public void setWidget() {
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_number);
        Eddelte = (EditText) findViewById(R.id.date);
        rbtnMale = (RadioButton) findViewById(R.id.rbtn_male);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtn_female);
        btnAddContact = (Button) findViewById(R.id.btn_add_contact);
        lvContact = (ListView) findViewById(R.id.lv_contact);
        emaill= (EditText)findViewById(R.id.email);
    }

    public void addContact(View view) {
        if (view.getId() == R.id.btn_add_contact) {
            String name = edtName.getText().toString().trim();
            String number = edtNumber.getText().toString().trim();
            String date = Eddelte.getText().toString().trim();
            String email = emaill.getText().toString().trim();
            boolean isMale = true;
            if (rbtnMale.isChecked()) {
                isMale = true;
            } else {
                isMale = false;
            }
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(number)) {
                Toast.makeText(this, "Please Input Number or Name", Toast.LENGTH_SHORT).show();
            } else {
                Contact contact = new Contact(isMale, name, number, date,email);
                arrayContact.add(contact);

            }
            adapter.notifyDataSetChanged();

        }
    }

    public void showDetail(int pos) {
        Intent itent = new Intent(MainActivity.this, Detail.class);
        Bundle bundle = new Bundle();
        bundle.putString("name",arrayContact.get(pos).getmName());
        bundle.putString("phone",arrayContact.get(pos).getmNumber());
        bundle.putBoolean("gerden",arrayContact.get(pos).isMale());
        bundle.putString("email",arrayContact.get(pos).getEmail());
        bundle.putString("date",arrayContact.get(pos).getMdate());
        itent.putExtra("data",bundle);
        startActivity(itent);
    }

    public void showDialogConfirm(final int position) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        Button btnCall = (Button) dialog.findViewById(R.id.btn_call);
        Button btnSendMessage = (Button) dialog.findViewById(R.id.btn_send_message);
        Button buttondelete = (Button) dialog.findViewById(R.id.button);
        Button button1 = (Button) dialog.findViewById(R.id.detail);
        TextView t1, t2, t3, t4;
        t1 = (TextView) dialog.findViewById(R.id.calll);
        t2 = (TextView) dialog.findViewById(R.id.messagee);
        t3 = (TextView) dialog.findViewById(R.id.deletee);
        t4 = (TextView) dialog.findViewById(R.id.detailll);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail(position);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail(position);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCall(position);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentSendMesseage(position);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayContact.remove(position);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayContact.remove(position);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentCall(position);
                dialog.dismiss();
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSendMesseage(position);
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void intentSendMesseage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }

    private void intentCall(int position) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + arrayContact.get(position).getmNumber()));
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && action != null && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }
}


