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
        eggOpinion.start();
        System.out.println("Спор начат...");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Курица");
        }
        if (eggOpinion.isAlive()) {
            try {
                eggOpinion.join();
            } catch (InterruptedException e) {
            }
            System.out.println("Победило яйцо! )))");
        } else {
            System.out.println("Победила курица! )))");
        }
        System.out.println("Спор окончен! )))");
    }
}