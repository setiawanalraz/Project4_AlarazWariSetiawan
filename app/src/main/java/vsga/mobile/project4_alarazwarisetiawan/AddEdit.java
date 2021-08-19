package vsga.mobile.project4_alarazwarisetiawan;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vsga.mobile.project4_alarazwarisetiawan.helper.DbHelper;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address, txt_contact;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address, contact;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_address = (EditText) findViewById(R.id.txt_address);
        txt_contact = (EditText) findViewById(R.id.txt_contact);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);
        contact = getIntent().getStringExtra(MainActivity.TAG_CONTACT);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(address);
            txt_contact.setText(contact);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Fungsi kosongkan semua edit text
    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_name.setText(null);
        txt_address.setText(null);
        txt_contact.setText(null);
    }

    // Menyimpan data ke database SQLite
    private void save() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("") ||
                String.valueOf(txt_contact.getText()).equals(null) || String.valueOf(txt_contact.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address or contact...", Toast.LENGTH_SHORT).show();
        }
        else {
            SQLite.insert(txt_name.getText().toString().trim(), txt_address.getText().toString().trim(),
                    Integer.parseInt(txt_contact.getText().toString().trim()));
            blank();
            finish();
        }
    }

    // Update data kedalam Database SQLite
    private void edit() {
        if (String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("") ||
                String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("") ||
                String.valueOf(txt_contact.getText()).equals(null) || String.valueOf(txt_contact.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address or contact...", Toast.LENGTH_SHORT).show();
        }
        else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_name.getText().toString().trim(),
                    txt_address.getText().toString().trim(), Integer.parseInt(txt_contact.getText().toString().trim()));
            blank();
            finish();
        }
    }
}
