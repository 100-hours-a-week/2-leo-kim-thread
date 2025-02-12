package Starcraft;

import Starcraft.Unit.Factory.EnumUnitFactory;
import Starcraft.Unit.Unit;
import Starcraft.Unit.UnitGases;
import Starcraft.Unit.UnitMinerals;

import java.util.*;

public class GameMenu {
    private static final GameMenu gameMenu = new GameMenu();
    private static final PlayerAction playerAction = PlayerAction.getInstance();
    private final Map<String, String> unitListInfo;
    private final String menu =
            "\n=== 게임 메뉴 ===\n" +
                    "1. 자원 현황\n" +
                    "2. 유닛 생성\n" +
                    "3. 유닛 목록\n" +
                    "4. 미네랄 채취율 증가\n" +
                    "5. 가스 채취율 증가\n" +
                    "6. 인구수 증가\n" +
                    "7. 게임 종료";

    GameMenu() {
        unitListInfo = new HashMap<>();
        makeUnitCreationMenu();
    }

    public static GameMenu getInstance() {
        return gameMenu;
    }

    public void run(Player player) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(menu);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    showResources(player);
                    break;
                case 2:
                    showUnitCreationMenu(player);
                    break;
                case 3:
                    showUnitList(player);
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

    private void showResources(Player player) {
        System.out.println("\n=== 자원 현황 ===");
        System.out.println("미네랄: " + player.minerals);
        System.out.println("가스: " + player.gases);
        System.out.println("인구수: " + player.curPopulation + "/" + player.maxPopulation);
    }

    private void showUnitCreationMenu(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.println(unitListInfo.get(player.race)); // 유닛 생성 메뉴 출력
        int index = sc.nextInt();

        if (index == 0) return; // 뒤로가기

        // 종족에 따라 생성 가능한 유닛 매칭
        EnumUnitFactory unitType = null;
        if (player.race.equals("Terran")) {
            switch (index) {
                case 1: unitType = EnumUnitFactory.MARINE; break;
                case 2: unitType = EnumUnitFactory.VULTURE; break;
                case 3: unitType = EnumUnitFactory.TANK; break;
            }
        } else if (player.race.equals("Zerg")) {
            switch (index) {
                case 1: unitType = EnumUnitFactory.ZERGLING; break;
                case 2: unitType = EnumUnitFactory.HYDRALISK; break;
                case 3: unitType = EnumUnitFactory.MUTALISK; break;
            }
        } else if (player.race.equals("Protoss")) {
            switch (index) {
                case 1: unitType = EnumUnitFactory.ZEALOT; break;
                case 2: unitType = EnumUnitFactory.DRAGOON; break;
                case 3: unitType = EnumUnitFactory.REAVER; break;
            }
        }

        if (unitType == null) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        // 유닛 생성 시도
        createUnit(player, unitType);
    }

    private void showUnitList(Player player) {
        System.out.println("\n=== 보유 유닛 목록 ===");
        for (Unit unit : player.createdUnits.keySet()) {
            System.out.println(unit.name + " : " + player.createdUnits.get(unit) + "개 존재합니다.");
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

    private void makeUnitCreationMenu() {
        List<String>[] unitList = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            unitList[i] = new ArrayList<>();
        }
        String[] races = {"Terran", "Zerg", "Protoss"};

        unitList[0].addAll(List.of("MARINE", "VULTURE", "TANK"));
        unitList[1].addAll(List.of("ZERGLING", "HYDRALISK", "MUTALISK"));
        unitList[2].addAll(List.of("ZEALOT", "DRAGOON", "REAVER"));

        for (int i = 0; i < unitList.length; i++) {
            String unitMenuString = getUnitInfo(unitList, i);
            unitListInfo.put(races[i], unitMenuString);
        }
    }

    private String getUnitInfo(List<String>[] unitList, int raceIdx) {
        int unitIdx = 1;
        StringBuilder sb = new StringBuilder();

        for (String unit : unitList[raceIdx]) {
            sb.append(unitIdx++).append(". ").append(unit).append(" (미네랄 : ")
                    .append(UnitMinerals.valueOf(unit + "_MINERAL").getMinerals());
            int gas = UnitGases.valueOf(unit + "_GAS").getGases();
            sb.append(gas == 0 ? ")" : " 가스 : " + gas + ")");
            sb.append("\n");
        }
        sb.append("0. 뒤로가기");
        return sb.toString();
    }

    private void endGame() {
        System.exit(0);
    }
}
