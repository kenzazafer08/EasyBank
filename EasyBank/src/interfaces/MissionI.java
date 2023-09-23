package interfaces;

import dto.Client;
import dto.Mission;

import java.util.List;

public interface MissionI {
    Mission add(Mission mission);
    boolean delete(int id);
    List<Mission> showList();
    List<Mission> missionHistory();
    List<Mission> missionStatistics();
}
