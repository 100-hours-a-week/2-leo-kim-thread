package Starcraft;

import java.util.Scanner;

public class BeforeStart {
    Player player;
    Player enemy;

    void play(){
        System.out.println("유저의 닉네임을 입력하세요 :");
        Scanner sc = new Scanner(System.in);
        String playerName = sc.next();
        System.out.println("종족을 입력해주세요");
        System.out.println("1.테란");
        System.out.println("2.저그");
        System.out.println("3.프로토스");
        int raceIdx = sc.nextInt();
        String race = "";
        while(race.isEmpty()) {
            switch (raceIdx) {
                case 1:
                    race = "Terran";
                    break;
                case 2:
                    race = "Zerg";
                    break;
                case 3:
                    race = "Protoss";
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
                    break;
            }
        }

        player = new Player(playerName, race);
        String[] randomComputerRace = {"Terran", "Zerg", "Protoss"};
        enemy = new Player("Computer", randomComputerRace[(int)(Math.random()*3)]);

        System.out.println(playerName+"님 스타크래프트에 오신 것을 환영합니다.");
        System.out.println("당신의 종족은 "+race+"입니다.");
        System.out.println("상대방의 종족은 "+enemy.race+"입니다.");
        System.out.println("게임을 시작합니다!");
    }

    void exit(){
        System.out.println("게임을 종료합니다.");
        System.exit(0);
    }
}
