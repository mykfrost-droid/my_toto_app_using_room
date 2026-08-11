package co.ke.eurochef.android.mytodos.repository;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.ke.eurochef.android.mytodos.data.Task;
import co.ke.eurochef.android.mytodos.data.TaskDao;

public class TaskRepository {

    private final TaskDao taskDao;

    private final ExecutorService executorService =
            Executors.newSingleThreadExecutor();

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void insertTask(Task task) {
        executorService.execute(() -> taskDao.insertTask(task));
    }

    public LiveData<List<Task>> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void updateTask(Task task) {
        executorService.execute(() -> taskDao.updateTask(task));
    }

    public void deleteTask(Task task) {
        executorService.execute(() -> taskDao.deleteTask(task));
    }
}