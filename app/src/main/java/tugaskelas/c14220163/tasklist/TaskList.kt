package tugaskelas.c14220163.tasklist

data class TaskList (
    val judul :String,
    val tanggal :String,
    val deskripsi :String,
    val isCompleted :Boolean = false
)