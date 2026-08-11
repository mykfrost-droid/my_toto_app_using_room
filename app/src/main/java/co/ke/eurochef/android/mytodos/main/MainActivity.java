package co.ke.eurochef.android.mytodos.main;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import co.ke.eurochef.android.mytodos.adapter.TaskAdapter;
import co.ke.eurochef.android.mytodos.data.Task;
import co.ke.eurochef.android.mytodos.viewmodel.TaskViewModel;
import co.ke.eurochef.android.mytodos.R;

public class MainActivity extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    private TaskAdapter taskAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerViewTasks =
                findViewById(R.id.recyclerViewTasks);

        FloatingActionButton fabAddTask =
                findViewById(R.id.fabAddTask);

        taskAdapter = new TaskAdapter(
                new TaskAdapter.OnTaskActionListener() {

                    @Override
                    public void onTaskChecked(Task task) {

                        task.setCompleted(!task.isCompleted());

                        taskViewModel.updateTask(task);
                    }

                    @Override
                    public void onTaskDeleted(Task task) {

                        taskViewModel.deleteTask(task);
                    }
                }
        );

        recyclerViewTasks.setLayoutManager(
                new LinearLayoutManager(this)
        );

        recyclerViewTasks.setAdapter(taskAdapter);

        taskViewModel = new ViewModelProvider(this)
                .get(TaskViewModel.class);

        taskViewModel.getAllTasks().observe(this, tasks -> {

            taskAdapter.setTasks(tasks);

        });

        fabAddTask.setOnClickListener(v -> {

            // We will add the Add Task dialog here next.

        });
    }
}