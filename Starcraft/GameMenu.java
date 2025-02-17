package Starcraft;

import Starcraft.Player.Player;
import Starcraft.Player.PlayerAction;
import Starcraft.Unit.EnumUnitFactory;
import Starcraft.Unit.Unit;
import Starcraft.Unit.UnitProperties.UnitGases;
import Starcraft.Unit.UnitProperties.UnitMinerals;

import java.util.*;

public class GameMenu {
    private static final GameMenu gameMenu = new GameMenu();
    private static final PlayerAction playerAction = PlayerAction.getInstance();
    private Map<String, Player> players;
    private final Map<String, String> unitListInfo;

    GameMenu() {
        unitListInfo = new HashMap<>();
        makeUnitCreationMenu();
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public static GameMenu getInstance() {
        return gameMenu;
    }

    public void run(Player player) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String menu = """
                    
                    === 게임 메뉴 ===
                    1. 자원 현황
                    2. 유닛 생성
                    3. 유닛 목록
                    4. 미네랄 채취율 증가
                    5. 가스 채취율 증가
                    6. 인구수 증가
                    7. 정찰 하기
                    8. 게임 종료""";
            System.out.println(menu);
            try{
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
                    int actionResultMineral = playerAction.increaseMineralRate(player);
                    switch (actionResultMineral) {
                        case 0:
                            String worker = switch (player.race) {
                                case "Terran" -> WorkerName.TERRAN_WORKER.getWorkerName();
                                case "Zerg" -> WorkerName.ZERG_WORKER.getWorkerName();
                                case "Protoss" -> WorkerName.PROTOSS_WORKER.getWorkerName();
                                default -> "";
                            };
                            System.out.println("성공적으로 " + worker + "를 추가하였습니다.");
                            System.out.println("현재 미네랄 채취율 : " + player.mineralRate + "/sec");
                            break;
                        case 1:
                            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
                            break;
                        case 2:
                            String supply = switch (player.race) {
                                case "Terran" -> PopulationName.TERRAN_POP.getPopName();
                                case "Zerg" -> PopulationName.ZERG_POP.getPopName();
                                case "Protoss" -> PopulationName.PROTOSS_POP.getPopName();
                                default -> "";
                            };
                            System.out.println("최대 인구수입니다. " + supply + "를 더 추가하십시오.");
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 5:
                    int actionResultGas = playerAction.increaseGasRate(player);
                    switch (actionResultGas) {
                        case 0:
                            String worker = switch (player.race) {
                                case "Terran" -> WorkerName.TERRAN_WORKER.getWorkerName();
                                case "Zerg" -> WorkerName.ZERG_WORKER.getWorkerName();
                                case "Protoss" -> WorkerName.PROTOSS_WORKER.getWorkerName();
                                default -> "";
                            };
                            System.out.println("성공적으로 " + worker + "를 추가하였습니다.");
                            System.out.println("현재 가스 채취율 : " + player.gasRate + "/sec");
                            break;
                        case 1:
                            System.out.println("일꾼을 뽑을 미네랄이 부족합니다.");
                            break;
                        case 2:
                            String supply = switch (player.race) {
                                case "Terran" -> PopulationName.TERRAN_POP.getPopName();
                                case "Zerg" -> PopulationName.ZERG_POP.getPopName();
                                case "Protoss" -> PopulationName.PROTOSS_POP.getPopName();
                                default -> "";
                            };
                            System.out.println("최대 인구수입니다. " + supply + "를 더 추가하십시오.");
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 6:
                    if (playerAction.increasePopulation(player)) {
                        System.out.println("성공적으로 인구수를 증가시켰습니다.");
                        System.out.println("현재 인구수 : " + player.maxPopulation);
                    } else {
                        System.out.println("미네랄이 부족합니다.");
                    }
                    break;
                case 7:
                    System.out.println("정찰 비용은 일꾼 한마리입니다.");
                    if (player.mineralRate <= 0) {
                        if (player.gasRate <= 0) {
                            System.out.println("정찰 보낼 일꾼이 존재하지 않습니다.");
                        } else {
                            player.gasRate -= GameProperty.INCREASE_GAS_RATE.getPlayerProperty();
                            player.curPopulation -= GameProperty.INCREASE_GAS_RATE_POP.getPlayerProperty();
                            showOtherPlayers(players, player);
                        }
                    } else {
                        player.mineralRate -= GameProperty.INCREASE_MINERAL_RATE.getPlayerProperty();
                        player.curPopulation -= GameProperty.INCREASE_MINERAL_RATE_POP.getPlayerProperty();
                        showOtherPlayers(players, player);
                    }
                    break;
                case 8:
                    endGame();
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }

            }catch(InputMismatchException e){
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                sc.nextLine();
            }
        }
    }

    private void showOtherPlayers(Map<String,Player> players, Player player) {

        for(String name : players.keySet()){
            // 다른 사람의 정보만 열람
            if(player.name.equals(name)){
                continue;
            }

            Player otherPlayer = players.get(name);
            System.out.println(otherPlayer);
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
        unitType = switch (player.race) {
            case "Terran" -> switch (index) {
                case 1 -> EnumUnitFactory.MARINE;
                case 2 -> EnumUnitFactory.VULTURE;
                case 3 -> EnumUnitFactory.TANK;
                default -> unitType;
            };
            case "Zerg" -> switch (index) {
                case 1 -> EnumUnitFactory.ZERGLING;
                case 2 -> EnumUnitFactory.HYDRALISK;
                case 3 -> EnumUnitFactory.MUTALISK;
                default -> unitType;
            };
            case "Protoss" -> switch (index) {
                case 1 -> EnumUnitFactory.ZEALOT;
                case 2 -> EnumUnitFactory.DRAGOON;
                case 3 -> EnumUnitFactory.REAVER;
                default -> unitType;
            };
            default -> null;
        };

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
        System.out.println("게임을 종료합니다!");
        System.exit(0);
    }
}

