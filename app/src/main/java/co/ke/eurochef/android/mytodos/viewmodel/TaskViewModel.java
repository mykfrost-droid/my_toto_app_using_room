package co.ke.eurochef.android.mytodos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.ke.eurochef.android.mytodos.data.AppDatabase;
import co.ke.eurochef.android.mytodos.data.DatabaseProvider;
import co.ke.eurochef.android.mytodos.data.Task;
import co.ke.eurochef.android.mytodos.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {

    private final TaskRepository repository;

    private final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        AppDatabase database =
                DatabaseProvider.getDatabase(application);

        repository = new TaskRepository(database.taskDao());

        allTasks = repository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        repository.insertTask(task);
    }

    public void updateTask(Task task) {
        repository.updateTask(task);
    }

    public void deleteTask(Task task) {
        repository.deleteTask(task);
    }
}