package Starcraft;

public class Main {
    public static void main(String[] args) {
        BeforeStart beforeStart = new BeforeStart();
        beforeStart.play();
        Player player = beforeStart.player;
        Starcraft starcraft = new Starcraft(player);
        starcraft.startGame();
        starcraft.gameMenu();
    }
}
