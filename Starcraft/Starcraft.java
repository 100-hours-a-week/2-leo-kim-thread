package Starcraft;

import Starcraft.Unit.*;
import Starcraft.Unit.Factory.EnumUnitFactory;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Starcraft {
    private final Player player;
    private final PlayerAction playerAction = PlayerAction.getInstance();
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
                    playerAction.increaseMineralRate(player);
                    break;
                case 5:
                    playerAction.increaseGasRate(player);
                    break;
                case 6:
                    playerAction.increasePopulation(player);
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
                System.out.println("3. 탱크 (미네랄: 150, 가스 100)");
                System.out.println("0. 뒤로가기");
            } else if (player.race.equals("Zerg")) {
                System.out.println("1. 저글링 (미네랄: 50)");
                System.out.println("2. 히드라리스크 (미네랄: 75, 가스 25)");
                System.out.println("3. 뮤탈리스크 (미네랄: 100, 가스 100)");
                System.out.println("0. 뒤로가기");
            } else {
                System.out.println("1. 질럿 (미네랄: 100)");
                System.out.println("2. 드라군 (미네랄: 125, 가스 50)");
                System.out.println("3. 리버 (미네랄: 200, 가스 100)");
                System.out.println("0. 뒤로가기");
            }
            int index = sc.nextInt();

            if (index == 0)
                break;

            // 이 코드의 중복을 피하기 위해서 팩토리 패턴을 사용해보는 게 좋다고 조언하던데
            if (player.race.equals("Terran")) {
                switch (index) {
                    case 1:
                        createUnit(player, EnumUnitFactory.MARINE);
                        break;
                    case 2:
                        createUnit(player, EnumUnitFactory.VULTURE);
                        break;
                    case 3:
                        createUnit(player, EnumUnitFactory.TANK);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } else if (player.race.equals("Zerg")) {
                switch (index) {
                    case 1:
                        createUnit(player, EnumUnitFactory.ZERGLING);
                        break;
                    case 2:
                        createUnit(player, EnumUnitFactory.HYDRALISK);
                        break;
                    case 3:
                        createUnit(player, EnumUnitFactory.MUTALISK);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            } else if (player.race.equals("Protoss")) {
                switch (index) {
                    case 1:
                        createUnit(player, EnumUnitFactory.ZEALOT);
                        break;
                    case 2:
                        createUnit(player, EnumUnitFactory.DRAGOON);
                        break;
                    case 3:
                        createUnit(player, EnumUnitFactory.REAVER);
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }

        }

    }

    private void createUnit(Player player, EnumUnitFactory unitType) {
        if (!unitType.checkMineral(player)) {
            System.out.println("미네랄이 부족합니다.");
            return;
        }
        if (!unitType.checkGas(player)) {
            System.out.println("가스가 부족합니다.");
            return;
        }
        if (!unitType.checkPopulation(player)) {
            System.out.println("최대 인구수를 초과합니다.");
            return;
        }

        Unit unit = unitType.createUnit(player);
        unitCreationAdaption(player, unit);
    }

    private void unitCreationAdaption(Player player, Unit unit) {
        player.minerals -= unit.minerals;
        player.gases -= unit.gases;
        player.curPopulation += unit.population;
        playerAction.createUnits(player, unit, 1);
        System.out.println(unit.name + "이 생산되었습니다.");
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