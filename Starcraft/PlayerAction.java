package Starcraft;

public class PlayerAction {
    public void increasePopulation(Player player){
        if(player.minerals > 100){
            player.minerals -= 100;
            player.maxPopulation = Math.min((player.maxPopulation + 8), 200);
            System.out.println("성공적으로 인구수를 증가시켰습니다.");
            System.out.println("현재 인구수 : "+player.maxPopulation);
            return;
        }
        System.out.println("미네랄이 부족합니다.");
    }
}
