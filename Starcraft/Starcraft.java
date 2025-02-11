package Starcraft;

import Starcraft.Unit.*;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Starcraft {
    private Player player;
    private Timer resourceTimer;
    private Timer unitAbilityTimer;

    public Starcraft(Player player) {
        this.player = player;
    }

    public void startGame() {
        // 자원 수집 타이머 시작
        resourceTimer = new Timer();
        resourceTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                player.minerals += player.mineralRate;
                player.gases += player.gasRate;
            }
        }, 0, 5000); // 1초마다 실행

        // 유닛 특수 능력 타이머 시작
        unitAbilityTimer = new Timer();
        unitAbilityTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // 각 종족별 특수 능력 실행
                for (Unit unit : player.createdUnits.keySet()) {
                    if (unit instanceof ZergUnits) {
                        ((ZergUnits) unit).regenerateHp();
                    } else if (unit instanceof ProtossUnits) {
                        ((ProtossUnits) unit).regenerateShield();
                    } else if (unit instanceof TerranUnits) {
                        if (unit.hp < unit.hpMax * 0.3) { // 체력 30% 미만
                            unit.hp = Math.max(0, unit.hp - 1);
                        }
                    }
                }
            }
        }, 0, 5000); // 5초마다 실행
    }

    public void gameMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 게임 메뉴 ===");
            System.out.println("1. 자원 현황");
            System.out.println("2. 유닛 생성");
            System.out.println("3. 유닛 목록");
            System.out.println("4. 미네랄 채취율 증가");
            System.out.println("5. 가스 채취율 증가");
            System.out.println("6. 인구수 증가");
            System.out.println("7. 게임 종료");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showResources();
                    break;
                case 2:
                    showUnitCreationMenu();
                    break;
                case 3:
                    showUnitList();
                    break;
                case 4:
                    player.increaseMineralRate();
                    break;
                case 5:
                    player.increaseGasRate();
                    break;
                case 6:
                    player.increasePopulation();
                    break;
                case 7:
                    endGame();
                    return;
            }
        }
    }

    private void showResources() {
        System.out.println("\n=== 자원 현황 ===");
        System.out.println("미네랄: " + player.minerals);
        System.out.println("가스: " + player.gases);
        System.out.println("인구수: " + player.curPopulation + "/" + player.maxPopulation);
    }

    private void showUnitCreationMenu() {
        // 종족별 생성 가능한 유닛 목록 표시
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 유닛 생성 메뉴 ===");
            if (player.race.equals("Terran")) {
                System.out.println("1. 마린 (미네랄: 50)");
                System.out.println("2. 벌쳐 (미네랄: 75)");
                System.out.println("3. 탱크 (미네랄: 150, 가스 100");
                System.out.println("0. 뒤로가기");
            }
            else if(player.race.equals("Zerg")){
                System.out.println("1. 저글링 (미네랄: 50)");
                System.out.println("2. 히드라리스크 (미네랄: 75, 가스 25)");
                System.out.println("3. 뮤탈리스크 (미네랄: 100, 가스 100");
                System.out.println("0. 뒤로가기");
            }
            else{
                System.out.println("1. 질럿 (미네랄: 100)");
                System.out.println("2. 드라군 (미네랄: 125, 가스 50)");
                System.out.println("3. 리버 (미네랄: 200, 가스 100");
                System.out.println("0. 뒤로가기");
            }
            int index = sc.nextInt();

            if (index == 0)
                break;

            // 이 코드의 중복을 피하기 위해서 팩토리 패턴을 사용해보는 게 좋다고 조언하던데
            if(player.race.equals("Terran")) {
                switch (index) {
                    case 1:
                        Marine marine = new Marine(player.name);
                        if (player.curPopulation + marine.population > player.maxPopulation
                                || player.minerals - marine.minerals < 0
                                || player.gases - marine.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= marine.minerals;
                        player.gases -= marine.gases;
                        player.curPopulation += marine.population;
                        player.createUnits(marine,1);
                        System.out.println(marine.name+"이 생산되었습니다.");
                        break;
                    case 2:
                        Vulture vulture = new Vulture(player.name);
                        if (player.curPopulation + vulture.population > player.maxPopulation
                                || player.minerals - vulture.minerals < 0
                                || player.gases - vulture.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= vulture.minerals;
                        player.gases -= vulture.gases;
                        player.curPopulation += vulture.population;
                        player.createUnits(vulture,1);
                        System.out.println(vulture.name+"이 생산되었습니다.");
                        break;
                    case 3:
                        Tank tank = new Tank(player.name);
                        if (player.curPopulation + tank.population > player.maxPopulation
                        || player.minerals - tank.minerals < 0
                        || player.gases - tank.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= tank.minerals;
                        player.gases -= tank.gases;
                        player.curPopulation += tank.population;
                        player.createUnits(tank,1);
                        System.out.println(tank.name+"이 생산되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }
            else if(player.race.equals("Zerg")){
                switch (index) {
                    case 1:
                        Zergling zergling = new Zergling(player.name);
                        if (player.curPopulation + zergling.population > player.maxPopulation
                                || player.minerals - zergling.minerals < 0
                                || player.gases - zergling.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= zergling.minerals;
                        player.gases -= zergling.gases;
                        player.curPopulation += zergling.population;
                        player.createUnits(zergling,1);
                        System.out.println(zergling.name+"이 생산되었습니다.");
                        break;
                    case 2:
                        Hydralisk hydralisk = new Hydralisk(player.name);
                        if (player.curPopulation + hydralisk.population > player.maxPopulation
                                || player.minerals - hydralisk.minerals < 0
                                || player.gases - hydralisk.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= hydralisk.minerals;
                        player.gases -= hydralisk.gases;
                        player.curPopulation += hydralisk.population;
                        player.createUnits(hydralisk,1);
                        System.out.println(hydralisk.name+"이 생산되었습니다.");
                        break;
                    case 3:
                        Mutalisk mutalisk = new Mutalisk(player.name);
                        if (player.curPopulation + mutalisk.population > player.maxPopulation
                                || player.minerals - mutalisk.minerals < 0
                                || player.gases - mutalisk.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= mutalisk.minerals;
                        player.gases -= mutalisk.gases;
                        player.curPopulation += mutalisk.population;
                        player.createUnits(mutalisk,1);
                        System.out.println(mutalisk.name+"이 생산되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }
            else{
                switch (index) {
                    case 1:
                        Zealot zealot = new Zealot(player.name);
                        if (player.curPopulation + zealot.population > player.maxPopulation
                                || player.minerals - zealot.minerals < 0
                                || player.gases - zealot.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= zealot.minerals;
                        player.gases -= zealot.gases;
                        player.curPopulation += zealot.population;
                        player.createUnits(zealot,1);
                        System.out.println(zealot.name+"이 생산되었습니다.");
                        break;
                    case 2:
                        Dragoon dragoon = new Dragoon(player.name);
                        if (player.curPopulation + dragoon.population > player.maxPopulation
                                || player.minerals - dragoon.minerals < 0
                                || player.gases - dragoon.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= dragoon.minerals;
                        player.gases -= dragoon.gases;
                        player.curPopulation += dragoon.population;
                        player.createUnits(dragoon,1);
                        System.out.println(dragoon.name+"이 생산되었습니다.");
                        break;
                    case 3:
                        Reaver reaver = new Reaver(player.name);
                        if (player.curPopulation + reaver.population > player.maxPopulation
                                || player.minerals - reaver.minerals < 0
                                || player.gases - reaver.gases < 0) {
                            System.out.println("자원이 부족하거나 최대 인구수를 초과합니다.");
                            continue;
                        }
                        player.minerals -= reaver.minerals;
                        player.gases -= reaver.gases;
                        player.curPopulation += reaver.population;
                        player.createUnits(reaver,1);
                        System.out.println(reaver.name+"이 생산되었습니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }
        }

    }

    private void showUnitList() {
        System.out.println("\n=== 보유 유닛 목록 ===");
        for (Unit unit : player.createdUnits.keySet()) {
            System.out.println(unit.name + " : " + player.createdUnits.get(unit) + "개 존재합니다.");
        }
    }

    private void endGame() {
        resourceTimer.cancel();
        unitAbilityTimer.cancel();
        System.out.println("게임을 종료합니다.");
    }
}