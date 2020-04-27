package com.example.myretrofit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import org.w3c.dom.Text;

import java.util.List;
//dilakukan extends dari class Abstract Item untuk pemodelan data dan view holder
public class Post extends AbstractItem<Post, Post.ViewHolder> {
    //trdpt 5 atribut yg telah dibuat di database
    private String id_siswa;
    private String nama;
    private String alamat;
    private String jenis_kelamin;
    private String no_telp;

    //membuat konstruktor berfungsi sbg penambahn data yakni menambahkan data yang didapat dari rest server ke recyclerview
    public Post(String id_siswa, String nama, String alamat, String jenis_kelamin, String no_telp){
        this.id_siswa = id_siswa;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
        this.no_telp = no_telp;
    }
    //membuat get dr data id,nama,alamat,jk,notelp
    public String getId_siswa() {
        return id_siswa;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getNo_telp() {
        return no_telp;
    }

    //lalu trdpt 3 method yg merupakan method implementasi dari class Abstract Item,
    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.rv_siswa;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_siswa;
    }

    public class ViewHolder extends FastAdapter.ViewHolder<Post> {
        TextView TextId, TextNama, TextAlamat, TextJK, TextNo;

        public ViewHolder(View itemView) {
            super(itemView);
            //kemudian di bawah ini merupakan proses binding atribut
            TextId = itemView.findViewById(R.id.txt_id);
            TextNama = itemView.findViewById(R.id.txt_nama);
            TextAlamat = itemView.findViewById(R.id.txt_alamat);
            TextJK = itemView.findViewById(R.id.txt_jk);
            TextNo = itemView.findViewById(R.id.txt_no_telp);
        }

        //selanjutnya dibawah ini fungsinya untuk melakukan bind model ke layout
        @Override
        public void bindView(Post item, List<Object> payloads) {
            TextId.setText(item.id_siswa);
            TextNama.setText(item.nama);
            TextAlamat.setText(item.alamat);
            TextJK.setText(item.jenis_kelamin);
            TextNo.setText(item.no_telp);
        }

        @Override
        public void unbindView(Post item) {
            TextId.setText(null);
            TextNama.setText(null);
            TextAlamat.setText(null);
            TextJK.setText(null);
            TextNo.setText(null);
        }
    }
}
