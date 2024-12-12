package tugaskelas.c14220163.tasklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter (
    private val context: Context,
    private val taskList: MutableList<TaskList>,
    private val onEdit: (TaskList, Int) -> Unit
): RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val judulTask: TextView = itemView.findViewById(R.id.taskTitle)
        val tanggalTask: TextView = itemView.findViewById(R.id.taskDate)
        val deskripsiTask: TextView = itemView.findViewById(R.id.taskDeskripsi)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapus)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnSelesai: Button = itemView.findViewById(R.id.btnSelesai)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_list, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]

        // Set the task data to the views in the ViewHolder
        holder.judulTask.text = task.judul
        holder.tanggalTask.text = task.tanggal
        holder.deskripsiTask.text = task.deskripsi

        val rootLayout = holder.itemView.findViewById<ConstraintLayout>(R.id.rootlayout)
        if (task.isCompleted) {
            holder.itemView.setBackgroundColor(context.getColor(R.color.completed_background))
        } else {
            holder.itemView.setBackgroundColor(context.getColor(R.color.default_background))
        }

        // Set click listeners for the buttons
        holder.btnHapus.setOnClickListener {
            taskList.removeAt(position)
            notifyItemRemoved(position)
        }
        holder.btnEdit.setOnClickListener {
            onEdit(task, position)
        }
        holder.btnSelesai.setOnClickListener {
            task.isCompleted = !task.isCompleted
            notifyItemChanged(position)
        }
    }
}
