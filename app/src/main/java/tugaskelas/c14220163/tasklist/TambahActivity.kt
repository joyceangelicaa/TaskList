package tugaskelas.c14220163.tasklist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder

class TambahActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_tambah)

        val inputTask = findViewById<EditText>(R.id.inputTask)
        val inputTanggal = findViewById<EditText>(R.id.inputTanggal)
        val inputDeskripsi = findViewById<EditText>(R.id.inputDeskripsi)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        //ini buat ambil data utk di edit
        val judul = intent.getStringExtra("judul")
        val tanggal = intent.getStringExtra("tanggal")
        val deskripsi = intent.getStringExtra("deskripsi")
        val posisi = intent.getIntExtra("posision", -1)

        //btn simpen
        btnSimpan.setOnClickListener {
            val intent = Intent()
            intent.putExtra("judul", inputTask.text.toString())
            intent.putExtra("tanggal", inputTanggal.text.toString())
            intent.putExtra("deskripsi", inputDeskripsi.text.toString())
            intent.putExtra("posisi", posisi)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}