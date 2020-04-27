package com.example.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormActivity extends AppCompatActivity {
    //terdapat pendeklarasian id,nama,alamat, no telp
    private EditText inputid_siswa, inputNama, inputAlamat, inputTelp;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btn = findViewById(R.id.btn_tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kita arahkan sesuai dengan id, nama, alamat, dan no telpon
                inputid_siswa = findViewById(R.id.edt_nim);
                inputNama = findViewById(R.id.edt_nama);
                inputAlamat = findViewById(R.id.edt_alamat);
                inputTelp = findViewById(R.id.edt_telp);

                //ini variabel untuk menyimpan data yang didapat dari form-nya
                String nim = inputid_siswa.getText().toString();
                String nama = inputNama.getText().toString();
                String alamat = inputAlamat.getText().toString();
                radioGroup = findViewById(R.id.group_jk);
                RadioButton selected = findViewById(radioGroup.getCheckedRadioButtonId());
                String jenis_kelamin = "";
                if (selected.getText().toString().equals("Perempuan")) {
                    jenis_kelamin = "P";
                } else if ((selected.getText().toString().equals("Laki-laki"))) {
                    jenis_kelamin = "L";
                }
                String no_telp = inputTelp.getText().toString();

//                TextView txt = findViewById(R.id.text_title);
//                txt.setText(nim+nama+alamat+jenis_kelamin+no_telp);

                //ini kode jika data yang diisi kosong
                if (TextUtils.isEmpty(nim) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(jenis_kelamin) || TextUtils.isEmpty(no_telp)) {
                    Toast.makeText(getApplicationContext(), "Fill the field", Toast.LENGTH_SHORT).show();
                } else {
                    Post post = new Post(nim, nama, alamat, jenis_kelamin, no_telp);

                    //jika tidak kosong, maka retrofit akan diinstansiasi ke base url
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://rizkyauliaf.000webhostapp.com/restserver/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

                    //lalu kita panggil method setpost
                    //kemudian mengisi parameternya ke body sesuai data yang dibutuhkan yakni
                    Call<ResponseBody> call = jsonPlaceHolderApi.setPost(
                            nim,
                            nama,
                            alamat,
                            jenis_kelamin,
                            no_telp
                    );

                    call.enqueue(new Callback<ResponseBody>() {
                        //jika berhasil maka akan kembali ke halaman mainActivity
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }
                        //jika gagal maka akan menampilkan text failed
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

}

