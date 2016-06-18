package Threads;

class EggVoice extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            System.out.println("Яйцо!");
        }
    }
}

public class VoiceDisput {

    static EggVoice eggOpinion;

    public static void main(String[] args) {
        eggOpinion = new EggVoice();
        eggOpinion.start(); // запуск потока, который утверждает, что яйцо было раньше
        System.out.println("Спор начат...");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Курица");
        }
        if (eggOpinion.isAlive()) {     // если побочный поток еще работает
            try {
                eggOpinion.join();      // ждём, пока закончится работа
            } catch (InterruptedException e) {
            }
            System.out.println("Победило яйцо! )))"); // объявляем победителем
        } else {
            System.out.println("Победила курица! )))"); // если побочный поток уже закончил работу (isAlive == false)
        }
        System.out.println("Спор окончен! )))");        //другой победитель
    }
}