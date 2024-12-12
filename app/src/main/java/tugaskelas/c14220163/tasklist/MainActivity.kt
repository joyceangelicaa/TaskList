package tugaskelas.c14220163.tasklist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //variabel global
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskAdapter: TaskListAdapter
    private val taskList = mutableListOf<TaskList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.screen_daftar)

        //inisiasi recyclerview
        recyclerView = findViewById(R.id.recyclerView)
        taskAdapter = TaskListAdapter(this,taskList){ task, position ->
            val intent = Intent(this, TambahActivity::class.java)
            intent.putExtra("judul", task.judul)
            intent.putExtra("tanggal", task.tanggal)
            intent.putExtra("deskripsi", task.deskripsi)
            intent.putExtra("posisi", position)
            startActivity(intent)
        }
        recyclerView.adapter = taskAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //btn tambah
        val btnTambah = findViewById<Button>(R.id.btnTambah)
        btnTambah.setOnClickListener {
            val intent = Intent(this, TambahActivity::class.java)
            startActivity(intent)
        }
        addSampleData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val judul = data.getStringExtra("judul")?:return
            val tanggal = data.getStringExtra("tanggal")?:return
            val deskripsi = data.getStringExtra("deskripsi")?:return
            val posisi = data.getIntExtra("posisi", -1)

            when (requestCode) {
                REQUEST_CODE_TAMBAH -> {
                    taskList.add(TaskList(judul , tanggal, deskripsi))
                    taskAdapter.notifyDataSetChanged()
                }
                REQUEST_CODE_EDIT -> {
                    if (posisi != -1) {
                        taskList[posisi] = TaskList(judul, tanggal, deskripsi, taskList[posisi].isCompleted)
                        taskAdapter.notifyItemChanged(posisi)
                    }
                }
            }
        }
    }

    private fun addSampleData() {
        taskList.add(TaskList("Task 1", "1-Mei-2022", "Deskripsi 1"))
        taskList.add(TaskList("Task 2", "2-Mei-2022", "Deskripsi 2"))
        taskList.add(TaskList("Task 3", "3-Mei-2022", "Deskripsi 3"))
        taskAdapter.notifyDataSetChanged()
    }
    companion object {
        const val REQUEST_CODE_TAMBAH = 1
        const val REQUEST_CODE_EDIT = 2
    }
}