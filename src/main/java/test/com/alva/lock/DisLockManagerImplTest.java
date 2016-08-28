import com.alva.lock.DisLock;
import com.alva.lock.DisLockManager;
import com.alva.lock.DisLockManagerImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
//import org.junit.Test;


public class DisLockManagerImplTest{

  // @Test
   public void testGetLock() throws InterruptedException {

        DisLockManagerImpl lockManager = new DisLockManagerImpl("localhost:2181");
        lockManager.init();

        Task task = new Task(lockManager);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
        executorService.submit(task);
        }
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(50);
        }
   private class Task implements Runnable {

   private DisLockManager lockManager;

   public Task(DisLockManager lockManager){
      this.lockManager = lockManager;
   }

   int i = 0;

   public void run() {
      DisLock lock = lockManager.getLock("testLock");
      lock.lock();
      try {
         i++;
         System.out.println(i);

      } finally {
         lock.unlock();
      }

   }

}
}