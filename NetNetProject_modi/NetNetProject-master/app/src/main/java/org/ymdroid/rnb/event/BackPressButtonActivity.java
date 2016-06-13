package org.ymdroid.rnb.event;

import android.app.Activity;
import android.widget.Toast;

public class BackPressButtonActivity {
//�ڷΰ��� ��ư 2ȸ ���� �� ���� (�� �� Ŭ���� �� 2�ʰ� ������ ������ ����)
	
        private long backKeyPressedTime = 0;
        private Toast toast;
        private Activity activity;

       public BackPressButtonActivity(Activity activity) {
                      this.activity = activity;
        }

         public void onBackPressed() {
                       if (isAfter2Seconds()) { //�� ó�� & 2�ʰ� ������ ���
                               backKeyPressedTime = System.currentTimeMillis();
                               // ����ð��� �ٽ� �ʱ�ȭ
                               toast = Toast.makeText(activity, "뒤로가기", Toast.LENGTH_SHORT);
                               toast.show();
                                return;
                      }
                      if (isBefore2Seconds()) { //2�ʰ� ������ �ʾ��� ���
                                programShutdown(); //���α׷��� �����Ų��.
                                toast.cancel();
                      }
         }

          private Boolean isAfter2Seconds() {
                  return System.currentTimeMillis() > backKeyPressedTime + 2000;
                                 // 2�� ������ ���
         }  

          private Boolean isBefore2Seconds() {
                  return System.currentTimeMillis() <= backKeyPressedTime + 2000;
                                 // 2�ʰ� ������ �ʾ��� ���
         }

          private void programShutdown() {
                         activity .moveTaskToBack(true);
                         activity .finish();
                         android.os.Process.killProcess(android.os.Process.myPid());
                         System.exit(0);
         }

}
